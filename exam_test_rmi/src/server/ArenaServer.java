package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Vector;

import common.ClientInterface;
import common.ServerInterface;


public class ArenaServer implements ServerInterface {
	private HashMap<String, Integer> reservations = new HashMap<String, Integer>() ;

	private int capacity = 5000;

	private Vector<ClientInterface> clients;

	ServerInterface stub;
	Registry registry;

	public ArenaServer() {

		clients = new Vector<>();
		
		try {
			String name = "Server";
			stub = (ServerInterface)UnicastRemoteObject.exportObject(this,0);
			registry = LocateRegistry.createRegistry(1099);
			registry.rebind(name, stub);

			System.out.println("Arena Server waiting for connections");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Vector<ClientInterface> getClients() {
		return clients;
	}

	HashMap<String, Integer> getReservations() {
		return reservations;
	}

	int getCapacity() {
		return capacity;
	}

	@Override
	public void register(ClientInterface client) throws RemoteException 
	{
		clients.add(client);
		
		try {
			for(ClientInterface cli : clients)
				cli.setRemainingPlaces(getCapacity() - getTakenPlaces());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		System.out.println("New client connected.");
	}

	public static void main(String[] args) 
	{
		new ArenaServer() ;
	}

	@Override
	public void reserve(String name, int number) {
		reservations.put(name, number);
		try {
			for(ClientInterface cli : clients)
				cli.setRemainingPlaces(getCapacity() - getTakenPlaces());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private int getTakenPlaces() 
	{
		int n = 0;

		for(Integer val : reservations.values())
			n += val;
		
		return n;
	}

	@Override
	public void remove(ClientInterface client) throws RemoteException 
	{
		clients.remove(client);
	}

	@Override
	public String getNames() throws RemoteException {
		String str = "";
		for(String name : reservations.keySet())
			str+=name+";";
		return str;
	}
}