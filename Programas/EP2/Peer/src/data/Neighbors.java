package data;

import java.util.HashMap;

public abstract class Neighbors {
    public static final HashMap<String, String> NEIGHBORS = new HashMap<String, String>() {{
        put("A", "192.168.0.21");
        put("B", "192.168.0.16");
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
    public static final int STATE_EXCHANGE_PORT = 9876;
    public static final int DOWNLOAD_PORT = 8765;
    public static final int SEARCH_PORT = 7654;
    public static final int FLOOD_PORT = 6543;
    public static final int RESPONSE_PORT = 5432;

    public static String getPeerIP(String peerName){
        return NEIGHBORS.get(peerName);
    }
}
