import java.io.*;
import java.net.*;
import java.util.*;

public class Ex3Client {

    public static void main(String args[]) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Input two number and operation:");
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();
            String operation = scanner.next();

            byte[] buff1 = String.valueOf(num1).getBytes();
            byte[] buff2 = String.valueOf(num2).getBytes();
            byte[] buff3 = operation.getBytes();

            InetAddress addsv = InetAddress.getByName("localhost");
            DatagramPacket a = new DatagramPacket(buff1, buff1.length, addsv, 1234);
            clientSocket.send(a);
            DatagramPacket b = new DatagramPacket(buff2, buff2.length, addsv, 1234);
            clientSocket.send(b);
            DatagramPacket c = new DatagramPacket(buff3, buff3.length, addsv, 1234);
            clientSocket.send(c);

            byte buff4[] = new byte[256];
            DatagramPacket re = new DatagramPacket(buff4, buff4.length);
            clientSocket.receive(re);
            String data = new String(re.getData()).trim();
            System.out.println("Result: " + data);

            clientSocket.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
