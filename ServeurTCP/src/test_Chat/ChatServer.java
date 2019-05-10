package test_Chat;

import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class ChatServer {
    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    private static final int maxClients = 50;
    private static final clientThread[] threads = new clientThread[maxClients];
    static final int PORT = 6666;

    public static void main(String args[]) {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println(e);
        }
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                int i = 0;
                for (i = 0; i < maxClients; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new clientThread(clientSocket, threads)).start();

                        break;
                    }
                }
                if (i == maxClients) {
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Trop de monde sur le chat");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }
}
