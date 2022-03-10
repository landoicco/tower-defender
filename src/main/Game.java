package main;

import helpers.LoadSave;
import managers.TileManager;
import scenes.*;

import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {

    private GameScreen gameScreen;
    private TileManager tileManager;
    private Thread gameThread;
    private Render render;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    // Scene classes
    private Menu menu;
    private Settings settings;
    private Playing playing;
    private Editing editing;

    public Game() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initClasses();
        createDefaultLevel();
        setResizable(false);
        add(gameScreen);
        pack();
        setVisible(true);
    }

    private void initClasses() {
        this.tileManager = new TileManager();
        this.render = new Render(this);
        this.gameScreen = new GameScreen(this);
        this.menu = new Menu(this);
        this.playing = new Playing(this);
        this.settings = new Settings(this);
        this.editing = new Editing(this);
    }

    private void createDefaultLevel() {
        int[] array = new int[400];
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
        LoadSave.createLevel("default_level", array);
    }

    public static void main(String[] args) {
        System.out.println("Code compiled... Starting up!");
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    private void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void updateGame() {
        //System.out.println("Game Updated!");
    }

    @Override
    public void run() {

        double timePerFrame = 1_000_000_000 / FPS_SET;
        double timePerUpdate = 1_000_000_000 / UPS_SET;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        long now;
        int frames = 0;
        int updates = 0;

        while (true) {

            now = System.nanoTime();

            // Render
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }

            // Update
            if (now - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = now;
                updates++;
            }

            // Checking FPS & UPS
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                updates = 0;
                frames = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    // Getters

    public Render getRender() {
        return this.render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Settings getSettings() {
        return settings;
    }


    public Playing getPlaying() {
        return playing;
    }

    public Editing getEditing() {
        return editing;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
