package process;

import data.Neighbors;
import models.PeerInfo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class Sender {
    public static void send(String IP, PeerInfo info, int port) {
        DatagramSocket socketClient = null;
        InetAddress ipAddress;
        try {
            socketClient = new DatagramSocket();
            ipAddress = InetAddress.getByName(IP);

            byte[] serializedInfo;
            DatagramPacket packet;

            serializedInfo = Serializer.convertToBytes(info);
            packet = new DatagramPacket(serializedInfo, serializedInfo.length, ipAddress, port);
            socketClient.send(packet);
            System.out.println("\n-------\nSent packet to: " + IP + "\n-------\n");

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if(socketClient != null)
                socketClient.close();
        }
    }
}
