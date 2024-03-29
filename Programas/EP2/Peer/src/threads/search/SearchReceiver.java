package threads.search;

import data.Neighbors;
import models.ClientSearch;
import models.SearchInfo;
import process.CheckSearch;
import process.Serializer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SearchReceiver extends Thread {
    @Override
    public void run() {
        try {
            receiveInfo();
        } catch (Exception e) {
            System.out.println("Erro no SearchReceiver: ");
            e.printStackTrace();
        }
    }

    private void receiveInfo() throws Exception {
        while(true) {
            try(DatagramSocket serverSocket = new DatagramSocket(Neighbors.SEARCH_PORT)){
                byte[] receivedData = new byte[16384];

                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                serverSocket.receive(receivedPacket);
                InetAddress ipAddress = receivedPacket.getAddress();
                ClientSearch clientSearch = (ClientSearch) (Serializer.convertFromBytes(receivedPacket.getData()));

                SearchInfo search = new SearchInfo(ipAddress.getHostAddress(), Neighbors.RESPONSE_PORT,
                        clientSearch.getFileName(), clientSearch.getID());

                CheckSearch.checkSearchInfo(search);

            }
        }
    }
}
