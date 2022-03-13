package ui;

import objects.Tile;
import scenes.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static main.GameStates.MENU;
import static main.GameStates.setGameState;

public class ToolBar extends Bar {

    private int currentTileIndex;

    private MyButton bMenu, bSave;
    private Editing editing;
    private Tile selectedTile;

    private Map<MyButton, ArrayList<Tile>> tilesMap = new HashMap<>();
    private MyButton bGrass, bWater, bStraightRoads, bRoadCorners, bWaterCorners, bBeaches, bIslands;
    private MyButton currentTileButton;

    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initButtons();
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        } else if (bSave.getBounds().contains(x, y)) {
            editing.saveLevel();
        } else if (bGrass.getBounds().contains(x, y)) {
            selectedTile = editing.getGame().getTileManager().getTile(bGrass.getId());
            editing.setSelectedTile(selectedTile);
        } else if (bWater.getBounds().contains(x, y)) {
            selectedTile = editing.getGame().getTileManager().getTile(bWater.getId());
            editing.setSelectedTile(selectedTile);
        } else {
            for (MyButton button : tilesMap.keySet()) {
                if (button.getBounds().contains(x, y)) {
                    selectedTile = tilesMap.get(button).get(0);
                    editing.setSelectedTile(selectedTile);
                    currentTileButton = button;
                    currentTileIndex = 0;
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        // Reset mouse over
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        bGrass.setMouseOver(false);
        bWater.setMouseOver(false);
        for (MyButton button : tilesMap.keySet()) {
            button.setMouseOver(false);
        }

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMouseOver(true);
        } else if (bGrass.getBounds().contains(x, y)) {
            bGrass.setMouseOver(true);
        } else if (bWater.getBounds().contains(x, y)) {
            bWater.setMouseOver(true);
        } else {
            for (MyButton button : tilesMap.keySet()) {
                if (button.getBounds().contains(x, y)) {
                    button.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMousePressed(true);
        } else if (bGrass.getBounds().contains(x, y)) {
            bGrass.setMousePressed(true);
        } else if (bWater.getBounds().contains(x, y)) {
            bWater.setMousePressed(true);
        } else {
            for (MyButton button : tilesMap.keySet()) {
                if (button.getBounds().contains(x, y)) {
                    button.setMousePressed(true);
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    public void rotateSprite() {
        currentTileIndex++;
        if (currentTileIndex >= tilesMap.get(currentTileButton).size()) {
            currentTileIndex = 0;
        }
        selectedTile = tilesMap.get(currentTileButton).get(currentTileIndex);
        editing.setSelectedTile(selectedTile);
    }

    private void initButtons() {
        int buttonsXPos = 0;
        int buttonsYPos = 640;
        int buttonsWidth = 100;
        int buttonsHeight = 25;

        bMenu = new MyButton("Menu", buttonsXPos, buttonsYPos, buttonsWidth, buttonsHeight);
        bSave = new MyButton("Save", buttonsXPos, buttonsYPos + buttonsHeight, buttonsWidth, buttonsHeight);

        int tileButtonsXPos = 130;
        int tileButtonsYPos = 640;
        int tileButtonsWidth = 50;
        int tileButtonsHeight = 50;
        int tileButtonsOffset = 55;
        int index = 0;

        // Tile buttons
        bGrass = new MyButton("Grass", tileButtonsXPos + (tileButtonsOffset * index),
                tileButtonsYPos, tileButtonsWidth, tileButtonsHeight, index++);
        bWater = new MyButton("Water", tileButtonsXPos + (tileButtonsOffset * index),
                tileButtonsYPos, tileButtonsWidth, tileButtonsHeight, index++);

        initMapButtons(bStraightRoads, editing.getGame().getTileManager().getStraightRoads(), tileButtonsXPos,
                tileButtonsYPos, tileButtonsOffset, tileButtonsWidth, tileButtonsHeight, index++);
        initMapButtons(bRoadCorners, editing.getGame().getTileManager().getRoadCorners(), tileButtonsXPos,
                tileButtonsYPos, tileButtonsOffset, tileButtonsWidth, tileButtonsHeight, index++);
        initMapButtons(bWaterCorners, editing.getGame().getTileManager().getWaterCorners(), tileButtonsXPos,
                tileButtonsYPos, tileButtonsOffset, tileButtonsWidth, tileButtonsHeight, index++);
        initMapButtons(bBeaches, editing.getGame().getTileManager().getBeaches(), tileButtonsXPos,
                tileButtonsYPos, tileButtonsOffset, tileButtonsWidth, tileButtonsHeight, index++);
        initMapButtons(bIslands, editing.getGame().getTileManager().getIslands(), tileButtonsXPos,
                tileButtonsYPos, tileButtonsOffset, tileButtonsWidth, tileButtonsHeight, index++);
    }

    private void initMapButtons(MyButton button, ArrayList<Tile> tileList, int x, int y,
                                int xOffset, int width, int height, int id) {

        button = new MyButton("", x + (xOffset * id), y, width, height, id);
        tilesMap.put(button, tileList);

    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);
        drawSimpleTileButtons(g, bGrass);
        drawSimpleTileButtons(g, bWater);
        drawSelectedTile(g);
        drawMapButtons(g);
    }

    private void drawSimpleTileButtons(Graphics g, MyButton button) {
        // Draw sprite
        g.drawImage(getButtonImage(button.getId()),
                button.getXPos(), button.getYPos(),
                button.getWidth(), button.getHeight(), null);

        drawButtonsFeedback(g, button);
    }

    private void drawMapButtons(Graphics g) {
        for (Map.Entry<MyButton, ArrayList<Tile>> entry : tilesMap.entrySet()) {
            MyButton button = entry.getKey();
            BufferedImage image = entry.getValue().get(0).getSprite();

            // Draw sprite
            g.drawImage(image, button.getXPos(), button.getYPos(), button.getWidth(), button.getHeight(), null);

            drawButtonsFeedback(g, button);
        }
    }

    private void drawButtonsFeedback(Graphics g, MyButton button) {

        // Mouse over
        if (button.isMouseOver()) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        // Mouse pressed
        if (button.isMousePressed()) {
            g.setColor(Color.CYAN);
        }
        g.drawRect(button.getXPos(), button.getYPos(),
                button.getWidth(), button.getHeight());
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 590, this.y, 50, 50, null);
            g.setColor(Color.BLACK);
            g.drawRect(590, this.y, 50, 50);
        }
    }

    public void draw(Graphics g) {
        // Background
        g.setColor(new Color(212, 34, 198));
        g.fillRect(x, y, width, height);

        // Buttons
        drawButtons(g);
    }

    private void resetButtons() {
        bMenu.resetBooleans();
        bSave.resetBooleans();
        bGrass.resetBooleans();
        bWater.resetBooleans();
        for (MyButton button : tilesMap.keySet()) {
            button.resetBooleans();
        }
    }

    private BufferedImage getButtonImage(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }
}
