package org.example.skywars.gamemanager.loot;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

public class LootChest {

    private final Location location;
    private final Inventory inventory;
    private Boolean opened;

    public LootChest(Location location, Inventory inventory) {
        this.location = location;
        this.inventory = inventory;
        this.opened = true;
    }

    public Location getLocation() {
        return location;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setOpened() {
        this.opened = true;
        Bukkit.getConsoleSender().sendMessage("open chest");
    }

    public Boolean getOpened() {
        return opened;
    }
}
