package com.mycompany.customeraccountsmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class for the Customer Accounts Management application.
 * This class sets up and launches the JavaFX application.
 */
public class App extends Application {

    // Static scene variable to manage the app's primary scene
    private static Scene scene;

    /**
     * Entry point of the JavaFX application.
     * Initializes the CustomerList, loads the FXML file, injects the CustomerList into the controller,
     * and sets up the main window (stage) of the application.
     * 
     * @param stage The primary stage for this JavaFX application
     * @throws IOException If loading the FXML file fails
     */
    @Override
    public void start(Stage stage) throws IOException {

        // Step 1: Create the CustomerList object and load data
        CustomerList customerList = new CustomerList();
        customerList.loadCustomerData();  // Load initial customer and account data

        // Step 2: Load the FXML file for the CustomerController
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CustomerAccounts.fxml"));
        Parent root = fxmlLoader.load();  // Load the UI layout from the FXML file

        // Step 3: Get the controller from the FXML file and inject the CustomerList
        CustomerController controller = fxmlLoader.getController();  // Access the controller
        controller.inject(customerList);  // Inject CustomerList into the controller

        // Step 4: Set up the scene and stage
        scene = new Scene(root, 808, 695);  // Create the main scene with specified width and height
        stage.setScene(scene);  // Set the scene to the primary stage
        stage.setTitle("Customer Accounts Management");  // Set the window title
        stage.show();  // Display the window
    }

    /**
     * Main method to launch the JavaFX application.
     * 
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        launch();  // Launch the JavaFX application
    }
}
