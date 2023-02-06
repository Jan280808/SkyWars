package org.example.skywars.gamemanager.state;

public abstract class GameState {

    public static final int LOBBY_STATE = 0;
    public static final int PLAY_STATE = 1;
    public static final int END_STATE = 2;

    public GameState() {}

    public abstract void start();

    public abstract void stop();
}
