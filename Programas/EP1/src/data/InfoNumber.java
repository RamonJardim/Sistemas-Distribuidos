package data;

public abstract class InfoNumber{
    private static long actualInfoNumber = 0;

    public static long getActualInfoNumber(){
        return actualInfoNumber++;
    }
}