package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface  extends Remote 
{
	public void setRemainingPlaces(int n) throws RemoteException;
}
