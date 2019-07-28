package communication;

import data.Neighbors;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public abstract class Downloader {
    public static void download(String fileName, String peer) {
        try {
            int fileSize = 2000000000;
            int bytesRead;
            int currentTot = 0;
            Socket socket = new Socket(Neighbors.getPeerIP(peer), Neighbors.DOWNLOAD_PORT);
            byte[] byteArray = new byte[fileSize];
            InputStream is = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bytesRead = is.read(byteArray, 0, byteArray.length);
            currentTot = bytesRead;

            do {
                bytesRead =
                        is.read(byteArray, currentTot, (byteArray.length - currentTot));
                if (bytesRead >= 0) currentTot += bytesRead;
            } while (bytesRead > -1);

            bos.write(byteArray, 0, currentTot);
            bos.flush();
            bos.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Erro no downloader: ");
            e.printStackTrace();
        }
    }
}
