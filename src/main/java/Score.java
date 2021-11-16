import java.awt.*;

public class Score extends Rectangle {
    //region Score Variables
    static int SCREEN_WIDTH;
    static int SCREEN_HEIGHT;
    int value;
    //endregion

    //region Score Constructor
    Score(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        Score.SCREEN_WIDTH = SCREEN_WIDTH;
        Score.SCREEN_HEIGHT = SCREEN_HEIGHT;
    }
    //endregion

    //region Draw
    public void draw(Graphics graphics) {
        graphics.setColor(Color.yellow);
        graphics.setFont(new Font("Helvetica", Font.BOLD, 20));
        graphics.drawString("SCORE: " + value, 0, 20);
    }
    //endregion
}
