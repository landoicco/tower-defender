package scenes;

import main.Game;
import ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static main.GameStates.*;

public class Menu extends GameScene implements GameMethods {

    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private MyButton bPlaying, bSettings, bQuit;
    private BufferedImage img;

    public Menu(Game game) {
        super(game);
        importImage();
        loadSprites();
        initButtons();
    }

    private void initButtons() {
        int buttonsXPos = 250;
        int buttonsYPos = 250;
        int buttonsWidth = 100;
        int buttonsHeight = 30;
        int offset = 40;

        bPlaying = new MyButton("Play", buttonsXPos, buttonsYPos, buttonsWidth, buttonsHeight);
        bSettings = new MyButton("Settings", buttonsXPos, buttonsYPos + offset, buttonsWidth, buttonsHeight);
        bQuit = new MyButton("Quit", buttonsXPos, buttonsYPos + (offset * 2), buttonsWidth, buttonsHeight);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            setGameState(PLAYING);
        }
        if (bSettings.getBounds().contains(x, y)) {
            setGameState(SETTINGS);
        }
        if (bQuit.getBounds().contains(x, y)) {
            // Close window
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        resetButtons();
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMouseOver(true);
        }
        if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMouseOver(true);
        }
        if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        }
        if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePressed(true);
        }
        if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
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
