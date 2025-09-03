package mlee671.basicfx.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import mlee671.basicfx.App;
import mlee671.basicfx.characters.BaseCharacter;
import mlee671.basicfx.characters.HeroCharacter;

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
  @FXML private ImageView imgSel;
  @FXML private Label lblName;
  @FXML private Label lblHealth;
  @FXML private Label lblSTR;
  @FXML private Label lblAGI;
  @FXML private Label lblWIS;
  @FXML private Label lblID;
  @FXML private Rectangle recGlow1;
  @FXML private Rectangle recGlow2;
  @FXML private Rectangle recGlow3;
  @FXML private Rectangle recGlow4;
  @FXML private Rectangle recGlow5;
  @FXML private Rectangle recGlow6;

  
  private List<ImageView> heroViews;
  private List<Rectangle> glowRects;
  private Image tileSet;
  private int tileSize;
  private boolean characterSelected;
  private BaseCharacter selectedCharacter;

  @FXML
  private void initialize() {
    characterMap = new HashMap<>();
    tileSet = new Image(App.class.getResourceAsStream("images/herotileset.png"));
    tileSize = 18;
    heroViews = List.of(imgHero1, imgHero2, imgHero3, imgHero4, imgHero5, imgHero6);
    glowRects = List.of(recGlow1, recGlow2, recGlow3, recGlow4, recGlow5, recGlow6);
    characterSelected = false;
    onReroll();
  }

  // Handle reroll button click
  // generates and stores new characters and images
  // calls draw method
  @FXML
  private void onReroll() {
    characterMap.clear();
    for (int i = 1; i <= 4; i++) {
      BaseCharacter character = new HeroCharacter(i);
      characterMap.put(getTile(character.getImage()), character);
    }
    draw();
    pane.setVisible(false);
  }

  // loop through character map and update hero views based on character id
  public void draw() {
    heroViews.forEach(view -> view.setImage(null));
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

  @FXML
  private void onNext(ActionEvent event) throws IOException {
    Button btn = (Button) event.getSource();
    Scene scene = btn.getScene();
    App.openScene(scene, "game2walk");
  }

  // Get the tile image for a specific character from tile map
  public Image getTile(int[] coords) {
    int x = coords[0];
    int y = coords[1];
    // Logic to get the tile image from the tileset
    return new javafx.scene.image.WritableImage(
        tileSet.getPixelReader(), x * tileSize, y * tileSize, tileSize, tileSize);
  }

  // resets selected character when background is clicked
  @FXML
  private void onClickBackground(MouseEvent event) throws IOException {
    if (characterSelected) {
      characterSelected = false;
      pane.setVisible(false);
      glowRects.forEach(rect -> rect.setVisible(false));
    }
  }

  // Handle character click events
  // shows character information on click
  // changes character position on click
  @FXML
  private void onClickCharacter(MouseEvent event) throws IOException {
    if (!characterSelected) {
      ImageView clickedImg = (ImageView) event.getSource();
      selectedCharacter = characterMap.get(clickedImg.getImage());
      if (selectedCharacter != null) {
        characterSelected = true;
        lblName.setText(selectedCharacter.getName());
        lblHealth.setText("Health: " + selectedCharacter.getHealth());
        lblSTR.setText("Strength: " + selectedCharacter.getStr());
        lblAGI.setText("Agility: " + selectedCharacter.getAgi());
        lblWIS.setText("Wisdom: " + selectedCharacter.getWis());
        lblID.setText("ID: " + selectedCharacter.getId());
        imgSel.setImage(clickedImg.getImage());
        pane.setVisible(true);
        glowRects.forEach(rect -> rect.setVisible(true));
      }
    } else {
      ImageView clickedImg = (ImageView) event.getSource();
      if (clickedImg.getImage() != null) {
        BaseCharacter character = characterMap.get(clickedImg.getImage());
        character.setId(selectedCharacter.getId());
      }
      selectedCharacter.setId(Character.getNumericValue(clickedImg.getId().charAt(7)));
      draw();
    }
  }
}
