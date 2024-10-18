package View;

import javax.swing.*;

import model.Hotel;

public class DataManager {
    // Method to save data to a file
    public void saveData() {
        Hotel hotel = Hotel.getInstance();
        DataSerialization.serializeData(hotel);
    }

    public void loadData() {
        Hotel loadedHotel = DataSerialization.deserializeData();

        if (loadedHotel != null) {
            // If data was successfully loaded, set the loaded hotel as the current instance
            Hotel.setInstance(loadedHotel);
            // Show a success message to the user
            JOptionPane.showMessageDialog(null, "App loaded. 'OK' to start");
        } else {
            // Display a message to the user
            JOptionPane.showMessageDialog(null, "Data not found or failed to load. Starting with an empty hotel.");
        }
    }
}
