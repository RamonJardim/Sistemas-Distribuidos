package threads;

import java.io.File;

public class FileModifier extends Thread {
    @Override
    public void run() {
        changeFiles();
    }

    private void changeFiles() {
        //File folder = new File("./Programas/EP1/files");    //UBUNTU
        File folder = new File("./files");          //WINDOWS
        File[] filesList = folder.listFiles();

    }


}
