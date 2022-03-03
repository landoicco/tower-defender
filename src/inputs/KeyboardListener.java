package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

public class KeyboardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("A key pressed!");
            gameState = PLAYING;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("S key pressed!");
            gameState = SETTINGS;
        } else {
            gameState = MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
