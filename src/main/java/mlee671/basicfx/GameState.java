package mlee671.basicfx;

import mlee671.basicfx.controllers.ControllerSuper;
import mlee671.basicfx.controllers.SceneManager;
import mlee671.basicfx.controllers.SceneManager.SceneType;

public class GameState {

  private ControllerSuper sceneController;
  private SceneType currentScene;

  /** Constructs a new GameState and initializes the currentScene and Controller. */
  public GameState() {

    currentScene = SceneType.MAINMENU; // Default starting scene
    sceneController = SceneManager.getController(currentScene);
  }

  public SceneType getCurrentScene() {
    return currentScene;
  }

  public ControllerSuper getCurrentController() {
    return sceneController;
  }

  public void setCurrentScene(String scene) {
    this.currentScene = SceneManager.getUiName(scene);
    sceneController = SceneManager.getController(currentScene);
  }

  // public void updateTimer(int timeRemaining) {
  //     sceneController.updateTimer(timeRemaining);
  // }

}
