package test_Chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

public class clientThread extends Thread{
    private String clientName = null;
    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
    private final clientThread[] threads;
    private int maxClients;
    private Vector<String> nomclients = new Vector();

    public clientThread(Socket clientSocket, clientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClients = threads.length;
    }


    public String getClientName() {
        return clientName;
    }


    public void run() {
        int maxClients = this.maxClients;
        clientThread[] threads = this.threads;


        try {
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());

            String name;
            os.println("Veuillez écrire votre pseudo.");
            name = is.readLine().trim();
            os.println("Bienvenue " + name+ " sur le chat. \n '/quit' pour quitter.");
            synchronized (this) {
                for (int i = 0; i < maxClients; i++) {
                    if (threads[i] != null && threads[i] == this) {
                        clientName =name;
                        break;
                    }
                }
                for (int i = 0; i < maxClients; i++) {
                    if (threads[i] != null && threads[i] != this) {
                        threads[i].os.println("*** Nouvel utilisateur : " + name+ " ***");
                    }
                }
                nomclients.add(getClientName());

            }

            while (true) {
                    String line = is.readLine();

                    if (line.startsWith("/quit")) {
                        break;
                    }
                    synchronized (this)
                    {
                            for (int i = 0; i < maxClients; i++)
                            {
                                if (threads[i] != null && threads[i].clientName != null) {
                                    threads[i].os.println("<" + name + "> " + line);
                                }
                            }
                    }
                System.out.println(nomclients);

                }
            synchronized (this) {
                for (int i = 0; i < maxClients; i++) {
                    if (threads[i] != null && threads[i] != this && threads[i].clientName != null) {
                        threads[i].os.println("*** " + name+ " a quitté le chat ***");
                        }
                }
                nomclients.remove(getClientName());
            }
            os.println("*** A+ " + name + " ***");
            synchronized (this) {
                for (int i = 0; i < maxClients; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                    }
                }
            }
            is.close();
            os.close();
            clientSocket.close();
        } catch (IOException e) {
        }
    }

}