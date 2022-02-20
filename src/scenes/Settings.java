package scenes;

import main.Game;

import java.awt.*;

public class Settings extends GameScene implements GameMethods {

    public Settings(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, 640, 640);
    }
}
