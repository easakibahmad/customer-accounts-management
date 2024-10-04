package com.mycompany.customeraccountsmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that handles customer and account-related actions 
 * for the CustomerAccountsManagement application.
 * This class provides the logic for interacting with the user interface (UI),
 * allowing users to find customers, view their accounts, and perform operations
 * like depositing, withdrawing, and generating reports.
 */
public class CustomerController implements Initializable {

    // Declare the CustomerList and current Customer
    private CustomerList customerList;  // List of all customers
    private Customer currentCustomer;  // Currently selected customer
    private int currentAccountIndex = 0;  // Index to keep track of the current account being displayed

    // Text fields for input/output
    @FXML
    private TextField customerIdField, nameField, phoneField, emailField, numberOfAccountsField, accountIdField, accountTypeField, depositField, withdrawField;

    // Text areas for displaying account details and messages
    @FXML
    private TextArea accountDetailsArea, messagesArea;

    // Buttons for various actions
    @FXML
    private Button clearButton, exitButton, depositButton, withdrawButton, nextButton, previousButton;

    /**
     * Initializes the controller. This method is automatically called when the controller is created.
     * Disables editing for text areas and sets placeholder text for input fields.
     * 
     * @param url The location used to resolve relative paths for the root object
     * @param rb The resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountDetailsArea.setEditable(false);
        messagesArea.setEditable(false);
        depositField.setPromptText("amount");
        withdrawField.setPromptText("amount");
    }
    
    /**
     * Injects the CustomerList object from the main application.
     * 
     * @param list The CustomerList object passed from the main application
     */
    public void inject(CustomerList list) {
        this.customerList = list;
    }

    /**
     * Finds a customer based on the input in the customerIdField.
     * Updates the UI with the customer's details and the first account, if available.
     * Displays an error message if the customer is not found.
     */
    @FXML
    public void findCustomer() {
        System.out.println("CustomerController - findCustomer()");
        String customerId = customerIdField.getText().trim();

        if (customerId.isEmpty()) {
            messagesArea.setText("Please enter a customer ID.");
            return;
        }

        clearCustomerDetails();  // Clear previous details from UI

        currentCustomer = customerList.findCustomer(customerId);  // Find customer by ID

        if (currentCustomer != null) {
            displayCustomerDetails();  // Show customer details

            currentAccountIndex = 0;  // Reset account index to the first account

            // Check if customer has accounts and display the first account
            if (currentCustomer.getNumberOfAccounts() > 0) {
                Account firstAccount = currentCustomer.getAccounts().get(currentAccountIndex);
                displayAccountDetails(firstAccount);  // Display details of the first account
            } else {
                clearAccountDetails();  // No accounts available for the customer
                messagesArea.setText("No accounts found for this customer.");
            }
        } else {
            clearCustomerDetails();  // Clear UI if customer not found
            messagesArea.setText(customerId + " not found");
        }
    }
    
    /**
     * Finds an account based on the input in the accountIdField.
     * Updates the UI with the details of the account and its associated customer.
     */
    @FXML
    public void findAccount() {
        System.out.println("CustomerController - findAccount()");

        String accountId = accountIdField.getText().trim();

        if (accountId.isEmpty()) {
            messagesArea.setText("Please enter an account ID.");
            return;
        }

        Account foundAccount = customerList.findAccount(accountId);  // Search for the account

        if (foundAccount != null) {
            currentCustomer = customerList.findCustomer(foundAccount.getCustomerID());  // Get the customer who owns the account
            customerIdField.setText(currentCustomer.getCustomerID());

            displayAccountDetails(foundAccount);  // Show the account details
            displayCustomerDetails();  // Show customer details

            currentCustomer.setCurrentAccount(accountId);  // Set the found account as the current account
        } else {
            messagesArea.setText("Account with ID: " + accountId + " not found.");
            clearAccountDetails();  // Clear UI if account not found
        }
    }

    /**
     * Displays details of the given account in the UI.
     * Also enables/disables the withdraw button based on the account type.
     * 
     * @param account The account whose details are to be displayed
     */
    private void displayAccountDetails(Account account) {
        accountIdField.setText(account.getAccountID());
        accountTypeField.setText(account.getType());
        accountDetailsArea.setText(account.getAccountDetails());

        // Disable withdraw button for Home Loan accounts
        if ("Home Loan".equalsIgnoreCase(account.getType())) {
            withdrawButton.setDisable(true);
        } else {
            withdrawButton.setDisable(false);
        }

        // Disable next/previous buttons if only one account exists
        if (currentCustomer.getNumberOfAccounts() == 1) {
            nextButton.setDisable(true);
            previousButton.setDisable(true);
        } else {
            nextButton.setDisable(false);
            previousButton.setDisable(false);
        }
    }

