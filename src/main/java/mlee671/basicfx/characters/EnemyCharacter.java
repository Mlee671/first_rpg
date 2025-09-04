package mlee671.basicfx.characters;

import javafx.scene.image.Image;
import mlee671.basicfx.App;

public class EnemyCharacter extends BaseCharacter {

  private String type = "enemy";

  public EnemyCharacter(int id) {
    super(id, "enemy");
    image = getTile(tileCoords);
  }

  // Get the tile image for a specific character from tile map
  private Image getTile(int[] coords) {
    int x = coords[0];
    int y = coords[1];
    // Logic to get the tile image from the tileset
    return new javafx.scene.image.WritableImage(
        App.getMonsterTileSet().getPixelReader(), x * tileSize, y * tileSize, tileSize-2, tileSize);
  }
}
