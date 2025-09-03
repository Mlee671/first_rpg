package mlee671.basicfx.controllers;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mlee671.basicfx.characters.BaseCharacter;

public class ControllerSuper {

    protected static HashMap<Image, BaseCharacter> characterMap;

    public void addView(ImageView imageView) {
        // Default implementation (can be overridden by subclasses)
    }

    public void restartGame() {
        // Default implementation (can be overridden by subclasses)
    }

}
