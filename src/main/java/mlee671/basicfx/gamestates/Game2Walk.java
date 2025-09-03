package mlee671.basicfx.gamestates;

import mlee671.basicfx.App;
import mlee671.basicfx.controllers.ControllerSuper;
import mlee671.basicfx.controllers.Game2WalkController;
import mlee671.basicfx.controllers.SceneManager;
import mlee671.basicfx.controllers.SceneManager.SceneType;

public class Game2Walk implements GameState {

    private Game2WalkController controller;
    private int count;
    private boolean walking = true;
    private int delay = 20;

    public Game2Walk() {
        controller = (Game2WalkController) SceneManager.getController(SceneType.GAME2WALK);
    }

    @Override
    public void enter() {
        controller.draw();
        count = 0;
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
        count++;
        if (walking) {
            if (count > delay) {
                controller.addStep();
                count = 0;
                if (Math.random() < 0.5) {
                    walking = false;
                    controller.startBattle();
                } else if (Math.random() > 0.95) {
                    System.out.println("Event triggered!");
                    ///walking = false;
                    //controller.startEvent();
                }
            }
        } else if (count > delay) {
            count = 0;
            walking = controller.pulse();
        }
        controller.draw();
    }
}
