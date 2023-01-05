module com.example.minidictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.minidictionary to javafx.fxml;
    exports com.example.minidictionary;
}