package mlee671.basicfx.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import mlee671.basicfx.App;
import mlee671.basicfx.characters.BaseCharacter;
import mlee671.basicfx.characters.HeroCharacter;

public class Game2Controller extends ControllerSuper {

  @FXML private Button btnRoll;
  @FXML private Button btnMenu;

  @FXML
  private void initialize() {
    characterMap = new HashMap<>();
    Views = List.of(imgHero1, imgHero2, imgHero3, imgHero4, imgHero5, imgHero6);
    glowRects = List.of(recGlow1, recGlow2, recGlow3, recGlow4, recGlow5, recGlow6);
    characterSelected = false;
    onReroll();
  }

  // Handle reroll button click
  // generates and stores new characters and images
  // calls draw method
  @FXML
  public void onReroll() {
    characterMap.clear();
    for (int i = 1; i <= 4; i++) {
      BaseCharacter character = new HeroCharacter(i);
      characterMap.put(character.getImage(), character);
    }
    draw();
    pane.setVisible(false);
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
}
