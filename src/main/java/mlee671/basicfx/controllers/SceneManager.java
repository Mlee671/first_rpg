package mlee671.basicfx.controllers;

import java.util.HashMap;
import javafx.scene.Parent;

public class SceneManager {

  public enum SceneType {
    MAINMENU,
    GAME1,
    GAME2,
    GAME2WALK
  }

  private static HashMap<SceneType, Parent> scenes = new HashMap<>();
  private static HashMap<SceneType, Object> controllers = new HashMap<>();

  public static void loadScenes(SceneType type, Parent parent) {
    scenes.put(type, parent);
  }

  public static Parent getScene(SceneType type) {
    return scenes.get(type);
  }

  public static void loadController(SceneType type, Object controller) {
    controllers.put(type, controller);
  }

  public static Object getController(SceneType type) {
    return controllers.get(type);
  }

  public static SceneType getUiName(String scene) {
    return SceneType.valueOf(scene.toUpperCase());
  }
}
