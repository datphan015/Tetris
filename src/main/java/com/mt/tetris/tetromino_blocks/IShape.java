package com.mt.tetris.tetromino_blocks;

import com.mt.tetris.TetrominoBlock;
import java.awt.Color;

public class IShape extends TetrominoBlock {

    public IShape() {
        super(new int[][]{
            {
                1,
                1,
                1,
                1
            }
        }, new Color(51, 255, 255));
    }
}
