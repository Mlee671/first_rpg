package mlee671.basicfx.gamestates;

import mlee671.basicfx.controllers.ControllerSuper;
import mlee671.basicfx.controllers.Game2Controller;
import mlee671.basicfx.controllers.SceneManager;
import mlee671.basicfx.controllers.SceneManager.SceneType;

public class Game2 implements GameState {

    private Game2Controller controller;

    public Game2() {
        controller = (Game2Controller) SceneManager.getController(SceneType.GAME2);
    }

    @Override
    public void enter() {
        // Logic for entering the game 2 state
    }

    @Override
    public void exit() {
        // Logic for exiting the game 2 state
    }

    @Override
    public SceneType getScene() {
        return SceneType.GAME2;
    }

    @Override
    public Object getController() {
        return controller;
    }

    @Override
    public void update() {
        // Logic for updating the game 2 state
    }
}
