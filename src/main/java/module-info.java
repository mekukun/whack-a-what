module com.designpattern {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.designpattern to javafx.fxml;
    exports com.designpattern;
}
