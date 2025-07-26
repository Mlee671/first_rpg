package mlee671.basicfx.controllers;

//import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
//import mlee671.basicfx.App;

public class GameScene {

    //<Circle fx:id="iconPlayer"
    //GridPane fx:id="gridArea"
    //onKeyPressed="#onKeyPress"
    @FXML private Circle iconPlayer;
    @FXML private GridPane gridArea;
    
    private int playerRow = 0;
    private int playerCol = 0;

    @FXML
    private void initialize() {
        // Initialization logic for the secondary controller
        System.out.println("SecondaryController initialized");
        // Place the player at the starting position
        GridPane.setRowIndex(iconPlayer, playerRow);
        GridPane.setColumnIndex(iconPlayer, playerCol);
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        // Handle key press events
        switch (event.getCode()) {
            case W:
                if (playerRow > 0) playerRow--;
                System.out.println("Moving Up");
                break;
            case S:
                if (playerRow < gridArea.getRowCount() - 1) {
                    playerRow++;
                }
                System.out.println("Moving Down");
                break;
            case A:
                if (playerCol > 0) {
                    playerCol--;
                }
                System.out.println("Moving Left");
                break;
            case D:
                if (playerCol < gridArea.getColumnCount() - 1) {
                    playerCol++;
                }
                System.out.println("Moving Right");
                break;
            default:
                break;
        }
        // Move the player icon
        GridPane.setRowIndex(iconPlayer, playerRow);
        GridPane.setColumnIndex(iconPlayer, playerCol);
    }
}