package communication;

import data.DAO;
import data.Neighbors;

import java.io.*;
import java.net.Socket;

public abstract class Downloader {
    public static void download(String fileName, String peer) throws Exception {
        Socket socket = null;
        BufferedOutputStream bos = null;
        try {
            int fileSize = 220000000; // 220mb
            int bytesRead;
            int currentTot = 0;
            Thread.sleep(500);
            socket = new Socket(Neighbors.getPeerIP(peer), Neighbors.DOWNLOAD_PORT);
            byte[] byteArray = new byte[fileSize];
            InputStream is = socket.getInputStream();
            new File(DAO.getFileFolderPath()).mkdir();
            FileOutputStream fos = new FileOutputStream(DAO.getFileFolderPath() + fileName);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(byteArray, 0, byteArray.length);
            currentTot = bytesRead;

            do {
                bytesRead =
                        is.read(byteArray, currentTot, (byteArray.length - currentTot));
                if (bytesRead >= 0) currentTot += bytesRead;
            } while (bytesRead > -1);

            bos.write(byteArray, 0, currentTot);
        } catch (IOException e) {
            System.out.println("Erro no download: Peer indisponível");
            //e.printStackTrace();
        } finally {
            if(bos != null) {
                bos.flush();
                bos.close();
            }
            if(socket != null) {
                socket.close();
            }
        }
    }
}
