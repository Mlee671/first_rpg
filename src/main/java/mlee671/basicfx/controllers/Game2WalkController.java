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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mlee671.basicfx.App;
import mlee671.basicfx.characters.BaseCharacter;
import mlee671.basicfx.characters.EnemyCharacter;

public class Game2WalkController extends ControllerSuper {

  @FXML private Button btnExit;
  @FXML private Label lblStep;
  @FXML private ImageView imgEnemy1;
  @FXML private ImageView imgEnemy2;
  @FXML private ImageView imgEnemy3;
  @FXML private ImageView imgEnemy4;
  @FXML private ImageView imgEnemy5;
  @FXML private ImageView imgEnemy6;
  @FXML private Rectangle recTarget1;
  @FXML private Rectangle recTarget2;
  @FXML private Rectangle recTarget3;
  @FXML private Rectangle recTarget4;
  @FXML private Rectangle recTarget5;
  @FXML private Rectangle recTarget6;

  private List<BaseCharacter> init;
  private int step;
  private int initcount;
  private HashMap<Integer, Set<Integer>> combatMap;

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
    int encounters = getRand();
    int onStep;
    combatMap = new HashMap<>();
    for (int i = 1; i <= encounters; i++) {
      onStep = (int) (Math.random() * 100) + 1;
      combatMap.put(onStep, new HashSet<>());
      for (int j = 0; j < 6; j++) {
        combatMap.get(onStep).add(getRand() + 6);
      }
    }
  }

  // Handle menu button click
  @FXML
  private void onExit(ActionEvent event) throws IOException {
    Button btn = (Button) event.getSource();
    Scene scene = btn.getScene();
    App.openScene(scene, "mainmenu");
  }

  private int getRand() {
    return (int) (Math.random() * 6) + 1;
  }

  @FXML
  private void onClickAbility(MouseEvent event) throws IOException {}

  public void addStep() {

    step++;
    lblStep.setText("Step: " + step);
    if (combatMap.containsKey(step)) {
      combatMap
          .get(step)
          .forEach(
              id -> {
                BaseCharacter character = new EnemyCharacter(id);
                characterMap.put(character.getImage(), character);
              });
      startBattle();
    }
    if (step >= 100) {
      Scene scene = pane.getScene();
      try {
        App.openScene(scene, "mainmenu");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public void startBattle() {
    this.init = new ArrayList<>();
    this.initcount = 0;
    inBattle = true;
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

  public void pulse() {
    if (inBattle) {
      draw();
      if (initcount >= init.size()) {
        initcount = 0;
      }
      glowRects.forEach(rect -> rect.setVisible(false));
      BaseCharacter character = init.get(initcount);
      initcount++;
      attack(character.getAttack(), character.getRole(), character.getId());
    } else {
      addStep();
    }
  }

  private void attack(int damage, String type, int id) {
    glowRects.get(id - 1).setVisible(true);
    glowRects.get(id - 1).setFill(Color.WHITE);
    BaseCharacter target = findTarget(id);
    if (target != null) {
      glowRects.get(target.getId() - 1).setVisible(true);
      glowRects.get(target.getId() - 1).setFill(Color.RED);
      target.takeDamage(damage);
      if (target.isDead()) {
        init.remove(target);
        characterMap.remove(target.getImage());
        draw();
      }
    } else {
      System.out.println("No valid target found.");
      glowRects.get(id - 1).setVisible(false);
      inBattle = false;
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
