package mlee671.basicfx.gamestates;

import mlee671.basicfx.controllers.ControllerSuper;
import mlee671.basicfx.controllers.SceneManager.SceneType;

public interface GameState {
    void enter();
    void exit();
    SceneType getScene();
    ControllerSuper getController();
    void update();
}
