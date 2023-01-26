import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServere {
    private static ServerSocket welcomeSocket;

    public static void main(String[] args) throws Exception {
        String num1, num2;
        String status = "";
        ServerSocket welcomeSocket = null;
        Socket connectionSocket = null;
        Scanner inFromClient =null;
        DataOutputStream outToClient = null;
        try {
            welcomeSocket = new ServerSocket(5678);
        } catch (IOException e) {
            System.out.println("Cannot create a welcome socket");
            System.exit(1);
        }
        while (true) {
            try {
                System.out.println("Waiting for client connection at port number 5678");
                connectionSocket = welcomeSocket.accept();
                inFromClient = new Scanner(connectionSocket.getInputStream());
                outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                while (true) {
                    System.out.println("STARTING...");
                    status = "enter number 1 (to end just press enter) : " + '\n';
                    outToClient.writeBytes(status);
                    num1 = inFromClient.nextLine();
                    if (num1.equals("!DISCONNECT")) {
                        System.out.println("DISCONNECTING...");
                        break;
                    }
                    System.out.println("Num 1 is " + num1);
    
                    status = "enter number 2 (to end just press enter) : " + '\n';
                    outToClient.writeBytes(status);
                    num2 = inFromClient.nextLine();
                    if (num2.equals("!DISCONNECT")) {
                        System.out.println("DISCONNECTING...");
                        break;
                    }
                    System.out.println("Num 1 is " + num2);
    
                    status = "The result is :" + (Integer.parseInt(num1) + Integer.parseInt(num2)) + '\n';
                    outToClient.writeBytes(status);
                    System.out.print(status);
                }
            }
            catch (IOException e) {
                System.out.println("Error cannot create this connection");
            }
            finally {
                System.out.println("Connection Close");
                try {
                    if (inFromClient != null)
                        inFromClient.close();
                    if (outToClient != null)
                        outToClient.close();
                    if (connectionSocket != null)
                        connectionSocket.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}