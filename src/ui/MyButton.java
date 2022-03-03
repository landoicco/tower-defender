package ui;

import java.awt.*;

public class MyButton {
    private int x, y, width, height;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;

    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
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
}
