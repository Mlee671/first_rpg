package mlee671.basicfx;

import java.io.IOException;
import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mlee671.basicfx.controllers.SceneManager;

/** JavaFX App */
public class App extends Application {

  private static Scene scene;
  private static GameStateContext context;
  private static Timeline timeline;

  public static void startTimeline() {
    timeline = new Timeline(
        new KeyFrame(Duration.seconds(0.1), event -> {
            context.update();
        })
    );
    timeline.setCycleCount(Timeline.INDEFINITE); // Repeat forever
    timeline.play();
  }

  public static void stopTimeline() {
    if (timeline != null) {
      timeline.stop();
    }
  }

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {

    for (SceneManager.SceneType type : SceneManager.SceneType.values()) {
      FXMLLoader fxmlLoader =
          new FXMLLoader(App.class.getResource("fxml/" + type.name().toLowerCase() + ".fxml"));
      SceneManager.loadScenes(type, fxmlLoader.load());
      SceneManager.loadController(type, fxmlLoader.getController());
    }
    context = new GameStateContext();
    scene = new Scene(SceneManager.getScene(SceneManager.SceneType.MAINMENU), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public static void openScene(Scene newScene, String name) throws IOException {
    scene = newScene;
    context.setCurrentScene(name);
    scene.setRoot(SceneManager.getScene(SceneManager.SceneType.valueOf(name.toUpperCase())));
  }
}
