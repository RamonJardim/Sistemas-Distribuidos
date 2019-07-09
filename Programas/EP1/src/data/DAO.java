package data;

import exceptions.InfoException;
import exceptions.OutdatedInfoException;
import exceptions.RepeatedInfoException;
import models.Metadata;
import models.PeerInfo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DAO {

    private static DAO dao = null;

    private static String peerName;
    private static long infoNumber = 0;

    private DAO(){
        //foreignersInfo.put("V", new PeerInfo("V",0, new ArrayList<Metadata>(), "V"));
    }

    private PeerInfo internInfo;
    private Map<String, PeerInfo> foreignersInfo = new ConcurrentHashMap<String, PeerInfo>();

    public static DAO getDAO(){
        if(dao == null)
            return dao = new DAO();
        else
            return dao;
    }

    public static void setPeerName(String peerName) {
        DAO.peerName = peerName;
    }

    public PeerInfo getInternInfo() {
        return internInfo;
    }

    public void setInternInfo(List<Metadata> files) {
        this.internInfo = new PeerInfo(peerName, infoNumber++, files, DAO.getPeerName());
    }

    public static String getPeerName() {
        return peerName;
    }

    public void setForeignersInfo(PeerInfo peerInfo) throws InfoException {
        if(foreignersInfo.containsKey(peerInfo.getPeerName())) {
            if (foreignersInfo.get(peerInfo.getPeerName()).compareTo(peerInfo) == 0) {
                throw new RepeatedInfoException(peerInfo, "Informação já registrada");
            }

            if (foreignersInfo.get(peerInfo.getPeerName()).compareTo(peerInfo) > 0) {
                throw new OutdatedInfoException(peerInfo, "Informação antiga");
            }
        }
        peerInfo.setReceiveMoment(System.currentTimeMillis());
        foreignersInfo.put(peerName, peerInfo);
    }

    public PeerInfo getForeignInfo(String peerName){
        return foreignersInfo.getOrDefault(peerName, null);
    }

    public void removePeerInfo(String peerName) {
        foreignersInfo.remove(peerName);
    }

    public Map<String, PeerInfo> getForeignsMap() {
        return foreignersInfo;
    }

    public static String getFileFolderPath(){
        return System.getProperty("os.name").contains("Windows") ? "./files/" : "../files/";
    }
}