package org.example.skywars.gamemanager.state;

import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.countdown.LobbyCountdown;

public class LobbyState extends GameState {
    private final LobbyCountdown countdown;

    public LobbyState(GameManager gameManager) {
        this.countdown = new LobbyCountdown(gameManager);
    }

    @Override
    public void start() {
        countdown.start();
    }

    @Override
    public void stop() {
        countdown.stop();
    }

    public void startIdle() {countdown.startIdle();}

    public void stopIdle() {countdown.stopIdle();}

    public void startWithCommand() {countdown.startWithCommand();}

}
