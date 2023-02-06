package org.example.skywars.gamemanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.example.skywars.SkyWars;
import org.example.skywars.gamemanager.kit.KitManager;
import org.example.skywars.gamemanager.loot.LootManager;
import org.example.skywars.gamemanager.scoreboard.ScoredBoardManager;
import org.example.skywars.gamemanager.state.map.MapManager;
import org.example.skywars.gamemanager.state.EndState;
import org.example.skywars.gamemanager.state.LobbyState;
import org.example.skywars.gamemanager.state.PlayState;
import org.example.skywars.gamemanager.state.GameState;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private final GameState[] gameStates;
    private GameState currentState;
    private final List<Player> gamePlayers;
    private final MapManager map;
    private final KitManager kitManager;
    private final LootManager lootManager;
    private final ScoredBoardManager scoredBoardManager;

    public GameManager() {
        this.gameStates = new GameState[3];
        this.gameStates[0] = new LobbyState(this);
        this.gameStates[1] = new PlayState(this);
        this.gameStates[2] = new EndState(this);

        this.gamePlayers = new ArrayList<>();
        this.map = new MapManager(this);
        map.loadLocationCache();

        this.kitManager = new KitManager(this);
        kitManager.loadKitsCache();

        this.lootManager = new LootManager();
        lootManager.loadLootChestCache();

        this.scoredBoardManager = new ScoredBoardManager(this);
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int stateID) {
        if(this.currentState != null)
            currentState.stop();
        this.currentState = gameStates[stateID];

        if(currentState instanceof LobbyState) {
            ((LobbyState) currentState).startIdle();
        } else {
            currentState.start();
        }
        sendConsoleInformation("" + ChatColor.GOLD + currentState + ChatColor.GRAY + " has been started");
    }

    public void sendConsoleInformation(String information) {
        Bukkit.getConsoleSender().sendMessage(SkyWars.MAIN + information);
    }

    public void checkIfPlayerHasWon() {
        if(gamePlayers.size() != 1) return;
        setCurrentState(2);
        gamePlayers.forEach(player -> Bukkit.getOnlinePlayers().forEach(player1 -> {
            player1.sendTitle("" + player.getName(), ChatColor.GOLD + "has won the game", 1, 20, 1);
            player1.setGameMode(GameMode.SURVIVAL);
            map.teleportPlayerToWaitingLobby(player1);
        }));
    }

    public List<Player> getGamePlayers() {
        return gamePlayers;
    }

    public MapManager getMap() {
        return map;
    }

    public KitManager getKitManager() {
        return kitManager;
    }

    public LootManager getLootManager() {
        return lootManager;
    }

    public ScoredBoardManager getScoredBoardManager() {
        return scoredBoardManager;
    }
}
