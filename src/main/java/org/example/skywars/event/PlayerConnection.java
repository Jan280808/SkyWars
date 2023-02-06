package org.example.skywars.event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.state.LobbyState;
import org.example.skywars.gamemanager.state.PlayState;

public class PlayerConnection implements Listener {

    private final GameManager gameManager;

    public PlayerConnection(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler @Deprecated
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        if(gameManager.getCurrentState() instanceof LobbyState lobbyState) {
            event.setJoinMessage("§8[§a+§8] §7" + player.getName());
            player.setGameMode(GameMode.SURVIVAL);
            player.getInventory().clear();
            player.getInventory().setItem(4, gameManager.getKitManager().getSelectKitItem());
            gameManager.getGamePlayers().add(player);
            gameManager.getMap().teleportPlayerToWaitingLobby(player);
            //gameManager.getScoredBoardManager().setScoreBoard();
            checkIfEnoughPlayer(lobbyState);
        } else {
            gameManager.getMap().teleportSpectatorPlayer(player);
            player.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler @Deprecated
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(null);

        if(gameManager.getCurrentState() instanceof LobbyState lobbyState)
            checkIfEnoughPlayer(lobbyState);
        if(gameManager.getCurrentState() instanceof PlayState)
            gameManager.checkIfPlayerHasWon();
        event.setQuitMessage("§8[§c-§8] §7" + player.getName());
        gameManager.getGamePlayers().remove(player);
        //gameManager.getScoredBoardManager().setScoreBoard();
    }

    private void checkIfEnoughPlayer(LobbyState lobbyState) {
        if(lobbyState == null) return;
        if(gameManager.getGamePlayers().size() < 2) {
            lobbyState.stop();
            lobbyState.startIdle();
        } else {
            lobbyState.stopIdle();
            lobbyState.start();
        }
    }
}
