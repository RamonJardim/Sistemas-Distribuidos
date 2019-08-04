package data;

import data.Neighbors;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Randomizer {
    public static String getRandomPeer(String... excludes) throws Exception {
        ArrayList<String> peers = new ArrayList<String>();
        List<String> excludesList = Arrays.asList(excludes);
        String peer;
        peers.addAll(Neighbors.NEIGHBORS.keySet());
        peers.removeAll(excludesList);

        if(peers.equals(excludesList))
            throw new Exception("All peers excluded from being randomized.");

        peer = peers.get((int)(Math.random()*peers.size()));

        return peer;
    }
}
