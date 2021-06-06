package service;

import java.io.*;

public class SerializerService {

    public SerializerService() {
    }

    public void writeObjectToFile(String filePath, Object serObj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object readObjectFromFile(String filePath) {
        try {
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
