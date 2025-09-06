package mlee671.basicfx;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import mlee671.basicfx.controllers.SceneManager;

/** JavaFX App */
public class App extends Application {

  private static Scene scene;
  private static GameStateContext context;
  private static Timeline timeline;
  private static int delay; // milliseconds
  private static Image monsterTileSet;
  private static Image heroTileSet;

  public static void startTimeline(int newDelay) {
    delay = newDelay;
    timeline =
        new Timeline(
            new KeyFrame(
                Duration.millis(delay),
                event -> {
                  try {
                    context.update();
                  } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                  }
                }));
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

    monsterTileSet = new Image(getClass().getResourceAsStream("images/monstertileset.png"));

    heroTileSet = new Image(getClass().getResourceAsStream("images/herotileset.png"));

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

  public static Image getMonsterTileSet() {
    return monsterTileSet;
  }

  public static Image getHeroTileSet() {
    return heroTileSet;
  }
}
