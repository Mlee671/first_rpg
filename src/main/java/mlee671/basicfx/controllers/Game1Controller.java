package mlee671.basicfx.controllers;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import mlee671.basicfx.App;

public class Game1Controller {

  @FXML private Canvas canvas;

  private List<icon> icons;
  private Boolean mouseHeld = false;
  private double mouseX = -1;
    private double mouseY = -1;

  enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }

  public class icon {
    public Circle circle;
    public int x;
    public int y;
    public Direction dir1;
    public Direction dir2;

    public icon(int x, int y) {
      circle = new Circle(3, Color.color(Math.random(), Math.random(), Math.random()));
      this.x = x;
      this.y = y;
      dir1 = Direction.values()[(int) (Math.random() * 4)];
        dir2 = Direction.values()[(int) (Math.random() * 4)];
    }
  }

  @FXML
  private void initialize() {
    icons = new ArrayList<>();
    icons.add(new icon(50, 50));
    icons.add(new icon(100, 100));
  }

  @FXML
  private void onMousePressed(MouseEvent event) {
    mouseHeld = true;
    mouseX = event.getX();
    mouseY = event.getY();
  }

    @FXML
    private void onMouseReleased(MouseEvent event) {
    mouseHeld = false;
    mouseX = -1;
    mouseY = -1;
  }

  @FXML
private void onMouseDragged(MouseEvent event) {
    if (mouseHeld) {
        mouseX = event.getX();
        mouseY = event.getY();
    }
}

  public void onEnter() {
    App.startTimeline(10);
    draw();
  }

  public void onExit() {
    App.stopTimeline();
  }

  public void pulse() {
    if (mouseHeld) {
      icons.add(new icon((int) (mouseX), (int) (mouseY)));
    }
    moveIcons();
    draw();
  }

  private void moveIcons() {
    for (icon ic : icons) {
      switch (ic.dir1) {
        case UP:
          ic.y -= 5;
          if (ic.y < 0) {
            ic.dir1 = Direction.DOWN;
          }
          break;
        case DOWN:
          ic.y += 5;
          if (ic.y > canvas.getHeight()) {
            ic.dir1 = Direction.UP;
          }
          break;
        case LEFT:
          ic.x -= 5;
          if (ic.x < 0) {
            ic.dir1 = Direction.RIGHT;
          }
          break;
        case RIGHT:
          ic.x += 5;
          if (ic.x > canvas.getWidth()) {
            ic.dir1 = Direction.LEFT;
          }
          break;
        default:
          ic.dir1 = Direction.values()[(int) (Math.random() * 4)];
          break;
      }
    }
    for (icon ic : icons) {
      switch (ic.dir2) {
        case UP:
          ic.y -= 5;
          if (ic.y < 0) {
            ic.dir2 = Direction.DOWN;
          }
          break;
        case DOWN:
          ic.y += 5;
          if (ic.y > canvas.getHeight()) {
            ic.dir2 = Direction.UP;
          }
          break;
        case LEFT:
          ic.x -= 5;
          if (ic.x < 0) {
            ic.dir2 = Direction.RIGHT;
          }
          break;
        case RIGHT:
          ic.x += 5;
          if (ic.x > canvas.getWidth()) {
            ic.dir2 = Direction.LEFT;
          }
          break;
        default:
          ic.dir2 = Direction.values()[(int) (Math.random() * 4)];
          break;
      }
    }
  }

  private void draw() {
    var gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    for (icon ic : icons) {
      gc.setFill(ic.circle.getFill());
      gc.fillOval(ic.x, ic.y, ic.circle.getRadius() * 2, ic.circle.getRadius() * 2);
    }
  }
}
