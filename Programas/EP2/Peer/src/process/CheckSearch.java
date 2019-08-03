package process;

import data.MetadataInfoDAO;
import data.Neighbors;
import data.SearchDAO;
import models.SearchInfo;

public class CheckSearch {

    private static SearchDAO searchDAO = SearchDAO.getSearchDAO();

    public static void checkSearchInfo(SearchInfo s) {
        if(searchDAO.checkIfSeen(s)) {
            printDuplicate(s);
        } else if(s.getTTL() <= 0) {
            printTTLExpired(s);
        } else {
            //TODO: VERIFICAR SE TENHO O ARQUIVO
            searchDAO.addToSeenSearches(s);
            s.decrementTTL();
            Sender.send(Randomizer.getRandomPeer(MetadataInfoDAO.getPeerName()), s, Neighbors.FLOOD_PORT);
        }
    }

    private static void printTTLExpired(SearchInfo s) {
        System.out.println("Console peer " + MetadataInfoDAO.getPeerName() + ": recebendo pesquisa " + s.getFileName() +
                ", TTL=ZERO NÃO ENCAMINHO.");
    }

    private static void printDuplicate(SearchInfo s) {
        System.out.println("Console peer " + MetadataInfoDAO.getPeerName() + ": recebendo pesquisa " + s.getFileName() +
                ", MSG DUPLICADA NÃO ENCAMINHO.");
    }
}