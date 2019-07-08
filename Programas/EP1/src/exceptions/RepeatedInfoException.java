package exceptions;

import models.PeerInfo;

public class RepeatedInfoException extends InfoException {
    public RepeatedInfoException(PeerInfo peerInfo, String message){
        super(peerInfo, message);
    }

    public RepeatedInfoException(PeerInfo peerInfo){
        super(peerInfo, "");
    }
}
