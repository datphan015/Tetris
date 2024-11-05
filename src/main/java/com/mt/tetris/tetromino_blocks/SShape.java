package com.mt.tetris.tetromino_blocks;

import com.mt.tetris.TetrominoBlock;
import java.awt.Color;

public class SShape extends TetrominoBlock {

    public SShape() {
        super(new int[][]{
            {0, 1, 1},
            {1, 1, 0}
        }, Color.GREEN);
    }
}