    /**
     * Clears the account-related fields from the UI.
     */
    private void clearAccountDetails() {
        accountIdField.clear();
        accountTypeField.clear();
    }

    /**
     * Clears the customer-related fields from the UI.
     * Also resets any account details displayed on the screen.
     */
    private void clearCustomerDetails() {
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        numberOfAccountsField.clear();
        accountDetailsArea.clear();
        messagesArea.clear();
        accountIdField.clear();
        accountTypeField.clear();
        withdrawButton.setDisable(false);  // Re-enable the withdraw button
    }

    /**
     * Displays the details of the current customer in the UI fields.
     */
    private void displayCustomerDetails() {
        nameField.setText(currentCustomer.getName());
        phoneField.setText(currentCustomer.getPhone());
        emailField.setText(currentCustomer.getEmail());
        numberOfAccountsField.setText(String.valueOf(currentCustomer.getNumberOfAccounts()));
    }

    /**
     * Handles the action of the "Next" button to display the next account of the current customer.
     */
    @FXML
    private void handleNextButton() {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            currentAccountIndex = (currentAccountIndex + 1) % currentCustomer.getNumberOfAccounts();
            Account nextAccount = currentCustomer.getAccounts().get(currentAccountIndex);
            displayAccountDetails(nextAccount);  // Display next account details
        }
    }

    /**
     * Handles the action of the "Previous" button to display the previous account of the current customer.
     */
    @FXML
    private void handlePreviousButton() {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            currentAccountIndex = (currentAccountIndex - 1 + currentCustomer.getNumberOfAccounts()) % currentCustomer.getNumberOfAccounts();
            Account previousAccount = currentCustomer.getAccounts().get(currentAccountIndex);
            displayAccountDetails(previousAccount);  // Display previous account details
        }
    }

    /**
     * Clears all the input and output fields when the Clear button is clicked.
     */
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
        withdrawButton.setDisable(false);  // Reset the withdraw button
    }

    /**
     * Handles the action for withdrawing money from the current account.
     * Updates the UI with the updated account balance after the withdrawal.
     */
    @FXML
    private void handleWithdrawButton() {
        try {
            double amount = Double.parseDouble(withdrawField.getText());

            if (amount <= 0) {
                messagesArea.setText("Withdraw amount must be positive.");
            } else {
                Account currentAccount = currentCustomer.getAccounts().get(currentAccountIndex);
                currentAccount.withdraw(amount);  // Perform the withdrawal
                displayAccountDetails(currentAccount);  // Update account details in the UI
                messagesArea.setText("Withdraw successful.");
            }
        } catch (NumberFormatException e) {
            messagesArea.setText("Invalid withdraw amount. Please enter a valid number.");
        }
    }

    /**
     * Handles the action for depositing money into the current account.
     * Updates the UI with the updated account balance after the deposit.
     */
    @FXML
    private void handleDepositButton() {
        try {
            double amount = Double.parseDouble(depositField.getText());

            if (amount <= 0) {
                messagesArea.setText("Deposit amount must be positive.");
            } else {
                Account currentAccount = currentCustomer.getAccounts().get(currentAccountIndex);
                currentAccount.deposit(amount);  // Perform the deposit
                displayAccountDetails(currentAccount);  // Update account details in the UI
                messagesArea.setText("Deposit successful.");
            }
        } catch (NumberFormatException e) {
            messagesArea.setText("Invalid deposit amount. Please enter a valid number.");
        }
    }

    /**
     * Applies monthly interest to the current account and updates the UI with the new balance.
     */
    @FXML
    private void handleApplyMonthlyInterestButton() {
        Account currentAccount = currentCustomer.getAccounts().get(currentAccountIndex);
        currentAccount.applyMonthlyInterest();  // Apply interest to the current account
        displayAccountDetails(currentAccount);  // Update the account details
        messagesArea.setText("Monthly interest applied successfully.");
    }

    /**
     * Generates a report for all customers and their accounts.
     */
    @FXML
    private void handleGenerateReportsButton() {
        customerList.generateReports();
        messagesArea.setText("Report generated successfully.");
    }

    /**
     * Handles the exit action and closes the application window.
     */
    @FXML
    private void handleExitButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles generic button clicks, used for under-development features.
     */
    @FXML
    private void handleButtonClicked() {
        Button clickedButton = (Button) exitButton.getScene().getFocusOwner();
        String buttonText = clickedButton.getText();
        messagesArea.setText(buttonText + " button clicked - under development");
    }
}
