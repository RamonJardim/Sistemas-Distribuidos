package exceptions;

import entities.Peer;
import models.PeerInfo;

public class OutdatedInfoException extends InfoException {
    public OutdatedInfoException(PeerInfo peerInfo, String message){
        super(peerInfo, message);
    }

    public OutdatedInfoException(PeerInfo peerInfo){
        super(peerInfo, "");
    }
}
