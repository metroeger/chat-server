
package chatserver;

import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderThread extends Thread{
    
    Vector<ClientConnection> clients;
    ClientConnection clientConn;

    public ReaderThread(Vector<ClientConnection> clients, ClientConnection clientConn) {
        this.clients = clients;
        this.clientConn = clientConn;
    }

    @Override
    public void run() {
        
        String message = "";
        
        while (true){
            try {
                message = clientConn.getBr().readLine();
                
                String toSend = clientConn.getName()+ ": " + message;
                
                System.out.println(clientConn.getName()+"'s message..."); // just for check up on server
                
                
                for (ClientConnection c : clients){
                   if (c!=clientConn){
                        //writing to everybody, who isn't this client
                        c.getPw().println(toSend);
                        c.getPw().flush();
                    }
                }              
            } catch (IOException ex) {
               
                boolean isOut = false;
                for (ClientConnection c : clients){
                  if (c==clientConn){
                      isOut = true;
                  }
                }
                if (isOut){
                    for (ClientConnection c : clients){
                    {
                        c.getPw().println(clientConn.getName() + "'s left the chat room");
                        c.getPw().flush();
                    }
                }        
                    System.out.println(clientConn.getName() + "'s left the chat room");    
                    break;
                }
            }
        }
    }        
}
