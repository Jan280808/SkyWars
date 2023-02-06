package org.example.skywars.gamemanager.state.map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.example.skywars.gamemanager.builder.FileBuilder;
import org.example.skywars.gamemanager.GameManager;

import java.util.HashMap;

public class MapManager {

    private final HashMap<String, Location> locationMap;
    private final GameManager gameManager;
    private final FileBuilder fileBuilder;

    public MapManager(GameManager gameManager) {
        this.gameManager = gameManager;
        this.fileBuilder = new FileBuilder("mapLocations");
        this.locationMap = new HashMap<>();
    }

    public void setLocation(String name, Location location) {
        if(name == null) return;
        if(location == null) return;
        fileBuilder.setLocation(name, location);
        gameManager.sendConsoleInformation("a new location was set with the id " + ChatColor.GOLD + name);
    }

    public void teleportPlayerToWaitingLobby(Player player) {
        if(player == null) return;
        player.teleport(locationMap.get(Locations.LOCATION_WAITING_LOBBY.getString()));
    }

    int i = 0;
    public void teleportPlayers() {
        gameManager.getGamePlayers().forEach(gamePlayer -> {
            i = i+1;
            gamePlayer.getPlayer().teleport(locationMap.get("playerSpawn"+i));
        });
    }

    public void teleportSpectatorPlayer(Player player) {
        if(player == null) return;
        player.teleport(locationMap.get(Locations.LOCATION_SPECTATOR_SPAWN.getString()));
    }

    public void loadLocationCache() {
        fileBuilder.getStrings(false).forEach(s -> {
            Location location = fileBuilder.getLocation(s);
            locationMap.put(s, location);
            gameManager.sendConsoleInformation("the location " + s + " was loaded " + ChatColor.GREEN + "successfully");
        });
    }
}
