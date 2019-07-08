package models;

import java.io.Serializable;
import java.util.List;

public class PeerInfo implements Serializable, Comparable<PeerInfo> {
    private String peerName;
    private List<Metadata> filesInfo;
    private long infoNumber;    // Número sequencial, quanto maior, mais novo.
    private long receiveMoment;


    private String peerSender;

    public PeerInfo(String peerName, long infoNumber, List<Metadata> actualFiles, String peerSender){
        this.filesInfo = actualFiles;
        this.infoNumber = infoNumber;
        this.peerName = peerName;
        this.peerSender = peerSender;
    }

    public String getPeerSender() {
        return peerSender;
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

    public void setPeerSender(String peerSender) {
        this.peerSender = peerSender;
    }

    @Override
    public int compareTo(PeerInfo o) {
        if(this.infoNumber > o.infoNumber)
            return 1;
        else if(this.infoNumber < o.infoNumber)
            return -1;
        else
            return 0;
    }

    public void setReceiveMoment(long receiveMoment) {
        this.receiveMoment = receiveMoment;
    }

    public long getReceiveMoment() {
        return receiveMoment;
    }
}