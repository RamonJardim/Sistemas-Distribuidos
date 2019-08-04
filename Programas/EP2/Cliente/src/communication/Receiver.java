package communication;

import data.Neighbors;
import data.Serializer;

import java.net.*;

public abstract class Receiver {
    public static String receiveMessage() throws SocketTimeoutException {
        return receiveDataGram();
    }

    private static String receiveDataGram() throws SocketTimeoutException {
        String receivedString = "";
        try(DatagramSocket serverSocket = new DatagramSocket(Neighbors.RESPONSE_PORT)) {
            byte[] receivedData = new byte[16384];

            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            serverSocket.setSoTimeout(5000);

            System.out.println("Espera para receber");

            serverSocket.receive(receivedPacket);
            InetAddress ipAddress = receivedPacket.getAddress();
            receivedString = (String) (Serializer.convertFromBytes(receivedPacket.getData()));
        } catch(SocketTimeoutException ste) {
            throw ste;
        } catch (Exception e) {
            System.out.println("Erro no receiver: ");
            e.printStackTrace();
        }
        return receivedString;
    }
}