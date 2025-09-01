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
    @FXML private Circle iconEnemy;
    @FXML private GridPane gridArea;
    @FXML private Button btnMenu;
    @FXML private Button btnExit;
    @FXML private ImageView imageApple;

    private int[] pCoord = {0, 0};
    private int[] imCoord = {2, 2};
    private int[] eCoord = {4, 4};
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

        if (playerOnEnemy()) {
            System.out.println("You encountered an enemy!");
            score = 0;
            pCoord[0] = GridPane.getRowIndex(iconEnemy);
            pCoord[1] = GridPane.getColumnIndex(iconEnemy);
            return;
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

        moveEnemy();

        if (playerOnEnemy()) {
            System.out.println("You encountered an enemy!");
            score = 0;
            eCoord[0] = GridPane.getRowIndex(iconEnemy);
            eCoord[1] = GridPane.getColumnIndex(iconEnemy);
            return;
        }

        // Update enemy position
        GridPane.setRowIndex(iconEnemy, eCoord[0]);
        GridPane.setColumnIndex(iconEnemy, eCoord[1]);
    }

    private void moveEnemy() {
        // Simple AI: Move towards the player
        double logic = Math.random();
        if (logic < .25) {
            if (eCoord[0] < pCoord[0]) eCoord[0]++;
            else if (eCoord[0] > pCoord[0]) eCoord[0]--;
        } else if (logic < .5) {
            if (eCoord[1] < pCoord[1]) eCoord[1]++;
            else if (eCoord[1] > pCoord[1]) eCoord[1]--;
        } else if (logic < .75) {
            if (eCoord[0] < 3) eCoord[0]++;
            else eCoord[0]--;
        } else {
            if (eCoord[1] < 3) eCoord[1]++;
            else eCoord[1]--;
        }
    }

    private boolean playerOnItem() {
        return pCoord[0] == imCoord[0] && pCoord[1] == imCoord[1];
    }

    private boolean playerOnEnemy() {
        return pCoord[0] == eCoord[0] && pCoord[1] == eCoord[1];
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