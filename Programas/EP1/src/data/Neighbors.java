package data;

import java.util.HashMap;

public abstract class Neighbors {
    public static final HashMap<String, String> NEIGHBORS = new HashMap() {{
        put("V", "54.207.88.140");
        put("W", "18.228.116.163");
        put("X", "18.228.136.201");
        put("Y", "18.228.152.193");
        put("Z", "18.231.172.67");
    }};
    public static final int PORT = 9876;

    public static String getPeerIP(String peerName){
        return NEIGHBORS.get(peerName);
    }
}
