package managers;

import helpers.ImageFix;
import helpers.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    private Tile GRASS, WATER, H_ROAD, V_ROAD, BL_WATER_CORNER, TL_WATER_CORNER, BR_WATER_CORNER, TR_WATER_CORNER;
    private Tile TOP_BEACH, BOTTOM_BEACH, LEFT_BEACH, RIGHT_BEACH, TL_ISLAND, TR_ISLAND, BR_ISLAND, BL_ISLAND;
    private Tile TL_ROAD_CORNER, TR_ROAD_CORNER, BL_ROAD_CORNER, BR_ROAD_CORNER;
    private BufferedImage atlas;

    private ArrayList<Tile> tiles = new ArrayList();
    private ArrayList<Tile> straightRoads = new ArrayList();
    private ArrayList<Tile> roadCorners = new ArrayList();
    private ArrayList<Tile> waterCorners = new ArrayList();
    private ArrayList<Tile> beaches = new ArrayList();
    private ArrayList<Tile> islands = new ArrayList();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    public BufferedImage getAnimatedSprite(int id, int animationIndex) {
        return tiles.get(id).getSprite(animationIndex);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

    public boolean isSpriteAnimation(int spriteID) {
        return tiles.get(spriteID).isAnimation();
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(9, 0), id++, "Grass"));
        tiles.add(WATER = new Tile(getAnimatedSprites(0, 0), id++, "Water"));

        straightRoads.add(H_ROAD = new Tile(getSprite(8, 0), id++, "Horizontal_Road"));
        straightRoads.add(V_ROAD = new Tile(ImageFix.getRotatedImage(getSprite(8, 0), 90), id++,
                "Vertical_Road"));

        // Water corners
        waterCorners.add(BL_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(5, 0), 0), id++, "BL_Water_Corner"));
        waterCorners.add(TL_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(5, 0), 90), id++, "TL_Water_Corner"));
        waterCorners.add(BR_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(5, 0), 180), id++, "BR_Water_Corner"));
        waterCorners.add(TR_WATER_CORNER = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(5, 0), 270), id++, "TR_Water_Corner"));

        // Beaches
        beaches.add(TOP_BEACH = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(6, 0), 0), id++, "Top_Beach"));
        beaches.add(RIGHT_BEACH = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(6, 0), 90), id++, "Right_Beach"));
        beaches.add(BOTTOM_BEACH = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(6, 0), 180), id++, "Bottom_Water"));
        beaches.add(LEFT_BEACH = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(6, 0), 270), id++, "Left_Beach"));

        // Island corners
        islands.add(TL_ISLAND = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(4, 0), 0), id++, "Top_Left_Island"));
        islands.add(TR_ISLAND = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(4, 0), 90), id++, "Top_Right_Island"));
        islands.add(BR_ISLAND = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(4, 0), 180), id++, "Bottom_Right_Island"));
        islands.add(BL_ISLAND = new Tile(ImageFix.getBuildRotatedImage(getAnimatedSprites(0, 0),
                getSprite(4, 0), 270), id++, "Bottom_Left_Island"));

        // Road corners
        roadCorners.add(TL_ROAD_CORNER = new Tile(ImageFix.buildImage(
                getImages(0, 0, 7, 0)), id++, "Top_Left_Road"));
        roadCorners.add(TR_ROAD_CORNER = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 7, 0),
                90, 1), id++, "Top_Right_Road"));
        roadCorners.add(BR_ROAD_CORNER = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 7, 0),
                180, 1), id++, "Bottom_Right_Road"));
        roadCorners.add(BL_ROAD_CORNER = new Tile(ImageFix.getBuildRotatedImage(getImages(0, 0, 7, 0),
                270, 1), id++, "Bottom_Left_Road"));

        // Add all to 'tiles'
        tiles.addAll(straightRoads);
        tiles.addAll(waterCorners);
        tiles.addAll(beaches);
        tiles.addAll(islands);
        tiles.addAll(roadCorners);
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }

    private BufferedImage[] getAnimatedSprites(int xCord, int yCord) {
        // Number of images of the animation
        int numOfImages = 4;
        BufferedImage[] animatedSprite = new BufferedImage[numOfImages];
        for (int i = 0; i < numOfImages; i++) {
            animatedSprite[i] = getSprite(xCord + i, yCord);
        }
        return animatedSprite;
    }

    private BufferedImage[] getImages(int firstX, int firstY, int secondX, int secondY) {
        return new BufferedImage[]{getSprite(firstX, firstY), getSprite(secondX, secondY)};
    }

    public ArrayList<Tile> getStraightRoads() {
        return straightRoads;
    }

    public ArrayList<Tile> getRoadCorners() {
        return roadCorners;
    }

    public ArrayList<Tile> getWaterCorners() {
        return waterCorners;
    }

    public ArrayList<Tile> getBeaches() {
        return beaches;
    }

    public ArrayList<Tile> getIslands() {
        return islands;
    }
}
