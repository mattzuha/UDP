import java.io.*;
import java.net.*;
import java.util.*;

public class Ex5Client {

    public static void main(String args[]) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1234;

            while (true) {
                // Client sends a message to the server
                System.out.print("You: ");
                String message = scanner.nextLine();
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);

                // Check if client wants to exit
                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("Closing connection...");
                    break;
                }

                // Client receives response from server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + serverResponse);

                // Check if server wants to exit
                if (serverResponse.equalsIgnoreCase("bye")) {
                    System.out.println("Server closed the connection.");
                    break;
                }
            }

            // Close resources
            scanner.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
