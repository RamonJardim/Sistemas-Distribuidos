package data;

import java.util.Dictionary;
import java.util.HashMap;

public abstract class Neighbors {
    public static final HashMap<String, String> NEIGHBORS = new HashMap() {{
        put("V", "localhost");
        put("W", "localhost");
        put("X", "localhost");
        put("Y", "localhost");
        put("Z", "localhost");
    }};
    public static final int PORT = 9876;

    public static String getPeerIP(String peerName){
        return NEIGHBORS.get(peerName);
    }
}
