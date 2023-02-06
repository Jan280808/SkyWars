package org.example.skywars.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.state.PlayState;

public class BlockEvents implements Listener {

    private final GameManager gameManager;

    public BlockEvents(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onChangeWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setCancelled(true);
    }

    @EventHandler @Deprecated
    public void onPicItem(PlayerPickupItemEvent event) {
        event.setCancelled(!(gameManager.getCurrentState() instanceof PlayState));
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        event.setCancelled(!(gameManager.getCurrentState() instanceof PlayState));
    }

    @EventHandler
    public void onSpawnEntity(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onChangeFoodLevel(FoodLevelChangeEvent event) {
        event.setCancelled(!(gameManager.getCurrentState() instanceof PlayState));
    }
}
