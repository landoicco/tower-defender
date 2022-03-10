package ui;

import scenes.Playing;

import java.awt.Graphics;
import java.awt.Color;

import static main.GameStates.MENU;
import static main.GameStates.setGameState;

public class ActionBar extends Bar {
    private MyButton bMenu;
    private Playing playing;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        initButtons();
    }

    public void draw(Graphics g) {
        // Background
        g.setColor(new Color(212, 34, 198));
        g.fillRect(x, y, width, height);

        // Buttons
        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        // Reset mouse over
        bMenu.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        }
    }

    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void initButtons() {
        int buttonsXPos = 0;
        int buttonsYPos = 650;
        int buttonsWidth = 100;
        int buttonsHeight = 30;
        int buttonOffset = 100;

        bMenu = new MyButton("Menu", buttonsXPos, buttonsYPos, buttonsWidth, buttonsHeight);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    private void resetButtons() {
        bMenu.resetBooleans();
    }
}
