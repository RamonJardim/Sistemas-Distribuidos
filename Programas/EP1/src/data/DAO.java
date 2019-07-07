package data;

import models.Metadata;
import models.PeerInfo;
import java.util.HashMap;
import java.util.List;

public class DAO {

    private static DAO dao = null;
    private static String peerName;
    private static long infoNumber = 0;

    public static DAO getDAO(){ return dao == null ? new DAO() : dao; }

    private PeerInfo internInfo;
    private HashMap<String, PeerInfo> strangersInfo;

    public static void setPeerName(String peerName) {
        DAO.peerName = peerName;
    }

    public PeerInfo getInternInfo() {
        return internInfo;
    }

    public void setInternInfo(List<Metadata> files) {
        this.internInfo = new PeerInfo(peerName, infoNumber++, files);
        this.internInfo = internInfo;
    }

    public void setOutsideInfo(String peerName, PeerInfo peerInfo){
        strangersInfo.put(peerName, peerInfo);
    }

    public PeerInfo getOutsideInfo(String peerName){
        return strangersInfo.get(peerName);
    }
}
