import java.io.*;
import java.net.*;

public class Ex3Server {

    public static void main(String args[]) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(1234);

            byte buff1[] = new byte[256];
            DatagramPacket a = new DatagramPacket(buff1, buff1.length);
            serverSocket.receive(a);
            String data1 = new String(a.getData()).trim();
            int num1 = Integer.parseInt(data1);

            byte buff2[] = new byte[256];
            DatagramPacket b = new DatagramPacket(buff2, buff2.length);
            serverSocket.receive(b);
            String data2 = new String(b.getData()).trim();
            int num2 = Integer.parseInt(data2);

            byte buff3[] = new byte[256];
            DatagramPacket c = new DatagramPacket(buff3, buff3.length);
            serverSocket.receive(c);
            String operation = new String(c.getData()).trim();

            double firefly = 0.0;
            switch (operation) {
                case "addition":
                    firefly = num1 + num2;
                    break;
                case "subtraction":
                    firefly = num1 - num2;
                    break;
                case "multiplication":
                    firefly = num1 * num2;
                    break;
                case "division":
                    if (num2 != 0) 
                        firefly = (double) num1 / num2;
                    break;
                default:
                    System.out.println("Error: Invalid operator!");
                    break;
            }

            byte buff4[] = new byte[256];
            String result = String.valueOf(firefly);
            buff4 = result.getBytes();
            InetAddress addcl = b.getAddress(); // a and b are same address and portnumber.
            int portcl = a.getPort();
            DatagramPacket leo = new DatagramPacket(buff4, buff4.length, addcl, portcl);
            serverSocket.send(leo);
            serverSocket.close();

        } catch (IOException e) {
        }
    }
}