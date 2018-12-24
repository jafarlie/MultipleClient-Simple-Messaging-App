package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Client {


    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    public Client(String address, int port){
        try {
            socket = new Socket(address, port);
            System.out.println("Trying to connect to the server ...");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Connected to the server");

            // Takes input from the client through the terminal
            input = new DataInputStream(System.in);
            // Sends out to the socket
            output = new DataOutputStream(socket.getOutputStream());
        }catch (UnknownHostException host){
            System.out.println(host);
        } catch (IOException e){
            System.out.println(e);
        } catch (InterruptedException interrupt) {
            interrupt.printStackTrace();
        }
        // String that will contain the message from the client
        String message = "";
        while(!message.equals("exit")){
            try{
                message = input.readLine();
                output.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            input.close();
            output.close();
            socket.close();
        } catch (IOException io){
            System.out.println(io);
        }
    }

    public static void main(String[] args){
        Client client = new Client("127.0.0.1", 5000);
    }
}
