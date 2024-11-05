package com.mt.tetris.tetromino_blocks;

import com.mt.tetris.TetrominoBlock;
import java.awt.Color;

public class LShape extends TetrominoBlock {

    public LShape() {
        super(new int[][]{
            {1, 0},
            {1, 0},
            {1, 1}
        }, Color.ORANGE);
    }

}
