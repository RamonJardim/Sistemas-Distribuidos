package threads.search;

import data.Neighbors;
import models.ClientSearch;
import models.SearchInfo;
import process.CheckSearch;
import process.Serializer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class FloodReceiver extends Thread {
    @Override
    public void run() {
        try {
            receiveInfo();
        } catch (Exception e) {
            System.out.println("Erro no FloodReceiver: ");
            e.printStackTrace();
        }
    }

    private void receiveInfo() throws Exception {
        while(true) {
            try(DatagramSocket serverSocket = new DatagramSocket(Neighbors.FLOOD_PORT)){
                byte[] receivedData = new byte[16384];

                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                serverSocket.receive(receivedPacket);
                InetAddress iPAddress = receivedPacket.getAddress();
                SearchInfo searchInfo = (SearchInfo) (Serializer.convertFromBytes(receivedPacket.getData()));

                SearchInfo search = new SearchInfo(searchInfo.getClientIP(), searchInfo.getClientPort(),
                        searchInfo.getFileName(), searchInfo.getID());

                CheckSearch.checkSearchInfo(search);
            }
        }
    }
}
