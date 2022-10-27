module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.GUI;
    opens com.example.demo1.GUI to javafx.fxml;
}