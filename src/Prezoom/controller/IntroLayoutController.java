package Prezoom.controller;

import Prezoom.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class IntroLayoutController {


    @FXML
    private Button createNewPrezoomButton;

    @FXML
    private Button openPrezoomButton;

    private Main main;

    @FXML
    void createNewPrezoom() {
        main.showEditingLayout();
    }

    @FXML
    void openPrezoom(ActionEvent event) {

    }

    public void setMain(Main main) {
        this.main = main;

        // Add observable list data to the table
    }

}
