package org.example.skywars.gamemanager.countdown;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.example.skywars.SkyWars;
import org.example.skywars.gamemanager.GameManager;

public class PlayCountdown extends Countdown {

    private final GameManager gameManager;
    private int seconds;
    public boolean isRunning;
    public PlayCountdown(GameManager gameManager) {
        this.gameManager = gameManager;
        this.seconds = 10;
    }

    @Override
    public void start() {
        if(isRunning) return;
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.inst, () -> {
            switch (seconds) {
                case 10, 5, 3, 2, 1 -> Bukkit.broadcastMessage(SkyWars.MAIN + "The protection phase ends in " + seconds + " seconds");
                case 0 -> {
                    Bukkit.broadcastMessage(SkyWars.MAIN + "Enjoy and don't rage :)");
                    gameManager.getGamePlayers().forEach(gamePlayer -> gamePlayer.getPlayer().playSound(gamePlayer.getPlayer(), Sound.BLOCK_NOTE_BLOCK_BELL, 0.5F, 1));
                    stop();
                }
            }
            --seconds;
        }, 0, 20);
    }

    @Override
    public void stop() {
        if(!isRunning) return;
        isRunning = false;
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
