package data;

import java.util.HashMap;

public abstract class Neighbors {
    public static final HashMap<String, String> NEIGHBORS = new HashMap<String, String>() {{
        put("V", "18.231.112.127");
        put("W", "52.67.107.28");
        put("X", "18.228.136.98");
        put("Y", "18.231.113.5");
        put("Z", "52.67.253.30");
    }};
    public static final int PORT = 9876;

    public static String getPeerIP(String peerName){
        return NEIGHBORS.get(peerName);
    }
}
