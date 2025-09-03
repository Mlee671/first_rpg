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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import mlee671.basicfx.App;
import mlee671.basicfx.characters.BaseCharacter;
import mlee671.basicfx.characters.EnemyCharacter;

public class Game2WalkController extends ControllerSuper {

  @FXML private Pane pane;
  @FXML private Button btnExit;
  @FXML private Label lblStep;
  @FXML private ImageView imgHero1;
  @FXML private ImageView imgHero2;
  @FXML private ImageView imgHero3;
  @FXML private ImageView imgHero4;
  @FXML private ImageView imgHero5;
  @FXML private ImageView imgHero6;
  @FXML private ImageView imgEnemy1;
  @FXML private ImageView imgEnemy2;
  @FXML private ImageView imgEnemy3;
  @FXML private ImageView imgEnemy4;
  @FXML private ImageView imgEnemy5;
  @FXML private ImageView imgEnemy6;
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
  @FXML private Rectangle recTarget1;
  @FXML private Rectangle recTarget2;
  @FXML private Rectangle recTarget3;
  @FXML private Rectangle recTarget4;
  @FXML private Rectangle recTarget5;
  @FXML private Rectangle recTarget6;

  private List<ImageView> Views;
  private List<Rectangle> glowRects;
  private List<Rectangle> targetRects;
  private Image tileSet;
  private int tileSize;
  private boolean characterSelected;
  private BaseCharacter selectedCharacter;
  private int step;

  @FXML
  private void initialize() {
    tileSet = new Image(App.class.getResourceAsStream("images/tileset.png"));
    tileSize = 18;
    step = 0;
    Views =
        List.of(
            imgHero1, imgHero2, imgHero3, imgHero4, imgHero5, imgHero6, imgEnemy1, imgEnemy2,
            imgEnemy3, imgEnemy4, imgEnemy5, imgEnemy6);
    glowRects = List.of(recGlow1, recGlow2, recGlow3, recGlow4, recGlow5, recGlow6);
    targetRects = List.of(recTarget1, recTarget2, recTarget3, recTarget4, recTarget5, recTarget6);
    characterSelected = false;
  }

  // loop through character map and update hero/enemy views based on character id
  public void draw() {
    Views.forEach(view -> view.setImage(null));
    lblStep.setText("Step: " + step);
    getCharacterMap()
        .forEach(
            (image, character) -> {
              Views.get(character.getId() - 1).setImage(image);
            });
  }

  // Get the character map
  private HashMap<Image, BaseCharacter> getCharacterMap() {
    return characterMap;
  }

  // Handle menu button click
  @FXML
  private void onExit(ActionEvent event) throws IOException {
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

  // resets selected character when background is clicked
  @FXML
  private void onClickBackground(MouseEvent event) throws IOException {
    if (characterSelected) {
      glowRects.get(selectedCharacter.getId()-1).setVisible(false);
      characterSelected = false;
      pane.setVisible(false);
    }
  }

  // Handle character click events
  // shows character information on click
  // changes character position on click
  @FXML
  private void onClickCharacter(MouseEvent event) throws IOException {

    ImageView clickedImg = (ImageView) event.getSource();
    if (characterSelected && clickedImg.getImage() != null) {
      glowRects.get(selectedCharacter.getId()-1).setVisible(false);
    }
    if (clickedImg.getImage() != null) {
      if (characterSelected) {
      glowRects.get(selectedCharacter.getId()-1).setVisible(false);
      }
      selectedCharacter = characterMap.get(clickedImg.getImage());
      characterSelected = true;
      lblName.setText(selectedCharacter.getName());
      lblHealth.setText("Health: " + selectedCharacter.getHealth());
      lblSTR.setText("Strength: " + selectedCharacter.getStr());
      lblAGI.setText("Agility: " + selectedCharacter.getAgi());
      lblWIS.setText("Wisdom: " + selectedCharacter.getWis());
      lblID.setText("ID: " + selectedCharacter.getId());
      imgSel.setImage(clickedImg.getImage());
      pane.setVisible(true);
      glowRects.get(selectedCharacter.getId()-1).setVisible(true);
    }
  }

  @FXML
  private void onClickAbility(MouseEvent event) throws IOException {
  }

  public void addStep() {
    step++;
  }

  public void startBattle() {
    int enemy = (int) (Math.random() * 6) + 1;
    Set<Integer> enemyId = new HashSet<>();
    while (enemyId.size() < enemy) {
      int id = (int) (Math.random() * 6) + 7;
      if (!enemyId.contains(id)) {
        enemyId.add(id);
        BaseCharacter character = new EnemyCharacter(id);
        characterMap.put(getTile(character.getImage()), character);
      }
    }
    draw();
  }

  public void startEvent() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'startEvent'");
  }

  public void pulse() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'pulse'");
  }
}
