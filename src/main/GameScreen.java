package main;

import javax.swing.JPanel;
import java.awt.*;

public class GameScreen extends JPanel {

    public GameScreen() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawRect(50, 50, 100, 100);
    }
}
