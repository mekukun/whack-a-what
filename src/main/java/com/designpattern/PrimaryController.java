package com.designpattern;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class PrimaryController {

    @FXML
    private ComboBox<String> moledp;

    @FXML
    private Button primaryButton;

    @FXML
    public void initialize() {

        // Add items to the moleDropdown
        moledp.getItems().addAll("item1", "item2", "item3");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

}