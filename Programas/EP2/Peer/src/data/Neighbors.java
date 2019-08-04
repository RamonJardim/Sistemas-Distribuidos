package data;

import java.util.HashMap;

public abstract class Neighbors {
    public static final HashMap<String, String> NEIGHBORS = new HashMap<String, String>() {{
        put("A", "54.208.74.87");
        put("B", "54.158.237.235");
        put("C", "54.89.99.34");
        put("D", "3.82.246.240");
        put("E", "3.86.35.100");
        put("F", "3.84.251.158");
        put("G", "54.82.44.133");
        put("H", "18.207.187.65");
        put("I", "3.83.158.220");
        put("J", "3.90.11.149");
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
