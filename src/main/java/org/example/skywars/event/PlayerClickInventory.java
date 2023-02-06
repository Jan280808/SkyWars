package org.example.skywars.event;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.state.LobbyState;

public class PlayerClickInventory implements Listener {

    private final GameManager gameManager;

    public PlayerClickInventory(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler @Deprecated
    public void onClick(InventoryClickEvent event) {
        if(event.getWhoClicked().getType() != EntityType.PLAYER) return;
        Player player = (Player) event.getWhoClicked();

        if(gameManager.getCurrentState() instanceof LobbyState) {
            event.setCancelled(true);

            if(!event.getView().getTitle().equals("Select kit")) return;

            if(event.getCurrentItem() == null) return;
            String displayName = event.getCurrentItem().getItemMeta().getDisplayName();

            if(gameManager.getCurrentState() instanceof LobbyState) {
                event.setCancelled(true);
                if(event.getCurrentItem().getType().equals(Material.CHEST)) return;
                gameManager.getKitManager().putPlayerAtKitMap(player, displayName);
            }
        }
    }
}
