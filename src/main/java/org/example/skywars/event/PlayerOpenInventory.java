package org.example.skywars.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.example.skywars.gamemanager.GameManager;

public class PlayerOpenInventory implements Listener {

    private final GameManager gameManager;


    public PlayerOpenInventory(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onOpenChest(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();
        gameManager.getLootManager().setOpenStatus(inventory.getLocation());
    }
}
