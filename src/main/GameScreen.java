package main;

import javax.swing.JPanel;
import java.awt.*;

public class GameScreen extends JPanel {

    private Dimension size;
    private Game game;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g);
    }

    private void setPanelSize() {
        this.size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
}
