module com.example.practicalexam {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.practicalexam to javafx.fxml;
    exports com.example.practicalexam;
}