package mlee671.basicfx.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import mlee671.basicfx.App;
import mlee671.basicfx.characters.BaseCharacter;

public class Game2Controller extends ControllerSuper {

  @FXML private Pane pane;
  @FXML private Button btnRoll;
  @FXML private Button btnMenu;
  @FXML private ImageView imgHero1;
  @FXML private ImageView imgHero2;
  @FXML private ImageView imgHero3;
  @FXML private ImageView imgHero4;
  @FXML private ImageView imgHero5;
  @FXML private ImageView imgHero6;
  private HashMap<Image, BaseCharacter> characterMap;
  private List<ImageView> heroViews;
  private Image tileSet;
  private int tileSize;

  @FXML
  private void initialize() {
    characterMap = new HashMap<>();
    tileSet = new Image(App.class.getResourceAsStream("images/tileset.png"));
    tileSize = 18;
    heroViews = List.of(imgHero1, imgHero2, imgHero3, imgHero4, imgHero5, imgHero6);
    draw();
  }

  // Handle reroll button click
  // generates and stores new characters and images 
  // calls draw method
  @FXML
  private void onReroll() {
    characterMap.clear();
    for (int i = 1; i <= 4; i++) {
      BaseCharacter character = new BaseCharacter(i);
      characterMap.put(getTile(character.getImage()), character);
    }
    draw();
  }

  // loop through character map and update hero views based on character id
  public void draw() {
    getCharacterMap()
        .forEach(
            (image, character) -> {
              heroViews.get(character.getId() - 1).setImage(image);
            });
  }

  // Get the character map
  private HashMap<Image, BaseCharacter> getCharacterMap() {
    return characterMap;
  }

  // Handle menu button click
  @FXML
  private void onMenu(ActionEvent event) throws IOException {
    Button btn = (Button) event.getSource();
    Scene scene = btn.getScene();
    App.openScene(scene, "mainmenu");
  }

  // Get the tile image for a specific character from tile map
  public Image getTile(int[] coords) {
    int x = coords[0];
    int y = coords[1];
    // Logic to get the tile image from the tileset
    return new javafx.scene.image.WritableImage(
        tileSet.getPixelReader(), x * tileSize, y * tileSize, tileSize, tileSize);
  }

  @FXML
  private void onClick() {
    // Logic for click event
  }
}
