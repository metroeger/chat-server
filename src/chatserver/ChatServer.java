package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

    public static void main(String[] args) {

        Vector <ClientConnection> clients = new Vector<>();
        System.out.println("server is about to start");
        
        try {
            //ServerSocket serverSocket = new ServerSocket(3333);
            ServerSocket serverSocket = new ServerSocket(3333);
          
            while (true) {
                System.out.println("server is waiting");

                Socket socket = serverSocket.accept();
                
                // reading from client
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientName = br.readLine();
                
                // writing (sending) to client
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                
                ClientConnection clientConn = new ClientConnection(clientName, br, pw);
                clients.add(clientConn);
                
                ReaderThread rt = new ReaderThread(clients, clientConn);
                rt.start();
                
                System.out.println(clientName + " is connected");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}
