package mlee671.basicfx.controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import mlee671.basicfx.App;
import mlee671.basicfx.characters.BaseCharacter;
import mlee671.basicfx.characters.HeroCharacter;

public class Game2Controller extends ControllerSuper {

  @FXML private Button btnRoll;
  @FXML private Button btnMenu;


  @FXML
  private void initialize() throws InvalidMidiDataException, IOException, MidiUnavailableException {
    characterMap = new HashMap<>();
    Views = List.of(imgHero1, imgHero2, imgHero3, imgHero4, imgHero5, imgHero6);
    glowRects = List.of(recGlow1, recGlow2, recGlow3, recGlow4, recGlow5, recGlow6);
    characterSelected = false;
    sequence = MidiSystem.getSequence(getClass().getResourceAsStream("/mlee671/basicfx/music/city.MID"));
    sequencer = MidiSystem.getSequencer();
  }

  public void onEnter() {
    try {
      sequencer.open();
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException | MidiUnavailableException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    sequencer.start();
    onReroll();
  }

  public void onExit() {
    if (sequencer != null && sequencer.isOpen()) {
      sequencer.stop();
      sequencer.close();
    }
  }

  // Handle reroll button click
  // generates and stores new characters and images
  // calls draw method
  @FXML
  public void onReroll() {
    characterMap.clear();
    for (int i = 1; i <= 4; i++) {
      BaseCharacter character = new HeroCharacter(i);
      characterMap.put(character.getImage(), character);
    }
    draw();
    pane.setVisible(false);
  }

  // Handle menu button click
  @FXML
  private void onMenu(ActionEvent event) throws IOException {
    Button btn = (Button) event.getSource();
    Scene scene = btn.getScene();
    App.openScene(scene, "mainmenu");
  }

  @FXML
  private void onNext(ActionEvent event) throws IOException {
    Button btn = (Button) event.getSource();
    Scene scene = btn.getScene();
    App.openScene(scene, "game2walk");
  }
}
