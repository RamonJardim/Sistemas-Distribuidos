package data;

import models.PeerInfo;

import java.util.HashMap;

public class DAO {

    private static DAO dao = null;
    private DAO(){}

    public static DAO getDAO(){ return dao == null ? new DAO() : dao; }

    private PeerInfo internInfo;
    private HashMap<String, PeerInfo> strangersInfo;

    public PeerInfo getInternInfo() {
        return internInfo;
    }

    public void setInternInfo(PeerInfo internInfo) {
        this.internInfo = internInfo;
    }

    public void setOutsideInfo(String peerName, PeerInfo peerInfo){
        strangersInfo.put(peerName, peerInfo);
    }

    public PeerInfo getOutsideInfo(String peerName){
        return strangersInfo.get(peerName);
    }
}
