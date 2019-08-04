package process;

import data.Neighbors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Randomizer {
    public static String getRandomPeer(String... excludes){
        Random random = new Random();
        ArrayList<String> peers = new ArrayList<String>();
        List<String> excludesList = Arrays.asList(excludes);
        String peer;
        peers.addAll(Neighbors.NEIGHBORS.keySet());
        peers.removeAll(excludesList);

//        if(peers.equals(excludesList))
//            throw new Exception("All peers excluded from being randomized.");

        //do{
        peer = peers.get(random.nextInt(peers.size()));
        //} while(excludesList.contains(peer));

        return peer;
    }

//    public static String getFileName() {
//        return BigInteger.probablePrime(50, new Random()).toString(Character.MAX_RADIX);
//    }
}
