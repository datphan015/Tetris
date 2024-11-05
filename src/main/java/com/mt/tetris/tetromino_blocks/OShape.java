package com.mt.tetris.tetromino_blocks;

import com.mt.tetris.TetrominoBlock;
import java.awt.Color;

public class OShape extends TetrominoBlock {

    public OShape() {
        super(new int[][]{
            {1, 1},
            {1, 1}
        }, Color.YELLOW);
    }
}
