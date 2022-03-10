package scenes;

import main.Game;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.*;

public class Menu extends GameScene implements GameMethods {

    private MyButton bPlaying, bEditing, bSettings, bQuit;

    public Menu(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int buttonsXPos = 250;
        int buttonsYPos = 250;
        int buttonsWidth = 100;
        int buttonsHeight = 30;
        int offset = 40;

        bPlaying = new MyButton("Play", buttonsXPos, buttonsYPos, buttonsWidth, buttonsHeight);
        bEditing = new MyButton("Edit", buttonsXPos, buttonsYPos + offset, buttonsWidth, buttonsHeight);
        bSettings = new MyButton("Settings", buttonsXPos, buttonsYPos + (offset * 2), buttonsWidth, buttonsHeight);
        bQuit = new MyButton("Quit", buttonsXPos, buttonsYPos + (offset * 3), buttonsWidth, buttonsHeight);
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
        if (bEditing.getBounds().contains(x, y)) {
            setGameState(EDITING);
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
        if (bEditing.getBounds().contains(x, y)) {
            bEditing.setMouseOver(true);
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
        if (bEditing.getBounds().contains(x, y)) {
            bEditing.setMousePressed(true);
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

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bEditing.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEditing.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }
}
