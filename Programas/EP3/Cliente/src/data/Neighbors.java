package data;

import java.util.HashMap;

public abstract class Neighbors {
    public static final HashMap<String, String> NEIGHBORS = new HashMap<String, String>() {{
        put("A", "172.31.94.222");
        put("B", "172.31.86.123");
        put("C", "172.31.84.211");
        put("D", "172.31.90.23");
        put("E", "172.31.90.169");
        put("F", "172.31.87.198");
        put("G", "172.31.86.205");
        put("H", "172.31.84.220");
        put("I", "172.31.88.144");
        put("J", "172.31.84.146");
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
