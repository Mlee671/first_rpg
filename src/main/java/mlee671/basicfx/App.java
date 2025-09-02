package mlee671.basicfx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mlee671.basicfx.controllers.SceneManager;

/** JavaFX App */
public class App extends Application {

  private static Scene scene;
  private GameState context;

  @Override
  public void start(Stage stage) throws IOException {

    for (SceneManager.SceneType type : SceneManager.SceneType.values()) {
      FXMLLoader fxmlLoader =
          new FXMLLoader(App.class.getResource("fxml/" + type.name().toLowerCase() + ".fxml"));
      SceneManager.loadController(type, fxmlLoader.getController());
      SceneManager.loadScenes(type, fxmlLoader.load());
    }
    context = new GameState();
    scene = new Scene(SceneManager.getScene(SceneManager.SceneType.MAINMENU));
    stage.setScene(scene);
    stage.show();
  }

  public static void openScene(Scene newScene, String name) throws IOException {
    scene = newScene;
    scene.setRoot(SceneManager.getScene(SceneManager.SceneType.valueOf(name.toUpperCase())));
  }

//   private static Parent loadFXML(String fxml) throws IOException {
//     FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
//     return fxmlLoader.load();
//   }

  public static void main(String[] args) {
    launch();
  }
}
