package org.example.skywars.gamemanager.countdown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.example.skywars.SkyWars;
import org.example.skywars.gamemanager.GameManager;

public class LobbyCountdown extends Countdown {

    private final GameManager gameManager;
    private int seconds;
    private boolean isRunning, isIdling;
    private int idleTaskID;

    public LobbyCountdown(GameManager gameManager) {
        this.gameManager = gameManager;
        this.seconds = 60;
    }

    @Override @Deprecated
    public void start() {
        if(isRunning) return;
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.inst, () -> {
            switch (seconds) {
                case 60, 50, 40, 30, 20, 15, 10, 5 -> {
                    gameManager.getGamePlayers().forEach(player -> player.sendMessage(SkyWars.MAIN + "The game starts in " + seconds + " seconds"));
                    Bukkit.getConsoleSender().sendMessage(SkyWars.MAIN + "lobbyCountdown: " + seconds);
                }
                case 25 -> {
                    gameManager.getGamePlayers().forEach(player -> player.sendMessage(SkyWars.MAIN + "SkyWars was created by Jan2808"));
                    Bukkit.getConsoleSender().sendMessage(SkyWars.MAIN + "lobbyCountdown: " + seconds);
                }
                case 4 -> gameManager.getGamePlayers().forEach(player -> {
                    player.getInventory().clear();
                    player.closeInventory();
                });
                case 3, 2, 1 -> {
                    gameManager.getGamePlayers().forEach(player -> {
                        player.sendTitle(SkyWars.MAIN + "The game starts in ", seconds + " seconds", 1, 20, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.5f, 1f);
                    });
                    Bukkit.getConsoleSender().sendMessage(SkyWars.MAIN + "lobbyCountdown: " + seconds);
                }
                case 0 -> gameManager.setCurrentState(1);
            }
            --seconds;
        }, 0, 20);
    }

    public void startWithCommand() {
        seconds = 5;
        start();
    }

    @Override
    public void stop() {
        if(!isRunning) return;
        Bukkit.getScheduler().cancelTask(taskID);
        isRunning = false;
        this.seconds = 20;
    }

    public void startIdle() {
        if(isIdling) return;

        isIdling = true;
        idleTaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.inst, () -> {
            if(gameManager.getGamePlayers().size() != 0)
                Bukkit.broadcastMessage(SkyWars.MAIN + "there must be at least 2 player online");
        }, 0, 20*30);
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "START IDLE");
    }

    public void stopIdle() {
        if(!isIdling) return;
        Bukkit.getScheduler().cancelTask(idleTaskID);
        isIdling = false;
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "STOP IDLE");
    }
}
