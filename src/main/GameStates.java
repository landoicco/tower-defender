package main;

public enum GameStates {

    MENU,
    PLAYING,
    SETTINGS;

    public static GameStates gameState = MENU;

    public static void setGameState(GameStates state) {
        gameState = state;
    }

}
