package com.mt.tetris;

import com.mt.tetris.tetromino_blocks.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GameArea extends JPanel {

    //hàng dọc
    private int gridRows;
    //hàng cột
    private int gridColumns;
    //ô của khung lưới
    private int gridCellSize;
    //
    private Color[][] bgBlocks;
    //cục gạch
    private TetrominoBlock tBlock;
    //mảng chứa các dạng
    private TetrominoBlock[] tBlocks;

    //cục gạch tetromino
    //tương ứng với 2 ô
    //tạo chữ L
    public GameArea(JPanel gameArea, int columns) {
        this.setBounds(gameArea.getBounds());
        this.setBackground(gameArea.getBackground());
        this.setBorder(gameArea.getBorder());

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;

        tBlocks = new TetrominoBlock[]{
            new IShape(),
            new JShape(),
            new ZShape(),
            new SShape(),
            new TShape(),
            new OShape(),
            new LShape()};
    }

    public void initBackgroundArray() {
        bgBlocks = new Color[gridRows][gridColumns];
    }

    // tạo block
    public void spawnBlock() {
        Random r = new Random();
        tBlock = tBlocks[r.nextInt(tBlocks.length)];

        tBlock.spawn(gridColumns);
    }

    public boolean checkBlockOutOfBounds() {
//        Dùng vòng lặp checkBottom để kiểm tra khung chơi có chống không và nếu block đã ở ngoài khung chơi
        int[][] shape = tBlock.getShape();
        int height = tBlock.getHeight();
        int width = tBlock.getWidth();

        for (int col = 0; col < width; col++) {
            for (int row = height - 1; row >= 0; row--) {
                int x = col;
                int y = row;
//               Bắt exeception
                if (y < 0 || x < 0) {
                    break;
                }
                if (tBlock.getY() < 0 && bgBlocks[y][x] != null) {
                    System.out.println("Block is out by " + tBlock.getY());
                    tBlock = null;
                    return true;
                }

            }
        }
        return false;
    }

    private boolean checkBottom() {
//        Kiểm tra cục gạch chạm đáy khung chơi không

        if (tBlock.getBottom() == gridRows) {
            return false;
        }

        int[][] shape = tBlock.getShape();
        int height = tBlock.getHeight();
        int width = tBlock.getWidth();

        for (int col = 0; col < width; col++) {
            for (int row = height - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + tBlock.getX();
                    int y = row + tBlock.getY() + 1;
                    if (y < 0) {
                        break;
                    }
                    if (bgBlocks[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    private boolean checkLeft() {
        int[][] shape = tBlock.getShape();
        int height = tBlock.getHeight();
        int width = tBlock.getWidth();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] != 0) {
                    int x = col + tBlock.getX() - 1;
                    int y = row + tBlock.getY();
//                    Bắt exeception
                    if (y < 0 || x < 0) {
                        break;
                    }
                    if (bgBlocks[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }

        return tBlock.getLeft() != 0;
    }

    private boolean checkRight() {
        int[][] shape = tBlock.getShape();
        int height = tBlock.getHeight();
        int width = tBlock.getWidth();

        for (int row = 0; row < height; row++) {
            for (int col = width - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + tBlock.getX() + 1;
                    int y = row + tBlock.getY();
//                    Bắt exeception
                    if (y < 0 || x < getWidth()) {
                        break;
                    }
                    if (bgBlocks[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }

        return tBlock.getRight() != gridColumns;
    }

    public int clearLines() {
        int lineCleared = 0;
        boolean lineFilled;

        for (int row = gridRows - 1; row >= 0; row--) {
            lineFilled = true;
            for (int col = 0; col < gridColumns; col++) {
                if (bgBlocks[row][col] == null) {
                    lineFilled = false;
                    break;
                }
            }
            if (lineFilled) {
                lineCleared++;
                clearLine(row);
                shiftBlocksDown(row);
                clearLine(0);

                row++;

                repaint();
            }
        }

        return lineCleared;
    }

    private void clearLine(int row) {
        for (int i = 0; i < gridColumns; i++) {
            bgBlocks[row][i] = null;
        }
    }

    private void shiftBlocksDown(int row) {
        for (int r = row; r > 0; r--) {
            for (int c = 0; c < gridColumns; c++) {
                bgBlocks[r][c] = bgBlocks[r - 1][c];
            }
        }
    }

    public boolean moveBlockDown() {
        if (checkBottom() == false) {
//            moveBlockToBackground();
//            clearLines();
            return false;
        }

        tBlock.moveDown();
        repaint();

        return true;
    }

    public void moveBlockLeft() {
        if (tBlock == null) {
            return;
        }
        if (!checkLeft()) {
            return;
        }
        tBlock.moveLeft();
        repaint();
    }

    public void moveBlockRight() {
        if (tBlock == null) {
            return;
        }
        if (!checkRight()) {
            return;
        }
        tBlock.moveRight();

        repaint();
    }

    public void hardDropBlock() {
        if (tBlock == null) {
            return;
        }
        while (checkBottom()) {
            tBlock.moveDown();
        }
        repaint();
    }

    public void softDropBlock() {
        if (tBlock == null) {
            return;
        }
        if (checkBottom()) {
            tBlock.moveDown();
        }
        repaint();
    }

    public void rotateBlockCW() {

        if (tBlock == null) {
            return;
        }
        tBlock.CWrotate();

        checkBlockBoundary();

        repaint();

    }

    public void rotateBlockCCW() {

        if (tBlock == null) {
            return;
        }
        tBlock.CCWrotate();

        checkBlockBoundary();

        repaint();

    }

    private void checkBlockBoundary() {
        if (tBlock.getLeft() < 0) {
            tBlock.setX(0);
            System.out.println("Tetromino went out of left bound");
        }
        if (tBlock.getRight() < 0 || tBlock.getRight() > gridColumns) {
            tBlock.setX(gridColumns - tBlock.getWidth());
            System.out.println("Tetromino went out of right bound");
        }
        if (tBlock.getBottom() < 0 || tBlock.getBottom() > gridRows) {
            tBlock.setY(gridRows - tBlock.getHeight());
            System.out.println("Tetromino went out of bottom bound");
        }
    }

// vẽ block
    private void drawBlock(Graphics g) {
        if (tBlock == null) {
            return;
        }
        int[][] shape = tBlock.getShape();
        int height = tBlock.getHeight();
        int width = tBlock.getWidth();
        Color shapeColor = tBlock.getColor();

        //vòng lặp qua hàng ngang
        for (int row = 0; row < height; row++) {
            //vòng lặp theo hàng dọc
            for (int col = 0; col < width; col++) {
                //1 = có màu, 0 = không tô màu
                if (shape[row][col] == 1) {
                    //getX và getY là tọa độ dùng để di chuyển gạch thay vì chỉ mặc định ở bên đỉnh trái
                    int x = (tBlock.getX() + col) * gridCellSize;
                    int y = (tBlock.getY() + row) * gridCellSize;

                    drawGridSquare(g, shapeColor, x, y);
                }
            }
        }
    }

    public void moveBlockToBackground() {
        int[][] shape = tBlock.getShape();
        int height = tBlock.getHeight();
        int width = tBlock.getWidth();

        int xPos = tBlock.getX();
        int yPos = tBlock.getY();

        Color color = tBlock.getColor();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (shape[row][column] == 1 && tBlock.getX() >= 0 && tBlock.getY() >= 0) {
                    bgBlocks[row + yPos][column + xPos] = color;
                }
            }
        }
    }

    private void drawBackground(Graphics g) {
        Color color;

        for (int row = 0; row < gridRows; row++) {
            for (int column = 0; column < gridColumns; column++) {
                color = bgBlocks[row][column];

                if (color != null) {
                    int x = column * gridCellSize;
                    int y = row * gridCellSize;

                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }

    private void drawGridSquare(Graphics g, Color color, int x, int y) {
        //tô gạch
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        //tô grid
        g.setColor(Color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBlock(g);
        drawBackground(g);
    }
}
