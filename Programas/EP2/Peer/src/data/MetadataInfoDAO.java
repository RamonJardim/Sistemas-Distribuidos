package data;

import exceptions.InfoException;
import exceptions.OutdatedInfoException;
import exceptions.RepeatedInfoException;
import models.Metadata;
import models.PeerInfo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MetadataInfoDAO {

    private static MetadataInfoDAO metadataInfoDao = null;

    private static String peerName;
    private static long infoNumber = 0;

    private MetadataInfoDAO(){}

    private PeerInfo internInfo;
    private Map<String, PeerInfo> foreignersInfo = new ConcurrentHashMap<String, PeerInfo>();

    public static MetadataInfoDAO getDAO(){
        if(metadataInfoDao == null)
            return metadataInfoDao = new MetadataInfoDAO();
        else
            return metadataInfoDao;
    }

    public static void setPeerName(String peerName) {
        MetadataInfoDAO.peerName = peerName;
    }

    public PeerInfo getInternInfo() {
        return internInfo;
    }

    public void setInternInfo(List<Metadata> files) {
        this.internInfo = new PeerInfo(peerName, infoNumber++, files, MetadataInfoDAO.getPeerName());
    }

    public static String getPeerName() {
        return peerName;
    }

    public void insertForeignInfo(PeerInfo peerInfo) throws InfoException {
        if(foreignersInfo.containsKey(peerInfo.getPeerName())) {
            if (foreignersInfo.get(peerInfo.getPeerName()).compareTo(peerInfo) == 0) {
                throw new RepeatedInfoException(peerInfo, "Informação já registrada");
            }

            if (foreignersInfo.get(peerInfo.getPeerName()).compareTo(peerInfo) > 0) {
                throw new OutdatedInfoException(peerInfo, "Informação antiga");
            }
        }
        peerInfo.setReceiveMoment(System.currentTimeMillis());
        foreignersInfo.put(peerInfo.getPeerName(), peerInfo);
    }

    public PeerInfo getForeignInfo(String peerName){
    return foreignersInfo.getOrDefault(peerName, null);
    }

    public void removePeerInfo(String peerName) {
        foreignersInfo.remove(peerName);
    }

    public Map<String, PeerInfo> getForeignersMap() {
        return foreignersInfo;
    }

    public static String getFileFolderPath(){
        return System.getProperty("os.name").contains("Windows") ? "./files/" : "../files/";
    }
}