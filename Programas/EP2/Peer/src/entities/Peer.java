package entities;

import data.DAO;
import threads.*;

public class Peer {
    public static void main(String[] args) {
        String peerName = args[0];
        DAO.setPeerName(peerName);
        Receiver T0 = new Receiver();
        SelfStateReader T1 = new SelfStateReader();
        SelfStateSender T2 = new SelfStateSender();
        ForeignStateSender T3 = new ForeignStateSender();
        OldStateCleaner T4 = new OldStateCleaner();
        FileModifier modifier = new FileModifier();
        T0.start();
        T1.start();
        T2.start();
        T3.start();
        T4.start();
        modifier.start();
    }
}