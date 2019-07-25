package data;

import java.util.HashMap;

public abstract class Neighbors {
    public static final HashMap<String, String> NEIGHBORS = new HashMap<String, String>() {{
        put("A", "18.231.112.127");
        put("B", "18.231.112.127");
        put("C", "18.231.112.127");
        put("D", "18.231.112.127");
        put("E", "18.231.112.127");
        put("F", "18.231.112.127");
        put("G", "18.231.112.127");
        put("H", "18.231.112.127");
        put("I", "18.231.112.127");
        put("J", "18.231.112.127");
        put("K", "18.231.112.127");
        put("L", "18.231.112.127");
    }};
    public static final int PORT = 9876;

    public static String getPeerIP(String peerName){
        return NEIGHBORS.get(peerName);
    }
}
