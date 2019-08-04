package threads.state;

import data.MetadataInfoDAO;
import models.Metadata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SelfStateReader extends Thread { // Este representa T1
    private MetadataInfoDAO metadataInfoDao;

    public SelfStateReader(){
        this.metadataInfoDao = MetadataInfoDAO.getDAO();
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
            System.out.println("Erro em T1: ");
            e.printStackTrace();
        }
    }

    private void updateMetadata(){
        try {
            File folder = new File(MetadataInfoDAO.getFileFolderPath());
            File[] filesList = folder.listFiles();
            BasicFileAttributes attributes;
            ArrayList<Metadata> myFilesList = new ArrayList<Metadata>();

            for (File file : filesList) {
                attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                if (file.isFile()) {
                    myFilesList.add(new Metadata(file.getName(), new Date(attributes.creationTime().toMillis()), new Date(attributes.lastModifiedTime().toMillis()), attributes.size(), getExtension(file)));
                }
            }
            metadataInfoDao.setInternInfo(myFilesList);

            //System.out.println(String.format("Console peer %s: Thread T1 - Arquivo(s) obtido(s) da pasta: (%s) Estado " +
            //    "atual de %s: %d.", MetadataInfoDAO.getPeerName(), Serializer.listFiles(myFilesList), MetadataInfoDAO.getPeerName(),
            //    metadataInfoDao.getInternInfo().getInfoNumber()));
        } catch (IOException e) {
           System.out.println("Erro em T1: ");
           e.printStackTrace();
        }
    }

    private String getExtension(File file){
        return file.getName().split("\\.")[1];
    }
}
