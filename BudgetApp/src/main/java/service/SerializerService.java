package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

public class SerializerService {
    private String file = Path.of("").toAbsolutePath() + "/data/";

    public SerializerService() {}

    public SerializerService(String path) {this.file = path;}

    public void writeObjectToFile(Object serObj) {
        try {
            String className = serObj.getClass().getName();
            FileOutputStream fileOut = new FileOutputStream(this.file + className.substring(className.lastIndexOf('.')+1) + ".ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object readObjectFromFile(String className) {
        try {
            FileInputStream fileIn = new FileInputStream(this.file + className + ".ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
