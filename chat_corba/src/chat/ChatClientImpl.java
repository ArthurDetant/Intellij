package chat;

import chat_corba.*;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class ChatClientImpl extends ChatClientPOA {

	private ChatClientFrame frm ;

	private String userName ;
	private ORB orb ;
	private ChatClient stub ;
	private ChatServer server ;
	
	public ChatClientImpl(ChatClientFrame f, String userName, String [] args) {
		frm = f ;
		this.userName = userName ;
		try {



			orb = ORB.init(args, null) ;
			Thread orbThread = new Thread(new Runnable() {
				@Override
				public void run() {
					orb.run() ; // !!! bloquant !!! donc dans un thread
				}
			}) ;
			orbThread.start() ;

			// creation de la rfrence CORBA sur l'implmentation du client
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA")) ;
			rootPOA.the_POAManager().activate() ;
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(this);
			stub = ChatClientHelper.narrow(ref);

			// lecture de l'IOR du serveur
			BufferedReader br = new BufferedReader(new FileReader("server_ior.txt")) ;
			String ior = br.readLine() ;
			br.close() ;

			// rcupration de la rfrence sur le serveur ( partir de son IOR)
			org.omg.CORBA.Object o = orb.string_to_object(ior) ;
			server = ChatServerHelper.narrow(o) ;

			server.register(stub) ;
			frm.setTitle(userName + " is connected") ;

		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}

	@Override
	public String getUserName(){
		System.out.println(userName + " sending user name !");
		return userName ;
	}

	@Override
	public void addMSG(String msg){
		System.out.println(userName + " adding msg : " + msg);
		frm.appendMsg(msg) ;
	}

	@Override
	public void updateConnectedUsers(String[] names) {
		System.out.println(userName + " updating connected user names !");
		frm.users.setListData(names) ;
	}
	
	public void sendToAll(String msg) {
		try {
			server.sendToAll(msg, stub) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unRegister() {
		try {
			server.unRegister(stub) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
