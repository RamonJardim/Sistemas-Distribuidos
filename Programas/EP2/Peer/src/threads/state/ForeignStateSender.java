package threads.state;

import data.MetadataInfoDAO;
import data.Neighbors;
import models.PeerInfo;
import process.Randomizer;
import process.Sender;

public class ForeignStateSender extends Thread { // Este representa T3

    @Override
    public void run(){
        try {
            sendInfo();
        } catch (Exception e) {
            System.out.println("Erro em T3: ");
            e.printStackTrace();
        }
    }

    private void sendInfo() throws Exception {
        String infoPeerToSendName;

        String peerToSendName;
        String peerToSendIP;
        PeerInfo infoToSend;

        while(true){
            do {
                Thread.sleep(500);
                infoPeerToSendName = Randomizer.getRandomPeer(MetadataInfoDAO.getPeerName());
                infoToSend = MetadataInfoDAO.getDAO().getForeignInfo(infoPeerToSendName);
                //System.out.println("Enviar info de: " + infoPeerToSendName + " " + infoToSend);
            } while(infoToSend == null);

            infoToSend.setPeerSender(MetadataInfoDAO.getPeerName());

            peerToSendName = Randomizer.getRandomPeer(MetadataInfoDAO.getPeerName(), infoPeerToSendName, infoToSend.getPeerSender());
            peerToSendIP = Neighbors.getPeerIP(peerToSendName);
            //System.out.println("Enviar para: " + peerToSendName + " " + peerToSendIP);

            Sender.send(peerToSendIP, infoToSend, Neighbors.STATE_EXCHANGE_PORT);

            //System.out.println(String.format("Console peer %s: Thread T3 - Envio do estado: %d do peer %s, arquivos: (%s) " +
            //    "por gossip ao peer: %s", MetadataInfoDAO.getPeerName(), infoToSend.getInfoNumber(), infoToSend.getPeerName(),
            //    Serializer.listFiles(infoToSend.getFilesInfo()), peerToSendName));
        }
    }
}
