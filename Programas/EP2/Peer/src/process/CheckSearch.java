package process;

import data.MetadataInfoDAO;
import data.Neighbors;
import data.SearchDAO;
import models.SearchInfo;
import threads.search.Uploader;

public class CheckSearch {

    private static SearchDAO searchDAO = SearchDAO.getSearchDAO();

    public static void checkSearchInfo(SearchInfo s) {
        if(searchDAO.checkIfSeen(s)) {
            printDuplicate(s);
        } else if(MetadataInfoDAO.getDAO().fileIsPresent(s.getFileName())){
            printHaveFile(s);
            sendResponse(s);
        } else if(s.getTTL() <= 0) {
            printTTLExpired(s);
        } else {
            String peerToSend = Randomizer.getRandomPeer(MetadataInfoDAO.getPeerName());
            printFileNotFound(s, peerToSend);
            searchDAO.addToSeenSearches(s);
            s.decrementTTL();
            Sender.send(Neighbors.getPeerIP(peerToSend), s, Neighbors.FLOOD_PORT);
        }
    }

    private static void sendResponse(SearchInfo s) {
        Uploader download = new Uploader(s);
        download.start();
        Sender.send(s.getClientIP(), MetadataInfoDAO.getPeerName(), Neighbors.RESPONSE_PORT);
    }

    private static void printFileNotFound(SearchInfo s, String peerToSend) {
        System.out.println("Console peer " + MetadataInfoDAO.getPeerName() + ": recebendo pesquisa \"" + s.getFileName() +
                "\", NÃO tenho o arquivo, encaminho para " + peerToSend + ".");
    }

    private static void printHaveFile(SearchInfo s) {
        System.out.println("Console peer " + MetadataInfoDAO.getPeerName() +": recebendo pesquisa \"" + s.getFileName() +
                "\", tenho o arquivo " + s.getFileName() + " no meu estado.");
    }

    private static void printTTLExpired(SearchInfo s) {
        System.out.println("Console peer " + MetadataInfoDAO.getPeerName() + ": recebendo pesquisa \"" + s.getFileName() +
                "\", TTL=ZERO NÃO ENCAMINHO.");
    }

    private static void printDuplicate(SearchInfo s) {
        System.out.println("Console peer " + MetadataInfoDAO.getPeerName() + ": recebendo pesquisa \"" + s.getFileName() +
                "\", MSG DUPLICADA NÃO ENCAMINHO.");
    }
}