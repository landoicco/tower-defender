package scenes;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface GameMethods {

    void render(Graphics g);

    void mouseClicked(int x, int y);

    void mouseMoved(int x, int y);

    void mousePressed(int x, int y);

    void mouseReleased(int x, int y);

    void mouseDragged(int x, int y);
}
