package data;

import java.util.HashMap;

public abstract class Neighbors {
    public static final HashMap<String, String> NEIGHBORS = new HashMap<String, String>() {{
        put("A", "3.91.181.190");
        put("B", "34.227.25.157");
        put("C", "34.207.160.141");
        put("D", "34.201.67.183");
        put("E", "52.91.172.144");
        put("F", "3.83.91.22");
        put("G", "52.206.228.157");
        put("H", "34.205.146.94");
        put("I", "18.205.27.112");
        put("J", "18.208.165.130");
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
