package scenes;

import helpers.LoadSave;
import main.Game;
import ui.ActionBar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Playing extends GameScene implements GameMethods {

    private int mouseX, mouseY;
    private int[][] lvl;
    private ActionBar actionBar;

    public Playing(Game game) {
        super(game);
        actionBar = new ActionBar(0, 640, 640, 50, this);

    }

    @Override
    public void render(Graphics g) {
        if (lvl == null) {
            loadDefaultLevel();
        }
        drawLevel(g);
        actionBar.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y > 640) {
            actionBar.mouseClicked(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y > 640) {
            actionBar.mouseMoved(x, y);
        } else {
            actionBar.mouseReleased(x, y);
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y > 640) {
            actionBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private BufferedImage getSprite(int spriteID) {
        return getGame().getTileManager().getSprite(spriteID);
    }

    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[x][y];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.getLevelData("default_level");
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }
}
