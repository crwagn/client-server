package client;

import java.net.*;
import java.io.*;

public class Client {
    
    private Socket socket = null;
    private DataInputStream input = null;
    private DataInputStream inputFromServer = null;
    private DataOutputStream output = null;
    String serverResponse;
    boolean connected = false;
    
    public Client(String address, int port) throws IOException{
        
        socket = new Socket(address, port);
        input = new DataInputStream(System.in);
        inputFromServer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        output = new DataOutputStream(socket.getOutputStream());
        connected = true;
    }
    
    public void close() throws IOException{
    
        socket.close();
        input.close();
        output.close();
        connected = false;
    }
    
    public String write(String input) throws IOException{
    
           output.writeUTF(input);
           serverResponse = inputFromServer.readUTF();
           return serverResponse;
    }
    
    //Check if client is connected to server
    public boolean isConnected(){
    
        return connected;
    }
}
