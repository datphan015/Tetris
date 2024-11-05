package com.mt.tetris;

import com.mt.tetris.tetris_panels.StartupForm;
import com.mt.tetris.tetris_panels.GameForm;
import com.mt.tetris.tetris_panels.LeaderboardForm;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TetrisMain {

    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;
    
    private static TetrisAudio audio = new TetrisAudio();

    public static void playLevelUp() {
        audio.playLvlUp();
    }

    public static void playTitleSong() {
        audio.playTitle();
    }

    public static void playGameOverSong() {
        audio.playGameOver();
    }

    public static void start() {
        gf.initControls();
        gf.startGame();
        gf.setVisible(true);
        audio.stopTitle();
    }

    public static void showLeaderboard() {
        lf.setVisible(true);
    }

    public static void showStartup() {
        sf.setVisible(true);
    }

    public static void gameOver(int score) {

        String playerName = JOptionPane.showInputDialog("Game over!\nPlease enter your name.");
        gf.setVisible(false);
        if (playerName != null) {
            lf.addPlayer(playerName, score);
        }

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                gf = new GameForm();
                sf = new StartupForm();
                lf = new LeaderboardForm();

                sf.setVisible(true);
                audio.playTitle();

            }
        });
    }
}
