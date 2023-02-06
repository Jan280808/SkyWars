package org.example.skywars.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.state.LobbyState;

public class PlayerInteraction implements Listener {

    private final GameManager gameManager;

    public PlayerInteraction(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler @Deprecated
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(gameManager.getCurrentState() instanceof LobbyState) {
            event.setCancelled(true);

            if(event.getItem() == null) return;
            ItemStack clickItem = event.getItem();
            gameManager.getKitManager().openKitSelectInventory(player, clickItem);
        }
    }
}
