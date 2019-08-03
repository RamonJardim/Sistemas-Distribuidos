package data;

public class DAO {
    private static DAO dao;

    private long actualID;


    private DAO(){
        actualID = 0;
    }
    public static DAO getDao(){
        return dao == null ? new DAO() : dao;
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
}
