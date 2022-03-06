package ui;

import objects.Tile;
import scenes.Playing;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameStates.MENU;
import static main.GameStates.setGameState;

public class BottomBar {
    private int width, height, x, y;
    private MyButton bMenu;
    private Playing playing;

    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    public BottomBar(int x, int y, int width, int height, Playing playing) {
        this.width = width;
        this.height = height;
        this.playing = playing;
        this.x = x;
        this.y = y;
        initButtons();
    }

    public void draw(Graphics g) {
        // Background
        g.setColor(new Color(212, 34, 198));
        g.fillRect(x, y, width, height);

        // Buttons
        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        resetButtons();
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        }
    }

    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void initButtons() {
        int buttonsXPos = 0;
        int buttonsYPos = 650;
        int buttonsWidth = 100;
        int buttonsHeight = 30;
        //int offset = 40;

        bMenu = new MyButton("Menu", buttonsXPos, buttonsYPos, buttonsWidth, buttonsHeight);

        int offset = 0;
        int id = 0;
        for (Tile tile : playing.getTileManager().getTiles()) {
            tileButtons.add(new MyButton(tile.getName(), 250 + offset, this.y, 50, 50, id++));
            offset += 60;
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        drawTileButtons(g);
    }

    private void drawTileButtons(Graphics g) {
        for (MyButton button : tileButtons) {
            button.draw(g);
            g.drawImage(getButtonImage(button.getId()),
                    button.getXPos(), button.getYPos(),
                    button.getWidth(), button.getHeight(), null);
        }
    }

    private BufferedImage getButtonImage(int id) {
        return playing.getTileManager().getSprite(id);
    }

    private void resetButtons() {
        bMenu.resetBooleans();
    }
}
