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
    private Tile selectedTile;

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
        } else {
            for (MyButton button : tileButtons) {
                if (button.getBounds().contains(x, y)) {
                    selectedTile = playing.getTileManager().getTile(button.getId());
                    playing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        // Reset mouse over
        bMenu.setMouseOver(false);
        for (MyButton button : tileButtons) {
            button.setMouseOver(false);
        }

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else {
            for (MyButton button : tileButtons) {
                if (button.getBounds().contains(x, y)) {
                    button.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        } else {
            for (MyButton button : tileButtons) {
                if (button.getBounds().contains(x, y)) {
                    button.setMousePressed(true);
                    return;
                }
            }
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
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 590, this.y, 50, 50, null);
            g.setColor(Color.BLACK);
            g.drawRect(590, this.y, 50, 50);
        }
    }

    private void drawTileButtons(Graphics g) {
        for (MyButton button : tileButtons) {
            // Draw sprite
            g.drawImage(getButtonImage(button.getId()),
                    button.getXPos(), button.getYPos(),
                    button.getWidth(), button.getHeight(), null);

            // Mouse over
            if (button.isMouseOver()) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }
            // Mouse pressed
            if (button.isMousePressed()) {
                g.setColor(Color.CYAN);
            }
            g.drawRect(button.getXPos(), button.getYPos(),
                    button.getWidth(), button.getHeight());
        }
    }

    private BufferedImage getButtonImage(int id) {
        return playing.getTileManager().getSprite(id);
    }

    private void resetButtons() {
        bMenu.resetBooleans();
        for (MyButton button : tileButtons) {
            button.resetBooleans();
        }
    }
}
