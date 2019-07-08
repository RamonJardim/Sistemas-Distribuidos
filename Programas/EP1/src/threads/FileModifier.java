package threads;

import data.DAO;
import process.Randomizer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileModifier extends Thread {
    @Override
    public void run() {
        try {
            changeFiles();
        } catch (Exception e) {
            System.out.println("Erro no modificador de arquivos: ");
            e.printStackTrace();
        }
    }

    private static List<File> files = new ArrayList<File>();

    private void changeFiles() throws Exception {
        File file;
        int index;
        while(true) {
            Thread.sleep(500);
            if (Math.random() > 0.60) {
                String fileName = Randomizer.getFileName();
                file = new File(DAO.getFileFolderPath() + fileName + ".txt");
                file.createNewFile();
                files.add(file);
            } else if(files.size() > 0){
                index = (int)(Math.random()*files.size());
                file = files.get(index);
                file.delete();
            }
        }
    }
}
