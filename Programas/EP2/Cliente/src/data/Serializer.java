package data;

import java.io.*;
import java.util.List;

public abstract class Serializer {

    public static byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }

    public static Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

    //public static String listFiles(List<Metadata> filesList){
    //    StringBuilder files = new StringBuilder();
    //    for(Metadata file: filesList){
    //        files.append(file.getFileName()).append(", ");
    //    }
    //    return files.length() > 2 ? files.substring(0, files.length()-2) : files.toString();
    //}
}
