import java.io.*;
import java.net.*;

public class Ex5Server {

    public static void main(String args[]) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(1234);

            while (true) {
                // Server receives message from client
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client: " + clientMessage);

                // Check if client wants to exit
                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Client closed the connection.");
                    break;
                }

                // Server sends response to client
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("You: ");
                String serverMessage = br.readLine();
                byte[] sendData = serverMessage.getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                // Check if server wants to exit
                if (serverMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Closing connection...");
                    break;
                }
            }

            // Close resources
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
