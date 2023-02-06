package org.example.skywars.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.state.PlayState;

public class PlayerBlockInteraction implements Listener {

    private final GameManager gameManager;

    public PlayerBlockInteraction(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(!(gameManager.getCurrentState() instanceof PlayState));
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        event.setCancelled(!(gameManager.getCurrentState() instanceof PlayState));
    }
}
