package mlee671.basicfx.gamestates;

import mlee671.basicfx.controllers.ControllerSuper;
import mlee671.basicfx.controllers.Game1Controller;
import mlee671.basicfx.controllers.SceneManager;
import mlee671.basicfx.controllers.SceneManager.SceneType;

public class Game1 implements GameState {

    private Game1Controller controller;

    public Game1() {
        controller = (Game1Controller) SceneManager.getController(SceneType.GAME1);
    }

    @Override
    public void enter() {
        controller.restartGame();
    }

    @Override
    public void exit() {
        // Logic for exiting the game 1 state
    }

    @Override
    public SceneType getScene() {
        return SceneType.GAME1;
    }

    @Override
    public Object getController() {
        return controller;
    }

    @Override
    public void update() {
        // Logic for updating the game 1 state
    }
}
