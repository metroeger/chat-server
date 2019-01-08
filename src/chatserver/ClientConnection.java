
package chatserver;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ClientConnection {
    
    private String name;
    private BufferedReader br;
    private PrintWriter pw;

    public ClientConnection(String name, BufferedReader br, PrintWriter pw) {
        this.name = name;
        this.br = br;
        this.pw = pw;
    }

    public PrintWriter getPw() {
        return pw;
    }

    public void setPw(PrintWriter pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }
    
    
}
