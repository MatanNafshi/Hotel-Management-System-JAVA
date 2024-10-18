// DataSerialization.java
package View;

import java.io.*;

import model.Hotel;

public class DataSerialization {
    // The file path where the serialized data will be stored
	private static final String FILE_PATH = "C:\\Users\\Matan Nafshi\\Documents\\Object Oriented Programming\\207723164_BIG_EX4\\data.ser";

    // Method to serialize and save hotel data to a file
    public static void serializeData(Hotel hotel) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(hotel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to deserialize and load hotel data from a file
    public static Hotel deserializeData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Hotel) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to get the file path of the serialized data
    public static String getFilePath() {
        return FILE_PATH;
    }
}
