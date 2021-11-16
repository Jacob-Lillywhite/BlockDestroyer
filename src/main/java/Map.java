import java.awt.*;
import java.util.Arrays;

public class Map {
    //region Map Variables
    private final int[][] map;
    private final int blockWidth;
    private final int horizontalOffset;
    private final int verticalOffset = 50;
    private final int blockHeight = 30;
    private int blockCount = 0;
    //endregion

    //region Row-Col Map Constructor
    public Map(int row, int col) {
        map = new int[row][col];
        // This is just an automated way to create a map for the game to be played.
        // You loop through the 2d array and assign values depending on how many hits a block can take.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = row - i;
            }
        }

        blockWidth = 540 / col;
        horizontalOffset = (GamePanel.SCREEN_WIDTH - 540) / 2;
    }
    //endregion

    //region Prebuilt Map Constructor
    // Constructor that accepts prebuilt maps. This will be useful for designing levels in a 2D array
    // and then loading them up into the game.
    public Map(int[][] map) {

        /*
         Because Java passes 2D arrays be Reference we have to make a work around
         otherwise the game's ability to 'reset' is hardcoded based off reassigning
         level map values. This way we can just have the Map object have a copy of the
         predefined levels and not actually edit them. This is done by giving this local map (this.map)
         the number of rows (arrays) in the map (map) and then using the built-in Arrays.copyOf() method
         which only copies 1D arrays to copy over the individual arrays.
         */
        this.map = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            this.map[i] = Arrays.copyOf(map[i], map[i].length);
        }
        blockWidth = 540 / map[0].length;
        horizontalOffset = (GamePanel.SCREEN_WIDTH - 540) / 2;
    }
    //endregion

    //region Draw
    public void draw(Graphics2D graphics) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    switch (map[i][j]) {
                        case 1:
                            graphics.setColor(Color.red);
                            graphics.fillRect(j * blockWidth + horizontalOffset, i * blockHeight + verticalOffset, blockWidth, blockHeight);
                            break;
                        case 2:
                            // Dark Orange because the native Color Orange is too similar to yellow
                            graphics.setColor(new Color(255, 140, 0));
                            graphics.fillRect(j * blockWidth + horizontalOffset, i * blockHeight + verticalOffset, blockWidth, blockHeight);
                            break;
                        case 3:
                            graphics.setColor(Color.yellow);
                            graphics.fillRect(j * blockWidth + horizontalOffset, i * blockHeight + verticalOffset, blockWidth, blockHeight);
                            break;
                        case 4:
                            graphics.setColor(Color.green);
                            graphics.fillRect(j * blockWidth + horizontalOffset, i * blockHeight + verticalOffset, blockWidth, blockHeight);
                            break;
                        case 5:
                            graphics.setColor(Color.blue);
                            graphics.fillRect(j * blockWidth + horizontalOffset, i * blockHeight + verticalOffset, blockWidth, blockHeight);
                            break;
                        case 6:
                            // Indigo
                            graphics.setColor(new Color(75, 0, 130));
                            graphics.fillRect(j * blockWidth + horizontalOffset, i * blockHeight + verticalOffset, blockWidth, blockHeight);
                            break;
                        case 7:
                            // Violet
                            graphics.setColor(new Color(127, 0, 255));
                            graphics.fillRect(j * blockWidth + horizontalOffset, i * blockHeight + verticalOffset, blockWidth, blockHeight);
                            break;
                    }

                    graphics.setStroke(new BasicStroke(3));
                    graphics.setColor(Color.black);
                    graphics.drawRect(j * blockWidth + horizontalOffset, i * blockHeight + verticalOffset, blockWidth, blockHeight);
                }
            }
        }
    }
    //endregion

    //region Decrement Block Method
    public void decrementBlockValue(int row, int col) {
        map[row][col]--;
    }
    //endregion

    //region Getters

    public int[][] getMap() {
        return this.map;
    }

    public int getBlockWidth() {
        return this.blockWidth;
    }

    public int getBlockHeight() {
        return this.blockHeight;
    }

    public int getHorizontalOffset() {
        return this.horizontalOffset;
    }

    public int getVerticalOffset() {
        return this.verticalOffset;
    }

    public int getNumBlocks() {
        for (int[] ints : map) {
            for (int j = 0; j < map[0].length; j++) {
                if (ints[j] > 0) {
                    blockCount += ints[j];
                }
            }
        }
        return blockCount;
    }
    //endregion
}
