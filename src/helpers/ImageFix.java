package helpers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFix {

    // Rotate
    public static BufferedImage getRotatedImage(BufferedImage img, int rotAngle) {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImg = new BufferedImage(width, height, img.getType());
        Graphics2D g2d = newImg.createGraphics();

        g2d.rotate(Math.toRadians(rotAngle), (width / 2), (height / 2));
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return newImg;
    }

    // Image Build
    public static BufferedImage buildImage(BufferedImage[] images) {
        int width = images[0].getWidth();
        int height = images[0].getHeight();

        BufferedImage newImg = new BufferedImage(width, height, images[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (BufferedImage img : images) {
            g2d.drawImage(img, 0, 0, null);
        }
        g2d.dispose();
        return newImg;
    }

    // Rotate second image only
    public static BufferedImage getBuildRotatedImage(BufferedImage[] images, int rotAngle, int rotAtIndex) {
        int width = images[0].getWidth();
        int height = images[0].getHeight();

        BufferedImage newImg = new BufferedImage(width, height, images[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (int i = 0; i < images.length; i++) {
            if (rotAtIndex == i) {
                g2d.rotate(Math.toRadians(rotAngle), (width / 2), (height / 2));
            }
            g2d.drawImage(images[i], 0, 0, null);
            if (rotAtIndex == i) {
                g2d.rotate(Math.toRadians(-rotAngle), (width / 2), (height / 2));
            }
        }
        return newImg;
    }

    // Rotate second image only + animation
    public static BufferedImage[] getBuildRotatedImage(BufferedImage[] images, BufferedImage secondImage, int rotAngle) {
        int width = images[0].getWidth();
        int height = images[0].getHeight();
        BufferedImage[] animatedImage = new BufferedImage[images.length];

        for (int i = 0; i < images.length; i++) {
            BufferedImage newImg = new BufferedImage(width, height, images[0].getType());
            Graphics2D g2d = newImg.createGraphics();

            g2d.drawImage(images[i], 0, 0, null);
            g2d.rotate(Math.toRadians(rotAngle), (width / 2), (height / 2));
            g2d.drawImage(secondImage, 0, 0, null);

            g2d.dispose();
            animatedImage[i] = newImg;
        }
        return animatedImage;
    }
}
