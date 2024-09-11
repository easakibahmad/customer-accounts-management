module com.mycompany.customeraccountsmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.customeraccountsmanagement to javafx.fxml;
    exports com.mycompany.customeraccountsmanagement;
}
