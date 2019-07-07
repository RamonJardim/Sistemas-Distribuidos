package threads;

import models.Metadata;
import models.PeerInfo;
import data.DAO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class MetadataReader extends Thread { // Este representa T1
    private DAO dao;

    public MetadataReader(){
        this.dao = DAO.getDAO();
    }

    @Override
    public void run() {
        try{
            while(true){   
                Thread.sleep(1000);
                updateMetadata();
            }
        }catch(Exception e){
            System.out.println("Erro em T1: " + e.getMessage());
        }
    }

    private void updateMetadata(){
        try {
            File folder = new File("./Programas/EP1/files");
            //File folder = new File("????files");
            File[] filesList = folder.listFiles();
            BasicFileAttributes attributes;
            List<Metadata> myFilesList = new ArrayList<Metadata>();
            String files = "";

            for (File file : filesList) {
                attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                if (file.isFile()) {
                    myFilesList.add(new Metadata(file.getName(), new Date(attributes.creationTime().toMillis()), new Date(attributes.lastModifiedTime().toMillis()), attributes.size(), getExtension(file)));
                }
            }
            dao.setInternInfo(myFilesList);

            for(Metadata file: dao.getInternInfo().getFilesInfo()){
                files += file.getFileName() + ", ";
            }
            System.out.println("Console peer X: thread T1 - " + dao.getInternInfo().getFilesCount() +
                    " arquivo(s) obtido(s) da pasta. (" + files + ") Estado atual de X: " + dao.getInternInfo().getInfoNumber() + ".");
        } catch (IOException e) {
           System.out.println("Erro em T1: " + e.getMessage());
        }
    }

    private String getExtension(File file){
        return file.getName().split("\\.")[1];
    }
}
