module com.example.allstmts {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.allstmts to javafx.fxml;
    exports com.example.allstmts;
}