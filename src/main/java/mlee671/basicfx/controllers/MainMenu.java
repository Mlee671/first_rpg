package mlee671.basicfx.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mlee671.basicfx.App;

// mlee671.basicfx.controllers.MainMenu

public class MainMenu {

    // <Button fx:id="btnStartNewGame" onAction="#startNewGame" text="Start New Game" />
    //   <Button fx:id="btnLoadGame" mnemonicParsing="false" onAction="#loadGame" text="Load Game" />
    //   <Button fx:id="btnOptions" mnemonicParsing="false" onAction="#options" text="Options" />
    //   <Button fx:id="btnExit" mnemonicParsing="false" onAction="#exit" text="Exit" />

    @FXML private Button btnCollectGame;
    @FXML private Button btnRpgGame;
    @FXML private Button btnOptions;
    @FXML private Button btnExit;

    @FXML
    private void onCollectGame() throws IOException {
        App.setRoot("gameScene1");
    }

    @FXML
    private void onRpgGame() throws IOException {
        // Logic to load a game would go here
        App.setRoot("gameScene2");
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
