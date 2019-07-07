package models;

import java.util.Date;
import java.util.List;

import data.DAO;

public class PeerInfo {
    private String peerName;
    private List<Metadata> filesInfo;
    private long infoNumber;    // Número sequencial, quanto maior, mais novo.

    public PeerInfo(String peerName, long infoNumber, List<Metadata> actualFiles){
        this.filesInfo = actualFiles;
        this.infoNumber = infoNumber;
        this.peerName = peerName;
    }

    public long getInfoNumber() {
        return this.infoNumber;
    }

    public String getPeerName() {
        return peerName;
    }

    public void setPeerName(String peerName) {
        this.peerName = peerName;
    }

    public int getFilesCount(){
        return this.filesInfo.size();
    }

    public List<Metadata> getFilesInfo(){
        return this.filesInfo;
    }

}