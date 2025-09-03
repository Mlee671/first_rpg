package mlee671.basicfx;

import mlee671.basicfx.controllers.ControllerSuper;
import mlee671.basicfx.controllers.SceneManager.SceneType;
import mlee671.basicfx.gamestates.Game1;
import mlee671.basicfx.gamestates.Game2;
import mlee671.basicfx.gamestates.Game2Walk;
import mlee671.basicfx.gamestates.Menu;
import mlee671.basicfx.gamestates.GameState;

public class GameStateContext {

  private GameState currentState;
  private GameState menuState;
  private GameState game1State;
  private GameState game2State;
  private GameState game2WalkState;

  /** Constructs a new GameState and initializes the currentScene and Controller. */
  public GameStateContext() {

    menuState = new Menu();
    game1State = new Game1();
    game2State = new Game2();
    game2WalkState = new Game2Walk();
    currentState = menuState;
  }

  public void update() {
    currentState.update();
  }

  public void setCurrentScene(String scene) {
    currentState.exit();
    this.currentState = changeState(scene);
    currentState.enter();
  }

  private GameState changeState(String scene) {
    switch (scene) {
      case "mainmenu":
        return menuState;
      case "game1":
        return game1State;
      case "game2":
        return game2State;
      case "game2walk":
        return game2WalkState;
      default:
        throw new IllegalArgumentException("Unknown scene: " + scene);
    }
  }
}
