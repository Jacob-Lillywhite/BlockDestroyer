import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GameFrame() {
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setBackground(Color.black);
        this.setTitle("Block Destroyer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}