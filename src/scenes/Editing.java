package scenes;

import helpers.LoadSave;
import main.Game;
import objects.Tile;
import ui.ToolBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Editing extends GameScene implements GameMethods {

    private int mouseX, mouseY, lastTileX, lastTileY, lastTileId;
    private int[][] lvl;
    private boolean drawSelected;
    private Tile selectedTile;
    private ToolBar toolBar;

    public Editing(Game game) {
        super(game);
        toolBar = new ToolBar(0, 640, 640, 50, this);
    }

    @Override
    public void render(Graphics g) {
        if (lvl == null) {
            loadDefaultLevel();
        }
        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y > 640) {
            toolBar.mouseClicked(x, y);
        } else {
            changeTile(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y > 640) {
            toolBar.mouseMoved(x, y);
            drawSelected = false;
        } else {
            toolBar.mouseReleased(x, y);
            drawSelected = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y < 640) {
            changeTile(x, y);
        }
    }

    public void saveLevel() {
        // Hacer que cambie de nombre
        LoadSave.saveLevel("default_level", lvl);
        getGame().getPlaying().setLevel(lvl);
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
        drawSelected = true;
    }

    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;

            if (lastTileX == tileX && lastTileY == tileY &&
                    lastTileId == selectedTile.getId()) {
                return;
            }

            lastTileId = selectedTile.getId();
            lastTileX = tileX;
            lastTileY = tileY;
            lvl[tileX][tileY] = selectedTile.getId();
        }
    }

    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[x][y];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelected) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY,
                    32, 32, null);
        }
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.getLevelData("default_level");
    }

    private BufferedImage getSprite(int spriteID) {
        return getGame().getTileManager().getSprite(spriteID);
    }

}
