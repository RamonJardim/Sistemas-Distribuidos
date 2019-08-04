package models;

import java.io.Serializable;

public class ClientSearch implements Serializable {
    private String fileName;
    private long ID;

    public ClientSearch(String fileName, long ID){
        this.fileName = fileName;
        this.ID = ID;
    }

    public String getFileName(){
        return this.fileName;
    }

    public long getID() {
        return ID;
    }

}