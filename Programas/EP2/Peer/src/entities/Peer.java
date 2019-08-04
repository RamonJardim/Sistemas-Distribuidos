package entities;

import data.MetadataInfoDAO;
import data.SearchDAO;
import threads.search.FloodReceiver;
import threads.search.SearchReceiver;
import threads.state.*;

public class Peer {
    public static void main(String[] args) {
        String peerName = args[0];
        String TTL = args[1];

        MetadataInfoDAO.setPeerName(peerName);
        SearchDAO.setTTL(Integer.parseInt(TTL));

        StateReceiver T0 = new StateReceiver();
        SelfStateReader T1 = new SelfStateReader();
        SelfStateSender T2 = new SelfStateSender();
        ForeignStateSender T3 = new ForeignStateSender();
        OldStateCleaner T4 = new OldStateCleaner();

        SearchReceiver sr = new SearchReceiver();
        FloodReceiver fr = new FloodReceiver();

        T0.start();
        T1.start();
        T2.start();
        T3.start();
        T4.start();

        sr.start();
        fr.start();
    }
}