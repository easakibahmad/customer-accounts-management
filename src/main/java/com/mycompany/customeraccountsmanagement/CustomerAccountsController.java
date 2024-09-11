package com.mycompany.customeraccountsmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class CustomerAccountsController implements Initializable {

    @FXML
    private TextField customerIdField, nameField, phoneField, emailField, numberOfAccountsField, accountIdField, accountTypeField, depositField, withdrawField;

    @FXML
    private TextArea accountDetailsArea, messagesArea;

    @FXML
    private Button clearButton, exitButton, depositButton, withdrawButton, nextButton, previousButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountDetailsArea.setEditable(false);
        messagesArea.setEditable(false);

        depositField.setPromptText("amount");
        withdrawField.setPromptText("amount");
    }

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
    private void handleExitButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleButtonClicked() {
        Button clickedButton = (Button) exitButton.getScene().getFocusOwner();
        String buttonText = clickedButton.getText();
        messagesArea.setText(buttonText + " button clicked - under development");
    }
}
