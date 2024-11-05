package com.mt.tetris;

import com.mt.tetris.tetris_panels.GameForm;

public class GameThread extends Thread {

    private GameArea ga;
    private GameForm gf;
    private int score;
    private int level = 1;
    private int scorePerLevel = 3;

    private int dropSpeed = 1000;
    private int speedupPerLevel = 25;

    public GameThread(GameArea ga, GameForm gf) {
        this.setName("GameThread");
        this.ga = ga;
        this.gf = gf;

        gf.updateScore(score);
        gf.updateLevel(level);
    }

    @Override
    public void run() {
        boolean isGameOver = false;
        while (!isGameOver || !isInterrupted()) {
            ga.spawnBlock();

            while (ga.moveBlockDown()) {
                try {
                    Thread.sleep(dropSpeed);
                } catch (InterruptedException ex) {
                    break;
                }
            }

            if (ga.checkBlockOutOfBounds()) {
                TetrisMain.playGameOverSong();

                TetrisMain.gameOver(score);
                isGameOver = true;
                break;
            }

            ga.moveBlockToBackground();
            score += ga.clearLines();
            gf.updateScore(score);

            int lvl = score / scorePerLevel + 1;
            if (lvl > level) {
                level = lvl;
                gf.updateLevel(level);
                TetrisMain.playLevelUp(); 
                dropSpeed -= speedupPerLevel;
            }

            if (dropSpeed < 0) {
                dropSpeed = 250;
            }
        }
    }
}
