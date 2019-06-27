package data;

import models.PeerInfo;

import java.util.HashMap;

public class DAO {

    private static DAO dao = null;
    private DAO(){}

    public DAO getDAO(){ return dao == null ? new DAO() : dao; }

    private PeerInfo internInfo;
    private HashMap<String, PeerInfo> strangersInfo;

    public PeerInfo getInternInfo() {
        return internInfo;
    }

    public void setInternInfo(PeerInfo internInfo) {
        this.internInfo = internInfo;
    }
}
