module com.mycompany.elevatorproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.elevatorproject to javafx.fxml;
    exports com.mycompany.elevatorproject;
}
