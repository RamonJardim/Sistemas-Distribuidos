package threads.search;

import data.MetadataInfoDAO;
import data.Neighbors;
import models.SearchInfo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Downloader extends Thread {

    private SearchInfo searchInfo;

    public Downloader(SearchInfo s){
        this.searchInfo = s;
    }

    @Override
    public void run() {
        super.run();
        sendFile();
    }

    public void sendFile(){
        try(ServerSocket serverSocket = new ServerSocket(Neighbors.DOWNLOAD_PORT)) {
            Socket socket = serverSocket.accept();
            File transferFile = new File(MetadataInfoDAO.getFileFolderPath() + searchInfo.getFileName());
            byte[] bytearray = new byte[(int) transferFile.length()];
            FileInputStream fin = new FileInputStream(transferFile);
            BufferedInputStream bin = new BufferedInputStream(fin);
            bin.read(bytearray, 0, bytearray.length);
            OutputStream os = socket.getOutputStream();
            os.write(bytearray, 0, bytearray.length);
            os.flush();
            socket.close();
        } catch(Exception e) {
          e.printStackTrace();
        }
    }
}
