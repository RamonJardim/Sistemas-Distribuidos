package models;

import data.SearchDAO;

import java.io.Serializable;

public class SearchInfo implements Serializable {
    private String clientIP;
    private int clientPort;
    private String fileName;
    private int TTL;

    private long ID;

    public SearchInfo(String clientIP, int clientPort, String fileName, long ID){
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.fileName = fileName;
        this.ID = ID;

        this.TTL = SearchDAO.getTTL();
    }

    public int getTTL(){
        return this.TTL;
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

    @Override
    public boolean equals(Object s){
        if (this == s) {
            return true;
        }
        if (s == null || getClass() != s.getClass()) {
            return false;
        }

        return ((SearchInfo)s).getID() == this.ID &&
                ((SearchInfo)s).getClientIP().equals(this.clientIP);
    }

    public void decrementTTL() {
        this.TTL--;
    }
}