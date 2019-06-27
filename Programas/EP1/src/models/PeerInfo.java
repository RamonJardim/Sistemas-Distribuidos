package models;

import java.util.Date;
import java.util.List;

import data.DAO;
import data.InfoNumber;

public class PeerInfo {
    private List<Metadata> filesInfo;
    private long infoNumber;    // Número sequencial, quanto maior, mais novo.

    public PeerInfo(List<Metadata> actualFiles){
        this.filesInfo = actualFiles;
        infoNumber = InfoNumber.getActualInfoNumber();
    }

    public Date getInfoNumber() {
        return this.infoNumber;
    }

    public List<Metadata> getFilesInfo(){
        return this.filesInfo;
    }
}