package threads;

import models.Metadata;
import models.PeerInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

public class MetadataReader extends Thread {
    private String peerName;

    public MetadataReader(String peerName){
        this.peerName = peerName;
    }

    @Override
    public void run() {

    }

    private void updateMetadata(){
        try {
            File folder = new File("./files");
            File[] listOfFiles = folder.listFiles();
            BasicFileAttributes metadata;

            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    metadata = Files.readAttributes(listOfFile.toPath(), BasicFileAttributes.class);
                    System.out.println("File " + listOfFile.getName());
                    System.out.println(metadata.creationTime() + " " + metadata.size() + " " + metadata.lastModifiedTime() + " "
                            + listOfFile.getName().split("\\.")[1]);
                } else if (listOfFile.isDirectory()) {
                    System.out.println("Directory " + listOfFile.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
