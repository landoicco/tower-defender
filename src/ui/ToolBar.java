package ui;

import objects.Tile;
import scenes.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameStates.MENU;
import static main.GameStates.setGameState;

public class ToolBar extends Bar {

    private MyButton bMenu, bSave;
    private Editing editing;
    private Tile selectedTile;

    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initButtons();
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        } else if (bSave.getBounds().contains(x, y)) {
            editing.saveLevel();
        } else {
            for (MyButton button : tileButtons) {
                if (button.getBounds().contains(x, y)) {
                    selectedTile = editing.getGame().getTileManager().getTile(button.getId());
                    editing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        // Reset mouse over
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        for (MyButton button : tileButtons) {
            button.setMouseOver(false);
        }

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMouseOver(true);
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
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMousePressed(true);
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
        int buttonOffset = 100;

        bMenu = new MyButton("Menu", buttonsXPos, buttonsYPos, buttonsWidth, buttonsHeight);
        bSave = new MyButton("Save", buttonsXPos + buttonOffset, buttonsYPos, buttonsWidth, buttonsHeight);

        int tileOffset = 0;
        int id = 0;
        for (Tile tile : editing.getGame().getTileManager().getTiles()) {
            tileButtons.add(new MyButton(tile.getName(), 250 + tileOffset, this.y, 50, 50, id++));
            tileOffset += 60;
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);
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

    public void draw(Graphics g) {
        // Background
        g.setColor(new Color(212, 34, 198));
        g.fillRect(x, y, width, height);

        // Buttons
        drawButtons(g);
    }

    private void resetButtons() {
        bMenu.resetBooleans();
        bSave.resetBooleans();
        for (MyButton button : tileButtons) {
            button.resetBooleans();
        }
    }

    private BufferedImage getButtonImage(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }
}
