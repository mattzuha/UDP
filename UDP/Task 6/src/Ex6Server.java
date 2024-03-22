import java.io.*;
import java.net.*;

public class Ex6Server {

    public static void main(String args[]) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(1234);

            byte[] receiveData = new byte[1024];

            // Receive integers a and b from the client
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] numbers = received.split(" ");
            int a = Integer.parseInt(numbers[0]);
            int b = Integer.parseInt(numbers[1]);

            // Send even numbers from range [a, b] to the client
            for (int i = a; i <= b; i++) {
                if (i % 2 == 0) {
                    byte[] sendData = String.valueOf(i).getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                    serverSocket.send(sendPacket);
                }
            }

            // Signal client that no more numbers will be sent
            byte[] endSignal = "END".getBytes();
            DatagramPacket endPacket = new DatagramPacket(endSignal, endSignal.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(endPacket);

            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
