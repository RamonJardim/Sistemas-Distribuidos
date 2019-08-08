package data;

import exceptions.InfoException;
import exceptions.OutdatedInfoException;
import exceptions.RepeatedInfoException;
import models.Metadata;
import models.PeerInfo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DAO {

    private List<String> links;

    private DAO(){
        links = new ArrayList<String>();
    }

    public static DAO getDAO(){
        if(metadataInfoDao == null)
            return metadataInfoDao = new MetadataInfoDAO();
        else
            return metadataInfoDao;
    }

    public String getLinks() {
        return links;
    }

    public void storeLink(String link) {
        this.links.add(link);
    }

    public static String getFilePath(){
        return System.getProperty("os.name").contains("Windows") ? "./files/" : "../files/links.txt";
    }
}