package managers;

import helpers.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    private Tile GRASS, WATER, ROAD;
    private BufferedImage atlas;
    private ArrayList<Tile> tiles = new ArrayList();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    private void createTiles() {
        tiles.add(GRASS = new Tile(getSprite(8, 1)));
        tiles.add(WATER = new Tile(getSprite(0, 6)));
        tiles.add(ROAD = new Tile(getSprite(9, 0)));
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }

}
