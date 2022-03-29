module com.example.myplatenrro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.myplatenrro to javafx.fxml;
    exports com.example.myplatenrro;
}