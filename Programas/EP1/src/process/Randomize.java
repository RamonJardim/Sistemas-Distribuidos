package process;

import data.DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Randomize {
    public static String getRandomPeer(String... excludes) throws Exception {
        ArrayList<String> peers = new ArrayList();
        List<String> excludesList = Arrays.asList(excludes);
        String peer;
        peers.add("V");
        peers.add("W");
        peers.add("X");
        peers.add("Y");
        peers.add("Z");

        if(peers.equals(excludesList))
            throw new Exception("All peers excluded from being randomized.");

        do{
            peer = peers.get((int)(Math.random()*4));
        } while(excludesList.contains(peer));

        return peer;
    }
}
