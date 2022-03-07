package ui;

import java.awt.*;

public class MyButton {
    private int x, y, width, height, id;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;

    // For normal buttons
    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.id = -1;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initBounds();
    }

    // For tile buttons
    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initBounds();
    }

    public void draw(Graphics g) {
        // Body
        drawBody(g);
        // Border
        drawBorder(g);
        // Text
        drawText(g);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        if (mousePressed) {
            g.setColor(Color.CYAN);
            g.drawRect(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

    private void drawBody(Graphics g) {
        if (mouseOver) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.ORANGE);
        }
        g.fillRect(x, y, width, height);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - (w / 2) + (width / 2), y + (h / 3) + (height / 2));
    }

    private void initBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    public int getId() {
        return id;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }
}
