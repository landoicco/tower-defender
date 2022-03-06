package scenes;

import helpers.LevelBuild;
import main.Game;
import managers.TileManager;
import ui.BottomBar;

import java.awt.Graphics;

public class Playing extends GameScene implements GameMethods {

    private int[][] lvl;
    private TileManager tileManager;
    private BottomBar bottomBar;

    public Playing(Game game) {
        super(game);
        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 640, 640, 50, this);
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
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y > 640) {
            bottomBar.mouseClicked(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y > 640) {
            bottomBar.mouseMoved(x, y);
        } else {
            bottomBar.mouseReleased(x, y);
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

    public TileManager getTileManager() {
        return tileManager;
    }
}
