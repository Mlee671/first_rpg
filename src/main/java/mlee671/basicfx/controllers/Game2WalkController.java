package mlee671.basicfx.controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.paint.Color;
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
  private List<BaseCharacter> init;
  private boolean characterSelected;
  private BaseCharacter selectedCharacter;
  private int step;
  private int initcount;

  @FXML
  private void initialize() {
    step = 0;
    Views =
        List.of(
            imgHero1, imgHero2, imgHero3, imgHero4, imgHero5, imgHero6, imgEnemy1, imgEnemy2,
            imgEnemy3, imgEnemy4, imgEnemy5, imgEnemy6);
    glowRects =
        List.of(
            recGlow1,
            recGlow2,
            recGlow3,
            recGlow4,
            recGlow5,
            recGlow6,
            recTarget1,
            recTarget2,
            recTarget3,
            recTarget4,
            recTarget5,
            recTarget6);
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

  // resets selected character when background is clicked
  @FXML
  private void onClickBackground(MouseEvent event) throws IOException {
    if (characterSelected) {
      glowRects.get(selectedCharacter.getId() - 1).setVisible(false);
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
      glowRects.get(selectedCharacter.getId() - 1).setVisible(false);
    }
    if (clickedImg.getImage() != null) {
      if (characterSelected) {
        glowRects.get(selectedCharacter.getId() - 1).setVisible(false);
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
      glowRects.get(selectedCharacter.getId() - 1).setVisible(true);
    }
  }

  @FXML
  private void onClickAbility(MouseEvent event) throws IOException {}

  public void addStep() {
    step++;
  }

  public void startBattle() {
    this.init = new ArrayList<>();
    this.initcount = 0;
    int enemy = (int) (Math.random() * 6) + 1;
    Set<Integer> enemyId = new HashSet<>();
    while (enemyId.size() < enemy) {
      int id = (int) (Math.random() * 6) + 7;
      if (!enemyId.contains(id)) {
        enemyId.add(id);
        BaseCharacter character = new EnemyCharacter(id);
        characterMap.put(character.getImage(), character);
      }
    }
    getCharacterMap()
        .forEach(
            (image, character) -> {
              init.add(character);
            });
    draw();
  }

  public void startEvent() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'startEvent'");
  }

  public boolean pulse() {
    if (initcount >= init.size()) {
      initcount = 0;
    }
    glowRects.forEach(rect -> rect.setVisible(false));
    BaseCharacter character = init.get(initcount);
    initcount++;
    return attack(character.getAttack(), character.getRole(), character.getId());
  }

  private boolean attack(int damage, String type, int id) {
    glowRects.get(id-1).setVisible(true);
    glowRects.get(id-1).setFill(Color.WHITE);
    BaseCharacter target = findTarget(id);
    if (target != null) {
      glowRects.get(target.getId() - 1).setVisible(true);
      glowRects.get(target.getId() - 1).setFill(Color.RED);
      target.takeDamage(damage);
      if (target.isDead()) {
        init.remove(target);
        characterMap.remove(target.getImage());
        glowRects.get(id-1).setVisible(false);
        draw();
      }
      return false;
    } else {
      System.out.println("No valid target found.");
      return true;
    }
  }

  private BaseCharacter findTarget(int id) {
    BaseCharacter target = null;
    BaseCharacter secondaryTarget = null;
    for (BaseCharacter character : characterMap.values()) {
      if (character.isDead()) {
        continue;
      }
      if (id <= 6) {
        if (character.getId() - 6 == id % 3) {
          return character;
        } else if (6 < character.getId() && character.getId() < 10) {
          target = character;
        } else if (character.getId() >= 10) {
          secondaryTarget = character;
        }
      } else {
        if (character.getId() == id % 3) {
          return character;
        } else if (character.getId() <= 3) {
          target = character;
        } else if (character.getId() <= 6) {
          secondaryTarget = character;
        }
      }
    }
    if (target != null) {
      return target;
    } else {
      return secondaryTarget;
    }
  }
}
