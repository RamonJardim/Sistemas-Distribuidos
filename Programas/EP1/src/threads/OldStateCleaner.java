package threads;

import data.DAO;
import models.PeerInfo;

import java.util.Map;

public class OldStateCleaner extends Thread { //T4
    @Override
    public void run() {
        cleanInfo();
    }

    private void cleanInfo() {
        while (true) {
            Map<String, PeerInfo> foreignersInfo = DAO.getDAO().getForeignsMap();
            foreignersInfo.forEach((String k, PeerInfo v) -> {
                if (System.currentTimeMillis() - foreignersInfo.get(k).getReceiveMoment() > 1500 && !k.equals(DAO.getPeerName())) {
                    System.out.println(String.format("Console peer %s: Thread T4 – eliminando estado do peer %s", DAO.getPeerName(), k));
                    DAO.getDAO().removePeerInfo(k);
                }
            });
        }
    }
}
