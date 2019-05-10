package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInterface extends Remote {

	public void register(ChatClientInterface c) throws RemoteException ;
	
	public void unRegister(ChatClientInterface c) throws RemoteException ;
	
	public void sendToAll(String msg, ChatClientInterface from) throws RemoteException ;
	
}
