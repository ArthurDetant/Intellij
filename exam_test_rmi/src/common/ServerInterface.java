package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface  extends Remote 
{
	void register(ClientInterface client) throws RemoteException;
	public void remove(ClientInterface client) throws RemoteException;
	public void reserve(String name, int number) throws RemoteException;
	public String getNames() throws RemoteException;
}
