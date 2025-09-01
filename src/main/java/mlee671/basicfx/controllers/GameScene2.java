package mlee671.basicfx.controllers;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import mlee671.basicfx.App;
import mlee671.basicfx.characters.BaseCharacter;

public class GameScene2 {

    @FXML private AnchorPane pane;
    @FXML private ImageView imageLeftUp;
    @FXML private ImageView imageRightUp;
    @FXML private ImageView imageLeftDown;
    @FXML private ImageView imageRightDown;
    @FXML private ImageView imageMidTop;
    @FXML private ImageView imageMidBottom;
    @FXML private Button btnRoll;
    @FXML private Button btnMenu;
    private HashMap<ImageView, BaseCharacter> characterMap;

    @FXML
    private void initialize() {
        characterMap = new HashMap<>();
        onReroll();
    }

    @FXML
    private void onReroll() {
        characterMap.clear();
        // Logic to create new characters and assign them to image views
        BaseCharacter character = new BaseCharacter();
        characterMap.put(imageLeftUp, character);
        imageLeftUp.setImage(new Image(App.class.getResource("images/SkeletalSword.png").toString()));
    }

    @FXML
    private void onMenu() {
        // Logic to open the menu
        try {
            App.setRoot("mainMenu");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void onClick() {
        // Logic for click event
    }
}
