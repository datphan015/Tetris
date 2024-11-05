package com.mt.tetris;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TetrisAudio {

    //relative filepath from Tetris folder
    private final String audioFolder = "TetrisSoundeffects" + File.separator;
    private final String levelUpPath = audioFolder + "lvlUp.wav";
    private final String gameOverPath = audioFolder + "gameOver.wav";
    private final String titlePath = audioFolder + "title.wav";

    private Clip lvlUpSound, titleSound, gameOverSound;

    public TetrisAudio() {
        try {
            lvlUpSound = AudioSystem.getClip();
            titleSound = AudioSystem.getClip();
            gameOverSound = AudioSystem.getClip();

            lvlUpSound.open(AudioSystem.getAudioInputStream(new File(levelUpPath).getAbsoluteFile()));
            titleSound.open(AudioSystem.getAudioInputStream(new File(titlePath).getAbsoluteFile()));
            gameOverSound.open(AudioSystem.getAudioInputStream(new File(gameOverPath).getAbsoluteFile()));
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(TetrisAudio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void playLvlUp() {
        lvlUpSound.setFramePosition(120);
        lvlUpSound.start();
    }

    public void playTitle() {
        titleSound.start();
    }

    public void stopTitle() {
        titleSound.stop();
    }

    public void playGameOver() {
        gameOverSound.setFramePosition(0);
        gameOverSound.start();
    }
}
