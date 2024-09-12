package com.mycompany.customeraccountsmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class CustomerController {
    private CustomerList customerList;
    private Customer currentCustomer;

    @FXML
    private TextField customerIdField;
    @FXML
    private TextArea customerDetailsArea;

    // Inject CustomerList reference from App class
    public void inject(CustomerList list) {
        this.customerList = list;
    }

    // Action handler for the "Find Customer" button
    @FXML
    public void findCustomer() {
        String customerId = customerIdField.getText();
        currentCustomer = customerList.findCustomer(customerId);

        if (currentCustomer != null) {
            displayCustomerDetails();
        } else {
            customerDetailsArea.setText("Customer not found.");
        }
    }

    // Display customer details
    private void displayCustomerDetails() {
        String details = "Customer ID: " + currentCustomer.getCustomerID() + "\n" +
                         "Name: " + currentCustomer.getName() + "\n" +
                         "Phone: " + currentCustomer.getPhone() + "\n" +
                         "Email: " + currentCustomer.getEmail() + "\n" +
                         "Number of Accounts: " + currentCustomer.getNumberOfAccounts();
        customerDetailsArea.setText(details);
    }
}