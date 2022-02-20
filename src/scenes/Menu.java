package scenes;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Menu extends GameScene implements GameMethods {

    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private BufferedImage img;

    public Menu(Game game) {
        super(game);
        importImage();
        loadSprites();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprites.get(29), 0, 0, null);
    }

    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Image not found/available!");
            e.printStackTrace();
        }
    }

}
