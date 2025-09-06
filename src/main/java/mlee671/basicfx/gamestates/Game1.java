package mlee671.basicfx.gamestates;

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
    controller.onEnter();
  }

  @Override
  public void exit() {
    controller.onExit();
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
    controller.pulse();
  }
}
