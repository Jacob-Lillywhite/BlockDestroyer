import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    //region Variables
    //region UI Variables
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    //endregion
    //region Game Variables
    // Ball Properties
    static final int BALL_DIAMETER = 20;
    // Paddle Properties
    private static final int paddleWidth = 100;
    private static final int PADDLE_HEIGHT = 15;
    private static final int PADDLE_MAX_WIDTH = 200;
    // Game Properties
    int totalBlocks;
    int level = 1;
    // Game Objects
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle;
    Score score;
    ArrayList<PowerUp> powerUplList = new ArrayList<>();
    ArrayList<Ball> ballList = new ArrayList<>();
    int[][] level1Map = {{1, 1, 1, 1, 1, 1, 1}};
    int[][] level2Map = {{2, 1, 2, 1, 2, 1, 2},
            {1, 2, 1, 2, 1, 2, 1}};
    int[][] level3Map = {{0, 1, 0, 1, 0, 1, 0},
            {2, 0, 2, 0, 2, 0, 2},
            {0, 3, 0, 3, 0, 3, 0}};
    int[][] level4Map = {{0, 0, 3, 0, 3, 0, 0},
            {0, 0, 3, 0, 3, 0, 0},
            {0, 0, 3, 0, 3, 0, 0},
            {3, 0, 0, 0, 0, 0, 3},
            {0, 3, 0, 0, 0, 3, 0},
            {0, 0, 3, 3, 3, 0, 0}};
    int[][] level5Map = {{0, 0, 4, 4, 4, 0, 0},
            {0, 4, 0, 0, 0, 4, 0},
            {4, 0, 4, 0, 4, 0, 4},
            {4, 0, 0, 4, 0, 0, 4},
            {4, 0, 4, 0, 4, 0, 4},
            {0, 4, 0, 0, 0, 4, 0},
            {0, 0, 4, 4, 4, 0, 0}};
    int[][] level6Map = {{0, 0, 6, 6, 6, 0, 0},
            {6, 6, 5, 5, 5, 6, 0},
            {6, 5, 5, 4, 5, 5, 6},
            {5, 4, 4, 3, 4, 4, 5},
            {4, 3, 3, 2, 3, 3, 4},
            {3, 2, 2, 1, 2, 2, 3},
            {2, 1, 1, 0, 1, 1, 2},
            {1, 0, 0, 0, 0, 0, 1}};
    int[][] level7Map = {{6, 0, 5, 0, 4, 0, 3},
            {6, 0, 5, 0, 4, 0, 3},
            {6, 0, 5, 0, 4, 0, 3},
            {6, 0, 5, 0, 4, 0, 3},
            {6, 0, 5, 0, 4, 0, 3},
            {6, 0, 5, 0, 4, 0, 3},
            {6, 0, 5, 0, 4, 0, 3},
            {6, 0, 5, 0, 4, 0, 3}};
    int[][] level8Map = {{0, 1, 2, 2, 2, 1, 0},
            {1, 2, 3, 3, 3, 2, 1},
            {2, 3, 4, 4, 4, 3, 2},
            {3, 4, 5, 5, 5, 4, 3},
            {3, 4, 5, 6, 5, 4, 3},
            {3, 4, 5, 5, 5, 4, 3},
            {2, 3, 4, 4, 4, 3, 2},
            {1, 2, 3, 3, 3, 2, 1},
            {0, 1, 2, 2, 2, 1, 0}};
    int[][] level9Map = {{0, 0, 0, 0, 0, 0, 0},
            {0, 0, 4, 4, 4, 0, 0},
            {0, 4, 0, 0, 0, 4, 0},
            {4, 0, 3, 3, 3, 0, 4},
            {4, 0, 3, 1, 3, 0, 4},
            {4, 0, 3, 3, 3, 0, 4},
            {0, 4, 0, 0, 0, 4, 0},
            {0, 0, 4, 4, 4, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}};
    int[][] level10Map = {{0, 7, 0, 7, 0, 7, 0},
            {7, 0, 6, 0, 6, 0, 7},
            {0, 5, 0, 5, 0, 6, 0},
            {7, 0, 5, 0, 5, 0, 7},
            {0, 6, 0, 4, 0, 6, 0},
            {7, 0, 5, 0, 5, 0, 7},
            {0, 6, 0, 5, 0, 6, 0},
            {7, 0, 6, 0, 6, 0, 7},
            {0, 7, 0, 7, 0, 7, 0}};
    //endregion
    //endregion
    //region Game State Variables
    boolean running = true;
    boolean victory = false;
    //region levelMaps
    private Map map;

    //endregion
    //endregion
    //region GamePanel
    // this is what holds the game itself.
    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        addPaddle();
        addBall();
        addMap(level);
        score = new Score(SCREEN_WIDTH, SCREEN_HEIGHT);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void startGame() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        addPaddle();
        addBall();
        addMap(level);
        score = new Score(SCREEN_WIDTH, SCREEN_HEIGHT);
        gameThread = new Thread(this);
        gameThread.start();
    }

    //endregion
    //region Add Objects
    private void addPaddle() {
        paddle = new Paddle((SCREEN_WIDTH / 2) - (paddleWidth / 2), 550, paddleWidth, PADDLE_HEIGHT);
    }

    private void addBall() {
        ballList.add(new Ball(SCREEN_WIDTH / 2 - BALL_DIAMETER / 2, SCREEN_HEIGHT / 2 - BALL_DIAMETER / 2, BALL_DIAMETER, BALL_DIAMETER, 1, -2));
    }

    private void addMap(int level) {

        switch (level) {
            case 1:
                map = new Map(this.level1Map);
                break;
            case 2:
                map = new Map(level2Map);
                break;
            case 3:
                map = new Map(level3Map);
                break;
            case 4:
                map = new Map(level4Map);
                break;
            case 5:
                map = new Map(level5Map);
                break;
            case 6:
                map = new Map(level6Map);
                break;
            case 7:
                map = new Map(level7Map);
                break;
            case 8:
                map = new Map(level8Map);
                break;
            case 9:
                map = new Map(level9Map);
                break;
            case 10:
                map = new Map(level10Map);
                break;
        }
        totalBlocks = map.getNumBlocks();
    }

    //endregion
    //region Game Creation Methods
    public void paintComponent(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics graphics) {
        paddle.draw(graphics);
        for (Ball ball : ballList) {
            ball.draw(graphics);
        }
        score.draw(graphics);
        map.draw((Graphics2D) graphics);
        for (PowerUp powerUp : powerUplList) {
            powerUp.draw(graphics);
        }
        if (!running) {
            gameOver(graphics);
        }
    }

    //endregion
    //region Collisions
    public void checkCollisions() {
        //region Paddle Border Collisions
        if (paddle.x <= 0) {
            paddle.x = 0;
        }
        if (paddle.x >= (SCREEN_WIDTH - paddle.width)) {
            paddle.x = SCREEN_WIDTH - paddle.width;
        }
        //endregion
        //region Ball Border Collisions
        for (Ball ball : ballList) {
            // TOP BORDER COLLISION
            if (ball.y <= 0) {
                ball.setYDir(-(ball.getYVelocity()));
            }
            // BOTTOM BORDER COLLISION
            if (ball.y >= SCREEN_HEIGHT - BALL_DIAMETER) {
                ballList.remove(ball);
                break;

            }
            // LEFT BORDER COLLISION
            if (ball.x <= 0) {
                ball.setXDir(-(ball.getXVelocity()));
            }
            // RIGHT BORDER COLLISION
            if (ball.x >= SCREEN_WIDTH - BALL_DIAMETER) {
                ball.setXDir(-(ball.getXVelocity()));
            }
            //endregion
            //region Paddle-Ball Collisions
            if (ball.intersects(paddle)) {
                ball.setYVelocity(-(ball.getYVelocity()));
                ball.setXDir(ball.getXVelocity());
                ball.setYDir(ball.getYVelocity());
            }
            //endregion
        }
        //endregion
        //region Map Collisions
        //TODO: Fix Ball-block collision based on x and y values of ball.
        // Block collisions
        A:
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap()[0].length; j++) {
                if (map.getMap()[i][j] > 0) {
                    int blockX = j * map.getBlockWidth() + map.getHorizontalOffset();
                    int blockY = i * map.getBlockHeight() + map.getVerticalOffset();
                    int blockWidth = map.getBlockWidth();
                    int blockHeight = map.getBlockHeight();


                    Rectangle block = new Rectangle(blockX, blockY, blockWidth, blockHeight);
                    // If a block is destroyed, there's a chance PowerUp will drop.
                    for (Ball ball : ballList) {
                        if (map.getMap()[i][j] == 1 && ball.intersects(block)) {
                            random = new Random();
                            if (random.nextInt(10) == 9) {
                                powerUplList.add(new PowerUp(blockX, blockY));
                            }
                        }
                        if (ball.intersects(block)) {
                            map.decrementBlockValue(i, j);
                            totalBlocks--;
                            if (totalBlocks == 0) {
                                level++;
                                if (level < 10) {
                                    addMap(level);
                                } else {
                                    // TODO: VICTORY SCREEN
                                    running = false;
                                }
                            }
                            score.value += 5;
                            if (ball.x <= block.x || ball.x >= block.x + block.width) {
                                ball.setXVelocity(-(ball.getXVelocity()));
                                ball.setXDir(ball.getXVelocity());
                                ball.setYDir(ball.getYVelocity());
                            } else {
                                ball.setYVelocity(-(ball.getYVelocity()));
                                ball.setXDir(ball.getXVelocity());
                                ball.setYDir(ball.getYVelocity());
                            }
                            break A;
                        }
                    }
                }
            }
        }
        //endregion
        //region PowerUp Collisions
        powerUpCheck:
        for (PowerUp powerup : powerUplList) {
            if (powerup.intersects(paddle)) {
                switch (powerup.powerUpType) {
                    case 0:
                        powerUplList.remove(powerup);
                        addBall();
                        break powerUpCheck;
                    case 1:
                        powerUplList.remove(powerup);
                        if (paddle.width < PADDLE_MAX_WIDTH) {
                            paddle.width += 20;
                        }
                        break powerUpCheck;

                }
            }
        }
        if (ballList.isEmpty()) {
            running = false;
        }
        //endregion
    }

    //endregion
    //region Game Movement
    public void move() {
        paddle.move();
        for (Ball ball : ballList) {
            ball.move();
        }
        for (PowerUp powerUp : powerUplList) {
            powerUp.move();
        }
    }

    //endregion
    //region Run (Game Loop)
    @Override
    public void run() {
        // Game Loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double nanoSeconds = 1000000000 / amountOfTicks;
        double delta = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSeconds;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollisions();
                repaint();
                delta--;
            }
        }
    }

    //endregion
    //region GameOver
    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Monospaced", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(graphics.getFont());
        graphics.drawString("GAME OVER", (SCREEN_WIDTH - metrics1.stringWidth("GAME OVER")) / 2, SCREEN_HEIGHT / 2 + SCREEN_HEIGHT / 4);
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Monospaced", Font.BOLD, 25));
        FontMetrics metrics2 = getFontMetrics(graphics.getFont());
        graphics.drawString("Press Spacebar to reset", (SCREEN_WIDTH - metrics2.stringWidth("Press Spacebar to reset")) / 2, SCREEN_HEIGHT / 2 + SCREEN_HEIGHT / 3);
    }

    //endregion
    //region Reset Game
    public void reset() {
        level = 1;
        victory = false;
        ballList.clear();
        powerUplList.clear();
        running = true;
        startGame();
        System.out.println(Arrays.deepToString(level1Map));
    }

    //endregion
    //region Key Listeners
    public class ActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (running) {
                paddle.keyPressed(e);
            }
            if (!running) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    reset();
                }
            }
        }

        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }
    }
    //endregion
}
