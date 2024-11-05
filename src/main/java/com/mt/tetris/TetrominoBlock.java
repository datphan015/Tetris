package com.mt.tetris;

import java.awt.Color;
import java.util.Random;

public class TetrominoBlock {

    private int[][] shape;
    private int[][][] shapes;
    private int currentRotation;
    Color color;
//    Color[] shapeColors = {Color.MAGENTA, Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW};
    private int x, y;

    public TetrominoBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;

        initShapes();
    }

    private void initShapes() {
        shapes = new int[4][][];

        for (int i = 0; i < 4; i++) {
            int row = shape[0].length;
            int col = shape.length;

            shapes[i] = new int[row][col];

            for (int y = 0; y < row; y++) {
                for (int x = 0; x < col; x++) {
                    shapes[i][y][x] = shape[col - x - 1][y];
                }
            }

            shape = shapes[i];
        }
    }

    public void spawn(int gridWidth) {

        Random r = new Random();

        currentRotation = r.nextInt(shapes.length);
        shape = shapes[currentRotation];

//        Cho block rơi từ ngoài khung chơi
        y = -getHeight() - 1;
        
//        200 x 400, số cột = 10
//        200/10 = 20 cột
//        (20 - 2) / 2 = 9
//        x = (gridWidth - getWidth()) / 2;

//        Tạo block ở vị trí ngẫu nhiên
        x = r.nextInt(gridWidth - getWidth());

//        color = shapeColors[r.nextInt(shapeColors.length)];
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int setX(int newX) {
        return x = newX;

    }

    public int setY(int newY) {
        return y = newY;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void CCWrotate() {
        currentRotation++;
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        shape = shapes[currentRotation];
    }

    public void CWrotate() {
        currentRotation--;
        if (currentRotation < 0) {
            currentRotation = 3;
        }
        shape = shapes[currentRotation];
    }

    public int getBottom() {
        return y + getHeight();
    }

    public int getLeft() {
        return x;
    }

    public int getRight() {
        return x + getWidth();
    }
}
