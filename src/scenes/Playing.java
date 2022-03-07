package scenes;

import helpers.LevelBuild;
import helpers.LoadSave;
import main.Game;
import managers.TileManager;
import objects.Tile;
import ui.BottomBar;

import java.awt.Graphics;

public class Playing extends GameScene implements GameMethods {

    private int mouseX, mouseY, lastTileX, lastTileY, lastTileId;
    private int[][] lvl;
    private boolean drawSelected;
    private TileManager tileManager;
    private Tile selectedTile;
    private BottomBar bottomBar;

    public Playing(Game game) {
        super(game);
        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 640, 640, 50, this);

        createDefaultLevel();
    }

    @Override
    public void render(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[x][y];
                g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
            }
        }
        bottomBar.draw(g);
        drawSelectedTile(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y > 640) {
            bottomBar.mouseClicked(x, y);
        } else {
            changeTile(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y > 640) {
            bottomBar.mouseMoved(x, y);
            drawSelected = false;
        } else {
            bottomBar.mouseReleased(x, y);
            drawSelected = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y > 640) {
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y < 640) {
            changeTile(x, y);
        }
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
        drawSelected = true;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelected) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY,
                    32, 32, null);
        }
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

    private void createDefaultLevel() {
        int[] array = new int[400];
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
        LoadSave.createLevel("Default Level", array);
    }
}
