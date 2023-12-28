module com.designpattern {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.designpattern to javafx.fxml;
    opens com.designpattern.Singleton to javafx.base;

    exports com.designpattern;
}
