module com.example.semaphore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.security.jgss;


    opens com.example.semaphore to javafx.fxml;
    exports com.example.semaphore;
}