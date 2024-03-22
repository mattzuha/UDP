import java.io.*;
import java.net.*;

public class Ex2Server {

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

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
            String data3 = new String(c.getData()).trim();
            int num3 = Integer.parseInt(data3);

            int firefly = lcm(lcm(num1, num2), num3);
            
            byte buff4[] = new byte[256];
            String result = String.valueOf(firefly);
            buff4 = result.getBytes();
            InetAddress addcl = b.getAddress(); //a and b are same address and portnumber. 
            int portcl = a.getPort();
            DatagramPacket leo = new DatagramPacket(buff4, buff4.length, addcl, portcl);
            serverSocket.send(leo);
            serverSocket.close();

        } catch (IOException e) {
        }
    }
}