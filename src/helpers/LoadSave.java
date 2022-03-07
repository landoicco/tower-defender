package helpers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class LoadSave {

    public static BufferedImage getSpriteAtlas() {

        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Image not found/available!");
            e.printStackTrace();
        }
        return img;
    }

    // txt file
    public static void createFile() {

        File textFile = new File("res/testTextFile.txt");
        try {
            textFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createLevel(String name, int[] idArray) {
        File newLevel = new File("res/" + name + ".txt");
        if (newLevel.exists()) {
            System.out.println("File: " + name + " already exists!");
            return;
        } else {
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeToFile(newLevel, idArray);
        }
    }

    public static void readFromFile() {
        File textFile = new File("res/text.txt");
        try {
            Scanner sc = new Scanner(textFile);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(File file, int[] idArray) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (Integer i : idArray) {
                pw.println(i);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
