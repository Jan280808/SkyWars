package org.example.skywars.gamemanager.state;

import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.countdown.EndCountdown;

public class EndState extends GameState {

    private final GameManager gameManager;
    private final EndCountdown countdown;

    public EndState(GameManager gameManager) {
        this.gameManager = gameManager;
        this.countdown = new EndCountdown();
    }

    @Override
    public void start() {
        countdown.start();
        gameManager.getGamePlayers().forEach(player -> player.getInventory().clear());
    }

    @Override
    public void stop() {
        countdown.stop();
    }
}
