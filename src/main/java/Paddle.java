import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    //region Paddle Variables
    private static final int speed = 8;
    private int xVelocity;
    //endregion

    //region Paddle Constructor
    Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    //endregion

    //region Setters
    public void setXDir(int xDir) {
        xVelocity = xDir;
    }
    //endregion

    //region Movement
    public void move() {
        x += xVelocity;
    }
    //endregion

    //region Draw
    public void draw(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillRect(x, y, width, height);
    }
    //endregion

    //region Key Events Handling
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            setXDir(-speed);
            move();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setXDir(speed);
            move();
        }

    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            setXDir(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setXDir(0);
        }
    }
    //endregion
}
