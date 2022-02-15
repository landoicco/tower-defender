package main;

import javax.swing.JFrame;

public class Game extends JFrame {

    private GameScreen gameScreen;

    public Game() {
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        gameScreen = new GameScreen();
        add(gameScreen);
    }

    public static void main(String[] args) {
        System.out.println("Hola Mundo!");
        new Game();
    }
}
