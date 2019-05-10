package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class SubServer implements Runnable 
{
	ArenaServer arena;
	Socket socket;
	
	private PrintWriter writer;
	private BufferedReader reader;
	
	public SubServer(ArenaServer arenaServer, Socket socket) 
	{
		this.arena = arenaServer;
		this.socket = socket;
		
		try 
		{
			writer = new PrintWriter(socket.getOutputStream()) ;
			writer.println(arena.getCapacity() - getTakenPlaces());
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run() 
	{
		while(true)
		{
			try {
				String name = reader.readLine();
				String number = reader.readLine();
				if(number != null)
				{
					int numReserved = Integer.parseInt(number);
				
					reserve(name, numReserved);
				}
				
			} catch (IOException e) {
				arena.getClients().remove(socket);
			}
		}
	}
	
	public void reserve(String name, int number)
	{
		HashMap<String, Integer> reservation = arena.getReservations();
		reservation.put(name, number);
		sendPlacesRemaining();
	}
	
	public void sendPlacesRemaining()
	{
		for(Socket cli : arena.getClients())
		{
			PrintWriter writer;
			try {
				writer = new PrintWriter(cli.getOutputStream());
				writer.println(arena.getCapacity() - getTakenPlaces());
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getTakenPlaces()
	{
		int placesTaken = 0;
		for (Integer value : arena.getReservations().values()) {
		    placesTaken += value;
		}
		return placesTaken;
	}
}
