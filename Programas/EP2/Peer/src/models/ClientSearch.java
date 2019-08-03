package models;

import java.io.Serializable;

public class ClientSearch implements Serializable {
    private String clientIP;
    private int clientPort;
    private String fileName;

    private long ID;

    public ClientSearch(String clientIP, int clientPort, String fileName, long ID){
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.fileName = fileName;

        this.ID = ID;
    }

    public String getFileName(){
        return this.fileName;
    }

    public int getClientPort(){
        return this.clientPort;
    }

    public String getClientIP(){
        return this.clientIP;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }


}