package service;

import java.io.*;
import java.nio.file.Path;

public class SerializerService {
    private String fileDir = Path.of("").toAbsolutePath() + "/data/";

    public SerializerService() {}

    public SerializerService(String path) {this.fileDir = path;}

    public void writeObjectToFile(Object serObj) {
        try {
            String className = serObj.getClass().getName();
            FileOutputStream fileOut = new FileOutputStream(this.fileDir + className.substring(className.lastIndexOf('.')+1) + ".ser");
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
            String filePath = this.fileDir + className + ".ser";
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
        } catch (FileNotFoundException ex) {
            System.out.println("No saved file to load!");
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
