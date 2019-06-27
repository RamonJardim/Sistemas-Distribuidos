package entities;

import models.PeerInfo;
import threads.MetadataReader;

public class Peer {
    public static void main(String[] args) {
        String peerName = "X";

        MetadataReader T1 = new MetadataReader(peerName);
        T1.start();
    }
}