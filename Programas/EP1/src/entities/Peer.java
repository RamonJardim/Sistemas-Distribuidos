package entities;

import data.DAO;
import models.PeerInfo;
import threads.MetadataReader;

public class Peer {
    public static void main(String[] args) {
        String peerName = args[0];
        DAO.setPeerName(peerName);
        MetadataReader T1 = new MetadataReader();
        T1.start();
    }
}