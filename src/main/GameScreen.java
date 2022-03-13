package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;

public class GameScreen extends JPanel {

    private Dimension size;
    private Game game;
    private KeyboardListener keyboardListener;
    private MyMouseListener myMouseListener;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
    }

    public void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g);
    }

    private void setPanelSize() {
        this.size = new Dimension(640, 690);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
}
