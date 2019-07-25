package models;

public class SearchInfo {
    private String clientIP;
    private int clientPort;
    private String fileName;
    private int TTL;

    public SearchInfo(String clientIP, int clientPort, String fileName){
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.fileName = fileName;
        
        this.TTL = 5; // GlobalInfo?
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
}