package mlee671.basicfx.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import mlee671.basicfx.App;

// mlee671.basicfx.controllers.MainMenu

public class MainMenuController extends ControllerSuper {

  // <Button fx:id="btnStartNewGame" onAction="#startNewGame" text="Start New Game" />
  //   <Button fx:id="btnLoadGame" mnemonicParsing="false" onAction="#loadGame" text="Load Game" />
  //   <Button fx:id="btnOptions" mnemonicParsing="false" onAction="#options" text="Options" />
  //   <Button fx:id="btnExit" mnemonicParsing="false" onAction="#exit" text="Exit" />

  @FXML private Button btnCollectGame;
  @FXML private Button btnRpgGame;
  @FXML private Button btnOptions;
  @FXML private Button btnExit;

  @FXML
  private void onCollectGame(ActionEvent event) throws IOException {
    Button btn = (Button) event.getSource();
    Scene scene = btn.getScene();
    App.openScene(scene, "game1");
  }

  @FXML
  private void onRpgGame(ActionEvent event) throws IOException {
    Button btn = (Button) event.getSource();
    Scene scene = btn.getScene();
    App.openScene(scene, "game2");
  }

  @FXML
  private void options() throws IOException {
    // Logic to open options would go here
    System.out.println("Options clicked");
  }

  @FXML
  private void exit() {
    // Logic to exit the application
    System.out.println("Exit clicked");
    System.exit(0);
  }
}
