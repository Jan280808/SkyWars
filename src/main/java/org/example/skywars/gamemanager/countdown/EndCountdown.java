package org.example.skywars.gamemanager.countdown;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.example.skywars.SkyWars;

public class EndCountdown extends Countdown {

    private int seconds;
    private boolean isRunning;

    public EndCountdown() {
        this.seconds = 10;
    }

    @Override
    public void start() {
        if(isRunning) return;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.inst, () -> {
            switch (seconds) {
                case 10, 5, 3, 2, 1 -> {
                    Bukkit.broadcastMessage(SkyWars.MAIN + "The server will restart in " + seconds + " seconds");
                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 0.5F, 1));
                }
                case 0 -> Bukkit.reload();
            }
            --seconds;
        }, 0, 20);
    }

    @Override
    public void stop() {
        if(!isRunning) return;
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
