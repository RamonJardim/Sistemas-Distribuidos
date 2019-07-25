package exceptions;

import models.PeerInfo;

public abstract class InfoException extends Exception {
    private PeerInfo peerInfo;

    public InfoException(PeerInfo peerInfo, String message){
        super(message);
        this.peerInfo = peerInfo;
    }

    public InfoException(PeerInfo peerInfo){
        this(peerInfo, "");
    }

    public PeerInfo getPeerInfo() {
        return peerInfo;
    }
}