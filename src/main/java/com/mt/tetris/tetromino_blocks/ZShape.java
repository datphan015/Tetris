package com.mt.tetris.tetromino_blocks;

import com.mt.tetris.TetrominoBlock;
import java.awt.Color;

public class ZShape extends TetrominoBlock {

    public ZShape() {
        super(new int[][]{
            {1, 1, 0},
            {0, 1, 1}
        }, Color.RED);
    }
}
