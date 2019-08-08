package communication;

import data.Serializer;
import models.ClientSearch;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class MessageSender {
    public static void sendMessage(String IP, int port, ClientSearch toSend) {
        DatagramSocket socketClient = null;
        InetAddress ipAddress;
        try {
            socketClient = new DatagramSocket();
            ipAddress = InetAddress.getByName(IP);

            byte[] serializedInfo;
            DatagramPacket packet;

            serializedInfo = Serializer.convertToBytes(toSend);
            packet = new DatagramPacket(serializedInfo, serializedInfo.length, ipAddress, port);
            socketClient.send(packet);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if(socketClient != null)
                socketClient.close();
        }
    }
}