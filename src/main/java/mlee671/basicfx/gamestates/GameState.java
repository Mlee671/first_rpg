package mlee671.basicfx.gamestates;

import java.io.IOException;
import mlee671.basicfx.controllers.SceneManager.SceneType;

public interface GameState {
  void enter();

  void exit();

  SceneType getScene();

  Object getController();

  void update() throws IOException;
}
