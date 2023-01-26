import java.io.*; 
import java.net.*;
import java.util.*;

class TCPClient { 
    public static void main(String argv[]) throws Exception 
    {
        String status = "";
        Scanner in = new Scanner(System.in);
        Socket clientSocket = null;
        Scanner inFromServer = null;
        DataOutputStream outToServer = null;
        String num1, num2;
        try {
            clientSocket = new Socket("localhost", 5678); 
            inFromServer = new Scanner(clientSocket.getInputStream());
            outToServer = new DataOutputStream(clientSocket.getOutputStream());

            while (true) {
                status = inFromServer.nextLine();
                System.out.println(status);
                num1 = in.nextLine();
                if (num1.isEmpty()) {
                    num1 = "!DISCONNECT";
                    break;
                }
                outToServer.writeBytes(num1 + '\n');
    
                status = inFromServer.nextLine();
                System.out.println(status);
                num2 = in.nextLine();
                if (num2.isEmpty()) {
                    num2 = "!DISCONNECT";
                    break;
                }
                outToServer.writeBytes(num2 + '\n');
    
                status = inFromServer.nextLine();
                System.out.println(status);
            }
        }
        catch (IOException e) {
            System.out.println("Error occurred: Closing the connection");
        }
        finally {
            try {
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } 
    } 
}