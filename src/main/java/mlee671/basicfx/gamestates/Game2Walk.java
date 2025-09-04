package mlee671.basicfx.gamestates;

import mlee671.basicfx.App;
import mlee671.basicfx.controllers.ControllerSuper;
import mlee671.basicfx.controllers.Game2WalkController;
import mlee671.basicfx.controllers.SceneManager;
import mlee671.basicfx.controllers.SceneManager.SceneType;

public class Game2Walk implements GameState {

  private Game2WalkController controller;
  private boolean walking = true;

  public Game2Walk() {
    controller = (Game2WalkController) SceneManager.getController(SceneType.GAME2WALK);
  }

  @Override
  public void enter() {
    controller.onEnter();
    App.startTimeline();
  }

  @Override
  public void exit() {
    App.stopTimeline();
  }

  @Override
  public SceneType getScene() {
    return SceneType.GAME2WALK;
  }

  @Override
  public ControllerSuper getController() {
    return controller;
  }

  @Override
  public void update() {
    controller.pulse();
    // if (walking) {

    //   controller.addStep();

      // if (Math.random() < 0.05) {
      //   controller.startBattle();
      //   walking = false;
      // } else if (Math.random() > 0.95) {
      //   System.out.println("Event triggered!");
      //   /// walking = false;
      //   // controller.startEvent();
      // }

    // } else {
    //   walking = controller.pulse();
    // }
  }
}
