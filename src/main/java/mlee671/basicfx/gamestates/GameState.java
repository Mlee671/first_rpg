package mlee671.basicfx.gamestates;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import mlee671.basicfx.controllers.SceneManager.SceneType;

public interface GameState {
  void enter() throws InvalidMidiDataException, MidiUnavailableException;

  void exit();

  SceneType getScene();

  Object getController();

  void update() throws IOException;
}
