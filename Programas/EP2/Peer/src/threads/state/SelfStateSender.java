package threads.state;

import data.MetadataInfoDAO;
import data.Neighbors;
import models.PeerInfo;
import process.Randomizer;
import process.Sender;

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
            peerToSendName = Randomizer.getRandomPeer(MetadataInfoDAO.getPeerName());
            peerToSendIP = Neighbors.getPeerIP(peerToSendName);
            infoToSend = MetadataInfoDAO.getDAO().getInternInfo();
            Sender.send(peerToSendIP, infoToSend, Neighbors.STATE_EXCHANGE_PORT);

            //System.out.println(String.format("Console peer %s: Thread T2 - Envio do estado: %d, arquivos: (%s)" +
            //        " por gossip ao peer: %s", MetadataInfoDAO.getPeerName(), infoToSend.getInfoNumber(),
            //        Serializer.listFiles(infoToSend.getFilesInfo()), peerToSendName));
        }
    }
}