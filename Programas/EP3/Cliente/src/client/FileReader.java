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

public class FileReader {
    private List<String> getLinks(){
        try {
            File folder = new File(MetadataInfoDAO.getFilepath());
            

        } catch (IOException e) {
           System.out.println("Erro ao ler o arquivo: ");
           e.printStackTrace();
        }
    }

    private String getExtension(File file){
        return file.getName().split("\\.")[1];
    }
}
