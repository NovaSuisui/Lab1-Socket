import java.io.*;
import java.net.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        String dateData;
        byte[] receiveData = new byte[1024];
        byte[] sendData  = new byte[1024];
        InetAddress IPAddress;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int port;
        while(true) {
            System.out.println("The server is waiting");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            IPAddress = receivePacket.getAddress();
            port = receivePacket.getPort();
            dateData = formatter.format(LocalDateTime.now());
            sendData = dateData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket); 
        }
    }    
}
