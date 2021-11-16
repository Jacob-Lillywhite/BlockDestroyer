import java.awt.*;

public class Ball extends Rectangle {
    //region Ball Variables
    private int xVelocity;
    private int yVelocity;
    //endregion

    //region Ball Constructor
    Ball(int x, int y, int width, int height, int xVelocity, int yVelocity) {
        super(x, y, width, height);
        int initialSpeed = 2;
        setXDir(initialSpeed * xVelocity);
        setYDir(initialSpeed * yVelocity);

    }
    //endregion

    //region Getters
    public int getXVelocity() {
        return this.xVelocity;
    }

    //region Setters
    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }
    //endregion

    public int getYVelocity() {
        return this.yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setYDir(int yDir) {
        yVelocity = yDir;
    }

    public void setXDir(int xDir) {
        xVelocity = xDir;
    }
    //endregion

    //region Movement
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
    //endregion

    //region Draw
    public void draw(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillOval(x, y, width, height);
    }
    //endregion
}
