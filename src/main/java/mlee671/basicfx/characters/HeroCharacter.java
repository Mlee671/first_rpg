package mlee671.basicfx.characters;

import javafx.scene.image.Image;
import mlee671.basicfx.App;

public class HeroCharacter extends BaseCharacter {

  private String type = "hero";

  public HeroCharacter(int id) {
    super(id, "hero");
    image = getTile(tileCoords);
    this.health += 100;
    this.currentHealth += 100;
  }

  // Get the tile image for a specific character from tile map
  private Image getTile(int[] coords) {
    int x = coords[0];
    int y = coords[1];
    // Logic to get the tile image from the tileset
    return new javafx.scene.image.WritableImage(
        App.getHeroTileSet().getPixelReader(), x * tileSize, y * tileSize, tileSize, tileSize);
  }
}
