package com.mt.tetris.tetromino_blocks;

import com.mt.tetris.TetrominoBlock;
import java.awt.Color;

public class TShape extends TetrominoBlock {

    public TShape() {
        super(new int[][]{
            {1, 1, 1},
            {0, 1, 0}
        }, Color.MAGENTA);
    }
}
