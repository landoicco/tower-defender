package main;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame {

    private GameScreen gameScreen;
    private BufferedImage img;

    public Game() {
        importImage();
        setSize(640, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        gameScreen = new GameScreen(img);
        add(gameScreen);
    }

    public static void main(String[] args) {
        System.out.println("Hola Mundo!");
        new Game();
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
