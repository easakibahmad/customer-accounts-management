package com.mycompany.customeraccountsmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    // Declare the CustomerList and current Customer
    private CustomerList customerList;
    private Customer currentCustomer;

    // Text fields
    @FXML
    private TextField customerIdField, nameField, phoneField, emailField, numberOfAccountsField, accountIdField,
                      accountTypeField, depositField, withdrawField;

    // Text areas
    @FXML
    private TextArea accountDetailsArea, messagesArea;

    // Buttons
    @FXML
    private Button clearButton, exitButton, depositButton, withdrawButton, nextButton, previousButton;

    // Inject CustomerList reference from App class
    public void inject(CustomerList list) {
        this.customerList = list;
    }

    // Initialization
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountDetailsArea.setEditable(false);
        messagesArea.setEditable(false);

        depositField.setPromptText("amount");
        withdrawField.setPromptText("amount");
    }

// Action handler for the "Find Customer" button
    @FXML
    public void findCustomer() {
        System.out.println("CustomerController - findCustomer()");
        String customerId = customerIdField.getText().trim();

        // Check if customer ID field is empty
        if (customerId.isEmpty()) {
            messagesArea.setText("Please enter a customer ID.");
            return;
        }
        clearCustomerDetails();

        // Attempt to find the customer in the CustomerList
        currentCustomer = customerList.findCustomer(customerId);

        if (currentCustomer != null) {
            displayCustomerDetails(); // Update UI fields with customer data
            // messagesArea.setText("Customer found!");
        } else {
            clearCustomerDetails(); // Clear customer details
            messagesArea.setText(customerId + " not found");
        }
    }
    
    // Clear customer details from the UI
    private void clearCustomerDetails() {
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        numberOfAccountsField.clear();
        accountDetailsArea.clear();
        messagesArea.clear();
    }

    // Method to bind customer details to the UI fields
    private void displayCustomerDetails() {
        nameField.setText(currentCustomer.getName());
        phoneField.setText(currentCustomer.getPhone());
        emailField.setText(currentCustomer.getEmail());
        numberOfAccountsField.setText(String.valueOf(currentCustomer.getNumberOfAccounts()));
    }

    // Handle Clear button
    @FXML
    private void handleClearButton() {
        customerIdField.clear();
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        numberOfAccountsField.clear();

        accountIdField.clear();
        accountTypeField.clear();
        accountDetailsArea.clear();
        messagesArea.clear();
        depositField.clear();
        withdrawField.clear();

        withdrawButton.setDisable(false);
    }

    // Handle Exit button
    @FXML
    private void handleExitButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    // Handle button clicks (e.g., deposit, withdraw, next, previous)
    @FXML
    private void handleButtonClicked() {
        Button clickedButton = (Button) exitButton.getScene().getFocusOwner();
        String buttonText = clickedButton.getText();
        messagesArea.setText(buttonText + " button clicked - under development");
    }

    // Implement other button handlers as needed
}
