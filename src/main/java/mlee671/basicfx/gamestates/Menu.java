package mlee671.basicfx.gamestates;

import mlee671.basicfx.controllers.ControllerSuper;
import mlee671.basicfx.controllers.SceneManager;
import mlee671.basicfx.controllers.SceneManager.SceneType;

public class Menu implements GameState {

    private ControllerSuper controller;

    public Menu() {
        controller = SceneManager.getController(SceneType.MAINMENU);
    }

    @Override
    public void enter() {
        // Logic for entering the menu state
    }

    @Override
    public void exit() {
        // Logic for exiting the menu state
    }

    @Override
    public SceneType getScene() {
        return SceneType.MAINMENU;
    }

    @Override
    public ControllerSuper getController() {
        return controller;
    }

    @Override
    public void update() {
        // Logic for updating the menu state
    }
}
