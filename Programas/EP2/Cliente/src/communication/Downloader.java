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
            System.out.println("Conectando");
            socket = new Socket(Neighbors.getPeerIP(peer), Neighbors.DOWNLOAD_PORT);
            System.out.println("Cria vetor");
            byte[] byteArray = new byte[fileSize];
            System.out.println("getStream");
            InputStream is = socket.getInputStream();
            System.out.println("Cria pasta");
            new File(DAO.getFileFolderPath()).mkdir();
            System.out.println("FOS");
            FileOutputStream fos = new FileOutputStream(DAO.getFileFolderPath() + fileName);
            System.out.println("BOS");
            bos = new BufferedOutputStream(fos);
            System.out.println("BytesRead");
            bytesRead = is.read(byteArray, 0, byteArray.length);
            currentTot = bytesRead;

            System.out.println("Do while");
            do {
                bytesRead =
                        is.read(byteArray, currentTot, (byteArray.length - currentTot));
                if (bytesRead >= 0) currentTot += bytesRead;
            } while (bytesRead > -1);

            System.out.println("BOS write");
            bos.write(byteArray, 0, currentTot);
            System.out.println("FimDwonload");
        } catch (IOException e) {
            System.out.println("Erro no downloader: ");
            e.printStackTrace();
        } finally {
            bos.flush();
            bos.close();
            socket.close();
            System.out.println("Fechou tudo");
        }
    }
}
