import java.io.*;
import java.net.*;
import java.util.*;

public class Ex6Client {

    public static void main(String args[]) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter two integers a and b (where a < b):");
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            byte[] sendData = new byte[1024];
            InetAddress serverAddress = InetAddress.getByName("localhost");

            // Send the integers a and b to the server
            sendData = (a + " " + b).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1234);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];

            // Receive even numbers from the server
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String received = new String(receivePacket.getData(), 0, receivePacket.getLength());

                if (received.equals("END")) {
                    System.out.println("Server has finished sending even numbers.");
                    break;
                } else {
                    System.out.println("Received from server: " + received);
                }
            }

            clientSocket.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
