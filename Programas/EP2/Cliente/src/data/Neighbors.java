package data;

import java.util.HashMap;

public abstract class Neighbors {
    public static final HashMap<String, String> NEIGHBORS = new HashMap<String, String>() {{
        put("A", "172.0.0.1");
        put("B", "172.0.0.1");
        put("C", "172.0.0.1");
        put("D", "172.0.0.1");
        put("E", "172.0.0.1");
        put("F", "172.0.0.1");
        put("G", "172.0.0.1");
        put("H", "172.0.0.1");
        put("I", "172.0.0.1");
        put("J", "172.0.0.1");
        put("K", "172.0.0.1");
        put("L", "172.0.0.1");
    }};

    public static final int SEARCH_PORT = 7654;
    public static final int DOWNLOAD_PORT = 8765;

    public static String getPeerIP(String peerName){
        return NEIGHBORS.get(peerName);
    }
}
