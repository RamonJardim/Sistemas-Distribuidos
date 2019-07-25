package communication;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class MessageSender {
    public static void sendMessage(String IP, int port, String toSend) {
        DatagramSocket socketClient = null;
        InetAddress ipAddress;
        try {
            socketClient = new DatagramSocket();
            ipAddress = InetAddress.getByName(IP);

            byte[] serializedInfo;
            DatagramPacket packet;

            serializedInfo = toSend.getBytes();
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
