package com.mycompany.customeraccountsmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        // Step 1: Create the CustomerList object and load data
        CustomerList customerList = new CustomerList();
        customerList.loadCustomerData();

        // Step 2: Load the FXML file for the CustomerController
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CustomerAccounts.fxml"));
        Parent root = fxmlLoader.load();

        // Step 3: Get the controller from the FXML file and inject the CustomerList
        CustomerController controller = fxmlLoader.getController();
        controller.inject(customerList);  // Inject CustomerList into the controller

        // Step 4: Set up the scene and stage
        scene = new Scene(root, 808, 695);  // Use appropriate dimensions for your app
        stage.setScene(scene);
        stage.setTitle("Customer Accounts Management");  // Set window title
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
