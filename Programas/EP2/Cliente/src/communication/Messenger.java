package communication;

import data.Neighbors;
import data.Randomizer;

import java.net.SocketTimeoutException;

public class Messenger {
    public static String beginFlood(String fileName) throws SocketTimeoutException {
        String response = "";
        try {
            MessageSender.sendMessage(Neighbors.getPeerIP(Randomizer.getRandomPeer()), Neighbors.PORT, fileName);
            response = Receiver.receiveMessage();
        } catch(SocketTimeoutException ste) {
            throw ste;
        } catch(Exception e) {
            System.out.println("Erro no Messenger: ");
            e.printStackTrace();
        }
        return response;
    }
}