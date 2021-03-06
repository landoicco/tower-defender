package objects;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage[] sprite;
    private String name;
    private int id;

    public Tile(BufferedImage sprite, int id, String name) {
        this.sprite = new BufferedImage[1];
        this.sprite[0] = sprite;
        this.id = id;
        this.name = name;
    }

    public Tile(BufferedImage[] sprites, int id, String name) {
        this.sprite = sprites;
        this.id = id;
        this.name = name;
    }

    public BufferedImage getSprite(int animationIndex) {
        return sprite[animationIndex];
    }

    public BufferedImage getSprite() {
        return sprite[0];
    }

    public String getName() {
        return name;
    }

    public boolean isAnimation() {
        return sprite.length > 1;
    }

    public int getId() {
        return id;
    }
}
