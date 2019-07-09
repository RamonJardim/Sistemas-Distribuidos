package threads;

import data.DAO;
import data.Neighbors;
import models.PeerInfo;
import process.Randomizer;
import process.Sender;
import process.Serializer;

public class SelfStateSender extends Thread { // Este representa T2

    @Override
    public void run() {
        try {
            sendInfo();
        } catch (Exception e) {
            System.out.println("Erro em T2: ");
            e.printStackTrace();
        }
    }

    private void sendInfo() throws Exception {
        String peerToSendName;
        String peerToSendIP;
        PeerInfo infoToSend;

        while (true) {
            Thread.sleep(500);
            peerToSendName = Randomizer.getRandomPeer(DAO.getPeerName());
            peerToSendIP = Neighbors.getPeerIP(peerToSendName);
            infoToSend = DAO.getDAO().getInternInfo();
            Sender.send(peerToSendIP, infoToSend, Neighbors.PORT);

            System.out.println(String.format("Console peer %s: Thread T2 - Envio do estado: %d, arquivos: (%s)" +
                    " por gossip ao peer: %s", DAO.getPeerName(), infoToSend.getInfoNumber(),
                    Serializer.listFiles(infoToSend.getFilesInfo()), peerToSendName));
        }
    }
}