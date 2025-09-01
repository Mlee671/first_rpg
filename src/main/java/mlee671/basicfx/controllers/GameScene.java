package mlee671.basicfx.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import mlee671.basicfx.App;

public class GameScene {

    //<Circle fx:id="iconPlayer"
    //GridPane fx:id="gridArea"
    //onKeyPressed="#onKeyPress"
    @FXML private Circle iconPlayer;
    @FXML private GridPane gridArea;
    @FXML private Button btnMenu;
    @FXML private Button btnExit;
    @FXML private ImageView imageApple;

    private int[] pCoord = {0, 0};
    private int[] imCoord = {2, 2};
    private int score = 0;

    @FXML
    private void initialize() {
        // Place the player at the starting position
        GridPane.setRowIndex(iconPlayer, pCoord[0]);
        GridPane.setColumnIndex(iconPlayer, pCoord[1]);
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        // Handle key press events
        switch (event.getCode()) {
            case W:
                if (pCoord[0] > 0) pCoord[0]--;
                break;
            case S:
                if (pCoord[0] < 5) {
                    pCoord[0]++;
                }
                break;
            case A:
                if (pCoord[1] > 0) {
                    pCoord[1]--;
                }
                break;
            case D:
                if (pCoord[1] < 5) {
                    pCoord[1]++;
                }
                break;
            default:
                break;
        }
        // Move the player icon
        GridPane.setRowIndex(iconPlayer, pCoord[0]);
        GridPane.setColumnIndex(iconPlayer, pCoord[1]);

        // check if Apple
        if (playerOnItem()) {
            System.out.println("You found an apple!");
            // Move the apple to a new random position
            moveItemRandomly();
            increaseScore();
        }

    }

    private boolean playerOnItem() {
        return pCoord[0] == imCoord[0] && pCoord[1] == imCoord[1];
    }

    private void moveItemRandomly() {
        imCoord[0] = (int) (Math.random() * 5);
        imCoord[1] = (int) (Math.random() * 5);
        if (playerOnItem()) {
            moveItemRandomly();
            return;
        }
        GridPane.setRowIndex(imageApple, imCoord[0]);
        GridPane.setColumnIndex(imageApple, imCoord[1]);
    }

    private void increaseScore() {
        // Logic to increase the player's score
        score++;
        System.out.println("Score increased! Current score: " + score);
    }

    @FXML
    private void menu() throws IOException {
        App.setRoot("mainMenu");
    }

    @FXML
    private void exit() {
        // Logic to exit the application
        System.out.println("Exit clicked");
        System.exit(0);
    }
}