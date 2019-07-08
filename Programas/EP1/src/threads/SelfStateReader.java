package threads;

import models.Metadata;
import models.PeerInfo;
import data.DAO;
import process.Serializer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SelfStateReader extends Thread { // Este representa T1
    private DAO dao;

    public SelfStateReader(){
        this.dao = DAO.getDAO();
    }

    @Override
    public void run() {
        try{
            updateMetadata();
            while(true){   
                Thread.sleep(500);
                updateMetadata();
            }
        }catch(Exception e){
            System.out.println("Erro em T1: " + e.getMessage());
        }
    }

    private void updateMetadata(){
        try {
            //File folder = new File("./Programas/EP1/files");    //UBUNTU
            File folder = new File("./files");          //WINDOWS
            File[] filesList = folder.listFiles();
            BasicFileAttributes attributes;
            List<Metadata> myFilesList = new ArrayList();

            for (File file : filesList) {
                attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                if (file.isFile()) {
                    myFilesList.add(new Metadata(file.getName(), new Date(attributes.creationTime().toMillis()), new Date(attributes.lastModifiedTime().toMillis()), attributes.size(), getExtension(file)));
                }
            }
            dao.setInternInfo(myFilesList);

            System.out.println(String.format("Console peer %s: Thread T1 - Arquivo(s) obtido(s) da pasta: (%s) Estado " +
                "atual de %s: %d.", DAO.getPeerName(), Serializer.listFiles(myFilesList), DAO.getPeerName(),
                dao.getInternInfo().getInfoNumber()));
        } catch (IOException e) {
           System.out.println("Erro em T1: " + e.getMessage());
        }
    }

    private String getExtension(File file){
        return file.getName().split("\\.")[1];
    }
}
