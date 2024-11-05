package com.mt.tetris.tetromino_blocks;

import com.mt.tetris.TetrominoBlock;
import java.awt.Color;

public class JShape extends TetrominoBlock {

    public JShape() {
        super(new int[][]{
            {0, 1},
            {0, 1},
            {1, 1}
        }, Color.BLUE);
    }

}
