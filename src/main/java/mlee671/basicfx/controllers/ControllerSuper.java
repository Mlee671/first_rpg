package mlee671.basicfx.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import mlee671.basicfx.characters.BaseCharacter;

public class ControllerSuper {

  @FXML protected Pane pane;
  @FXML protected ImageView imgHero1;
  @FXML protected ImageView imgHero2;
  @FXML protected ImageView imgHero3;
  @FXML protected ImageView imgHero4;
  @FXML protected ImageView imgHero5;
  @FXML protected ImageView imgHero6;
  @FXML protected ImageView imgSel;
  @FXML protected Label lblName;
  @FXML protected Label lblHealth;
  @FXML protected Label lblSTR;
  @FXML protected Label lblAGI;
  @FXML protected Label lblWIS;
  @FXML protected Label lblID;
  protected List<Rectangle> glowRects;
  protected boolean characterSelected;
  protected BaseCharacter selectedCharacter;
  protected List<ImageView> Views;
    @FXML protected Rectangle recGlow1;
  @FXML protected Rectangle recGlow2;
  @FXML protected Rectangle recGlow3;
  @FXML protected Rectangle recGlow4;
  @FXML protected Rectangle recGlow5;
  @FXML protected Rectangle recGlow6;
  protected boolean inBattle = false;
  Sequence sequence;
  Sequencer sequencer;
 

  protected static HashMap<Image, BaseCharacter> characterMap;

  // resets selected character when background is clicked
  @FXML
  private void onClickBackground(MouseEvent event) throws IOException {
    if (characterSelected) {
      characterSelected = false;
      pane.setVisible(false);
      glowRects.forEach(rect -> rect.setVisible(false));
    }
  }

  // Handle character click events
  // shows character information on click
  // changes character position on click
  @FXML
  private void onClickCharacter(MouseEvent event) throws IOException {
    if (!characterSelected) {
      ImageView clickedImg = (ImageView) event.getSource();
      selectedCharacter = characterMap.get(clickedImg.getImage());
      if (selectedCharacter != null) {
        characterSelected = true;
        lblName.setText(selectedCharacter.getName());
        lblHealth.setText("Health: " + selectedCharacter.getHealth());
        lblSTR.setText("Strength: " + selectedCharacter.getStr());
        lblAGI.setText("Agility: " + selectedCharacter.getAgi());
        lblWIS.setText("Wisdom: " + selectedCharacter.getWis());
        lblID.setText("ID: " + selectedCharacter.getId());
        imgSel.setImage(clickedImg.getImage());
        pane.setVisible(true);
        if (inBattle) {
            return;
        }
        glowRects.subList(0, Math.min(6, glowRects.size()))
         .forEach(rect -> rect.setVisible(true));
      }
    } else if (!inBattle) {
      ImageView clickedImg = (ImageView) event.getSource();
      if (clickedImg.getImage() != null) {
        BaseCharacter character = characterMap.get(clickedImg.getImage());
        character.setId(selectedCharacter.getId());
      }
      selectedCharacter.setId(Character.getNumericValue(clickedImg.getId().charAt(7)));
      draw();
    }
  }

  // loop through character map and update hero views based on character id
  public void draw() {
    Views.forEach(view -> view.setImage(null));
    getCharacterMap()
        .forEach(
            (image, character) -> {
              Views.get(character.getId() - 1).setImage(image);
            });
  }

  // Get the character map
  protected HashMap<Image, BaseCharacter> getCharacterMap() {
    return characterMap;
  }
}
