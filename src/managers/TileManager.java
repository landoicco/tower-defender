package managers;

import helpers.ImageFix;
import helpers.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    private Tile GRASS, WATER, H_ROAD, V_ROAD, BL_WATER_CORNER, TL_WATER_CORNER, BR_WATER_CORNER, TR_WATER_CORNER;
    private Tile TOP_WATER, BOTTOM_WATER, LEFT_WATER, RIGHT_WATER, TL_ISLAND, TR_ISLAND, BR_ISLAND, BL_ISLAND;
    private Tile TL_ROAD_CORNER, TR_ROAD_CORNER, BL_ROAD_CORNER, BR_ROAD_CORNER;
    private BufferedImage atlas;
    private ArrayList<Tile> tiles = new ArrayList();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(9, 0), id++, "Grass"));
        tiles.add(WATER = new Tile(getSprite(0, 0), id++, "Water"));
        tiles.add(H_ROAD = new Tile(getSprite(8, 0), id++, "Horizontal_Road"));
        tiles.add(V_ROAD = new Tile(ImageFix.getRotatedImage(getSprite(8, 0), 90), id++,
                "Vertical_Road"));

        // Water corners
        tiles.add(BL_WATER_CORNER = new Tile(ImageFix.buildImage(getImages(0, 0, 5, 0)),
                id++, "BL_Water_Corner"));
        tiles.add(TL_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(
                getImages(0, 0, 5, 0), 90, 1),
                id++, "TL_Water_Corner"));

        tiles.add(BR_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(
                getImages(0, 0, 5, 0), 180, 1),
                id++, "BR_Water_Corner"));
        tiles.add(TR_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(
                getImages(0, 0, 5, 0), 270, 1),
                id++, "TR_Water_Corner"));

        // Water and ground
        tiles.add(TOP_WATER = new Tile(ImageFix.buildImage(
                getImages(0, 0, 6, 0)), id++, "Top_Water"));
        tiles.add(BOTTOM_WATER = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 6, 0),
                180, 1), id++, "Bottom_Water"));
        tiles.add(LEFT_WATER = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 6, 0),
                270, 1), id++, "Left_Water"));
        tiles.add(RIGHT_WATER = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 6, 0),
                90, 1), id++, "Right_Water"));

        // Island corners
        tiles.add(TL_ISLAND = new Tile(ImageFix.buildImage(
                getImages(0, 0, 4, 0)), id++, "Top_Left_Island"));
        tiles.add(TR_ISLAND = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 4, 0),
                90, 1), id++, "Top_Right_Island"));
        tiles.add(BR_ISLAND = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 4, 0),
                180, 1), id++, "Bottom_Right_Island"));
        tiles.add(BL_ISLAND = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 4, 0),
                270, 1), id++, "Bottom_Left_Island"));

        // Road corners
        tiles.add(TL_ROAD_CORNER = new Tile(ImageFix.buildImage(
                getImages(0, 0, 7, 0)), id++, "Top_Left_Road"));
        tiles.add(TR_ROAD_CORNER = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 7, 0),
                90, 1), id++, "Top_Right_Road"));
        tiles.add(BR_ROAD_CORNER = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 7, 0),
                180, 1), id++, "Bottom_Right_Road"));
        tiles.add(BL_ROAD_CORNER = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 7, 0),
                270, 1), id++, "Bottom_Left_Road"));
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }

    private BufferedImage[] getImages(int firstX, int firstY, int secondX, int secondY) {
        return new BufferedImage[]{getSprite(firstX, firstY), getSprite(secondX, secondY)};
    }

}
