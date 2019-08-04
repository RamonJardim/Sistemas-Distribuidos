package communication;

import data.DAO;
import data.Neighbors;
import models.ClientSearch;

import java.net.SocketTimeoutException;

public abstract class Messenger {
    public static String beginFlood(String fileName, String peerName) throws SocketTimeoutException {
        String response = "";
        try {
            ClientSearch cs = new ClientSearch(fileName, DAO.getDao().getAndIncrementActualID());
            MessageSender.sendMessage(Neighbors.getPeerIP(peerName), Neighbors.SEARCH_PORT, cs);
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