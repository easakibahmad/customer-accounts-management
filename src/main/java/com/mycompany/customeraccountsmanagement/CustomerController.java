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
    private int currentAccountIndex = 0;

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

    // Initialization
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountDetailsArea.setEditable(false);
        messagesArea.setEditable(false);

        depositField.setPromptText("amount");
        withdrawField.setPromptText("amount");
    }
    
    // Inject CustomerList reference from App class
    public void inject(CustomerList list) {
        this.customerList = list;
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

            currentAccountIndex = 0;
            
            // Check if the customer has accounts, and display the first account if available
            if (currentCustomer.getNumberOfAccounts() > 0) {
                Account firstAccount = currentCustomer.getAccounts().get(currentAccountIndex);
                displayAccountDetails(firstAccount);
            } else {
                // If no accounts are found, clear the account details section
                clearAccountDetails();
                messagesArea.setText("No accounts found for this customer.");
            }
        } else {
            clearCustomerDetails(); // Clear customer details
            messagesArea.setText(customerId + " not found");
        }
    }
    
    @FXML
    public void findAccount() {
        System.out.println("CustomerController - findAccount()");

        // Get the account ID from the input field
        String accountId = accountIdField.getText().trim();

        // Validate that the input is not empty
        if (accountId.isEmpty()) {
            messagesArea.setText("Please enter an account ID.");
            return;
        }

        // Attempt to find the account in the CustomerList
        Account foundAccount = customerList.findAccount(accountId);

        if (foundAccount != null) {
            // Set the current customer to the owner of the account
            currentCustomer = customerList.findCustomer(foundAccount.getCustomerID());
            
            // Set the Customer ID field
            customerIdField.setText(currentCustomer.getCustomerID());

            // Display account details and customer details
            displayAccountDetails(foundAccount);
            displayCustomerDetails();

            // Set the current account to the found account in the customer's list
            currentCustomer.setCurrentAccount(accountId);
        } else {
            messagesArea.setText("Account with ID: " + accountId + " not found.");
            clearAccountDetails();
        }
    }
    
    // Method to display the found account's details on the UI
    private void displayAccountDetails(Account account) {
        accountIdField.setText(account.getAccountID());
        accountTypeField.setText(account.getType());
        
        // Display account-specific details in the accountDetailsArea
        accountDetailsArea.setText(account.getAccountDetails());

        // Disable withdraw button if it's a Home Loan account
        if ("Home Loan".equalsIgnoreCase(account.getType())) {
            withdrawButton.setDisable(true);
        } else {
            withdrawButton.setDisable(false);
        }
        
        // Disable next and previous buttons if the customer has only one account
        if (currentCustomer.getNumberOfAccounts() == 1) {
            nextButton.setDisable(true);
            previousButton.setDisable(true);
        } else {
            nextButton.setDisable(false);
            previousButton.setDisable(false);
        }
    }

    // Method to clear account fields if no account is found
    private void clearAccountDetails() {
        accountIdField.clear();
        accountTypeField.clear();
    }
    
    // Clear customer details from the UI
    private void clearCustomerDetails() {
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        numberOfAccountsField.clear();
        
        accountDetailsArea.clear();
        messagesArea.clear();
        accountIdField.clear();
        accountTypeField.clear();
        
        withdrawButton.setDisable(false);
    }

    // Method to bind customer details to the UI fields
    private void displayCustomerDetails() {
        nameField.setText(currentCustomer.getName());
        phoneField.setText(currentCustomer.getPhone());
        emailField.setText(currentCustomer.getEmail());
        numberOfAccountsField.setText(String.valueOf(currentCustomer.getNumberOfAccounts()));
    }
    
    // Handle "Next" button to display the next account
    @FXML
    private void handleNextButton() {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            currentAccountIndex = (currentAccountIndex + 1) % currentCustomer.getNumberOfAccounts();
            Account nextAccount = currentCustomer.getAccounts().get(currentAccountIndex);
            displayAccountDetails(nextAccount); // Call the correct display method with the next account
        }
    }

    // Handle "Previous" button to display the previous account
    @FXML
    private void handlePreviousButton() {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            currentAccountIndex = (currentAccountIndex - 1 + currentCustomer.getNumberOfAccounts()) % currentCustomer.getNumberOfAccounts();
            Account previousAccount = currentCustomer.getAccounts().get(currentAccountIndex);
            displayAccountDetails(previousAccount); // Call the correct display method with the previous account
        }
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
    
    @FXML
    private void handleWithdrawButton() {
       try {
           double amount = Double.parseDouble(withdrawField.getText());

           if (amount <= 0) {
               messagesArea.setText("Withdraw amount must be positive.");
           } else {
               // Ensure that the correct account based on currentAccountIndex is being used
               Account currentAccount = currentCustomer.getAccounts().get(currentAccountIndex);

               // Perform the withdraw action on the correct account
               currentAccount.withdraw(amount);

               // Update the UI with the correct account details
               displayAccountDetails(currentAccount);
               messagesArea.setText("Withdraw successful.");
           }
       } catch (NumberFormatException e) {
           messagesArea.setText("Invalid withdraw amount. Please enter a valid number.");
       }
    }

    @FXML
    private void handleDepositButton() {
        try {
            double amount = Double.parseDouble(depositField.getText());

            if (amount <= 0) {
                messagesArea.setText("Deposit amount must be positive.");
            } else {
                // Ensure that the correct account based on currentAccountIndex is being used
                Account currentAccount = currentCustomer.getAccounts().get(currentAccountIndex);

                // Perform the deposit action on the correct account
                currentAccount.deposit(amount);

                // Update the UI with the correct account details
                displayAccountDetails(currentAccount); // Ensure correct account details are displayed
                messagesArea.setText("Deposit successful.");
            }
        } catch (NumberFormatException e) {
            messagesArea.setText("Invalid deposit amount. Please enter a valid number.");
        }
    }
    
    @FXML
    private void handleApplyMonthlyInterestButton() {
        // Apply monthly interest to the current account based on currentAccountIndex
        Account currentAccount = currentCustomer.getAccounts().get(currentAccountIndex);

        // Apply monthly interest to the correct account
        currentAccount.applyMonthlyInterest();

        // Update the account details in the UI with the correct account
        displayAccountDetails(currentAccount);

        // Display success message
        messagesArea.setText("Monthly interest applied successfully.");
    }
    
    @FXML
    private void handleGenerateReportsButton() {
        customerList.generateReports();
        messagesArea.setText("Report generated successfully.");
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
}
