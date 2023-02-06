package org.example.skywars.gamemanager.state;

import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.countdown.PlayCountdown;

public class PlayState extends GameState {

    private final GameManager gameManager;
    private final PlayCountdown countdown;

    public PlayState(GameManager gameManager) {
        this.gameManager = gameManager;
        this.countdown = new PlayCountdown(gameManager);
    }

    @Override
    public void start() {
        countdown.start();
        gameManager.getMap().teleportPlayers();
        gameManager.getKitManager().givePlayerKit();
        gameManager.getLootManager().fillChest();
    }

    @Override
    public void stop() {
        countdown.stop();
    }

    public PlayCountdown getCountdown() {
        return countdown;
    }
}
