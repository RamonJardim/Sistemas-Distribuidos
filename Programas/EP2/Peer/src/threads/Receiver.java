package threads;

import data.DAO;
import data.Neighbors;
import exceptions.OutdatedInfoException;
import exceptions.RepeatedInfoException;
import models.PeerInfo;
import process.Serializer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receiver extends Thread {
    @Override
    public void run() {
        try {
            receiveInfo();
        } catch (Exception e) {
            System.out.println("Erro no receiver: ");
            e.printStackTrace();
        }
    }

    private void receiveInfo() throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(Neighbors.PORT);
        while(true) {
            try {
                byte[] receivedData = new byte[16384];

                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                serverSocket.receive(receivedPacket);
                InetAddress iPAddress = receivedPacket.getAddress();
                PeerInfo receivedMetadata = (PeerInfo) (Serializer.convertFromBytes(receivedPacket.getData()));

                DAO.getDAO().insertForeignInfo(receivedMetadata);

                System.out.println(String.format("Console peer %s: Recebimento do estado %d do peer %s, " +
                                "arquivos: (%s) por gossip vindo do peer %s.", DAO.getPeerName(), receivedMetadata.getInfoNumber(),
                        receivedMetadata.getPeerName(), Serializer.listFiles(receivedMetadata.getFilesInfo()), receivedMetadata.getPeerSender()));

            } catch (OutdatedInfoException e) {
                System.out.println(String.format("Console peer %s: Recebimento ANTIGO do estado %d do peer %s (atual: %d), " +
                                "arquivos: (%s) por gossip vindo do peer %s.", DAO.getPeerName(), e.getPeerInfo().getInfoNumber(),
                        e.getPeerInfo().getPeerName(), DAO.getDAO().getForeignInfo(e.getPeerInfo().getPeerName()).getInfoNumber(), Serializer.listFiles(e.getPeerInfo().getFilesInfo()), e.getPeerInfo().getPeerSender()));
            } catch (RepeatedInfoException e) {
                System.out.println(String.format("Console peer %s: recebimento DUPLICADO do estado %d do peer %s, arquivos: (%s)" +
                                " vindo do peer %s", DAO.getPeerName(), e.getPeerInfo().getInfoNumber(), e.getPeerInfo().getPeerName(), Serializer.listFiles(e.getPeerInfo().getFilesInfo()),
                        e.getPeerInfo().getPeerSender()));
            }
        }
    }
}
