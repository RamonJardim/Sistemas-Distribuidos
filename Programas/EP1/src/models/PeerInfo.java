package models;

import java.util.Date;
import java.util.List;

public class PeerInfo {
    private List<Metadata> filesInfo;
    private Date infoCreationTime;

    public PeerInfo(){
        infoCreationTime = new Date(System.currentTimeMillis());
    }

    public Date getInfoCreationTime() {
        return infoCreationTime;
    }
}