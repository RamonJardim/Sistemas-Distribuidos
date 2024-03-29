package threads;

import data.DAO;
import data.Neighbors;
import models.PeerInfo;
import process.Randomizer;
import process.Sender;
import process.Serializer;

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
                infoPeerToSendName = Randomizer.getRandomPeer(DAO.getPeerName());
                infoToSend = DAO.getDAO().getForeignInfo(infoPeerToSendName);
                //System.out.println("Enviar info de: " + infoPeerToSendName + " " + infoToSend);
            } while(infoToSend == null);

            infoToSend.setPeerSender(DAO.getPeerName());

            peerToSendName = Randomizer.getRandomPeer(DAO.getPeerName(), infoPeerToSendName, infoToSend.getPeerSender());
            peerToSendIP = Neighbors.getPeerIP(peerToSendName);
            //System.out.println("Enviar para: " + peerToSendName + " " + peerToSendIP);

            Sender.send(peerToSendIP, infoToSend, Neighbors.PORT);

            System.out.println(String.format("Console peer %s: Thread T3 - Envio do estado: %d do peer %s, arquivos: (%s) " +
                "por gossip ao peer: %s", DAO.getPeerName(), infoToSend.getInfoNumber(), infoToSend.getPeerName(),
                Serializer.listFiles(infoToSend.getFilesInfo()), peerToSendName));
        }
    }
}
