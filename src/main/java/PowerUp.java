import java.awt.*;
import java.util.Random;

public class PowerUp extends Rectangle {
    static final int POWERUP_WIDTH = 20;
    static final int POWERUP_HEIGHT = 20;
    int yVelocity = 2;
    int powerUpType;
    Random random;

    //The class constructor will make the PowerUp object at the position (x,y).
    PowerUp(int x, int y) {
        super(x, y, POWERUP_WIDTH, POWERUP_HEIGHT);
        // We'll use a random Integer to determine the kind of PowerUp.
        random = new Random();
        powerUpType = random.nextInt(2);
    }

    public void move() {
        // The PowerUp falls down the screen at a fixed rate.
        y += yVelocity;
    }

    public void draw(Graphics graphics) {

        switch (powerUpType) {
            case 0:
                // Extra ball
                graphics.setColor(Color.white);
                graphics.fillRect(x, y, POWERUP_WIDTH, POWERUP_HEIGHT);
                break;
            case 1:
                graphics.setColor(Color.blue);
                graphics.fillRect(x, y, POWERUP_WIDTH, POWERUP_HEIGHT);
                break;
        }
    }
}
