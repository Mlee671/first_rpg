package mlee671.basicfx.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import mlee671.basicfx.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("mainMenu");
    }
}