package com.mycompany.customeraccountsmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.List;
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
            // messagesArea.setText("Customer found!");
            currentAccountIndex = 0;
            displayAccountDetails();
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
    
    // Display the current account details
    private void displayAccountDetails() {
        if (currentCustomer.getNumberOfAccounts() > 0) {
            List<Account> accounts = currentCustomer.getAccounts();
            Account currentAccount = accounts.get(currentAccountIndex);
            accountIdField.setText(currentAccount.getAccountID());
            accountTypeField.setText(currentAccount.getType());
            
            // Disable withdraw button if the account type is "Home Loan"
            if ("Home Loan".equalsIgnoreCase(currentAccount.getType())) {
                withdrawButton.setDisable(true);
            } else {
                withdrawButton.setDisable(false);
            }
        }
    }
    
        // Handle "Previous" button to display the previous account
    @FXML
    private void handlePreviousButton() {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            currentAccountIndex = (currentAccountIndex - 1 + currentCustomer.getNumberOfAccounts()) % currentCustomer.getNumberOfAccounts();
            displayAccountDetails();
        }
    }
    
    // Handle "Next" button to display the next account
    @FXML
    private void handleNextButton() {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            currentAccountIndex = (currentAccountIndex + 1) % currentCustomer.getNumberOfAccounts();
            displayAccountDetails();
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
    private void handleDepositButton() {
        try {
            double amount = Double.parseDouble(depositField.getText());

            if (amount <= 0) {
                messagesArea.setText("Deposit amount must be positive.");
            } else {
                currentCustomer.getCurrentAccount().deposit(amount);
                displayAccountDetails(currentCustomer.getCurrentAccount()); // Update UI
                messagesArea.setText("Deposit successful.");
            }
        } catch (NumberFormatException e) {
            messagesArea.setText("Invalid deposit amount. Please enter a valid number.");
        }
    }

    @FXML
    private void handleWithdrawButton() {
        try {
            double amount = Double.parseDouble(withdrawField.getText());

            if (amount <= 0) {
                messagesArea.setText("Withdraw amount must be positive.");
            } else {
                currentCustomer.getCurrentAccount().withdraw(amount);
                displayAccountDetails(currentCustomer.getCurrentAccount()); // Update UI
                messagesArea.setText("Withdraw successful.");
            }
        } catch (NumberFormatException e) {
            messagesArea.setText("Invalid withdraw amount. Please enter a valid number.");
        }
    }
    
    @FXML
    private void handleApplyMonthlyInterestButton() {
        // Apply monthly interest to the current account
        currentCustomer.getCurrentAccount().applyMonthlyInterest();

        // Update the account details in the UI
        displayAccountDetails(currentCustomer.getCurrentAccount());

        // Display success message
        messagesArea.setText("Monthly interest applied successfully.");
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
