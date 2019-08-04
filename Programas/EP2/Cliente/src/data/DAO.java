package data;

public class DAO {
    private static DAO dao;

    private static int clientID;
    private long actualID;


    private DAO(){
        actualID = 0;
    }
    public static DAO getDao(){
        return dao == null ? dao = new DAO() : dao;
    }

    public static int getClientID() {
        return clientID;
    }

    public static void setPeerID(int id) {
        clientID = id;
    }

    public long getActualID() {
        return actualID;
    }

    public void incrementID(){
        actualID++;
    }

    public long getAndIncrementActualID() {
        return actualID++;
    }

    public static String getFileFolderPath(){
        return System.getProperty("os.name").contains("Windows") ? "./files/" : "../files/";
    }
}
