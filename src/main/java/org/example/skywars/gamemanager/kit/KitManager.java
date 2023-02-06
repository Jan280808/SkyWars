package org.example.skywars.gamemanager.kit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.example.skywars.SkyWars;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.builder.ItemBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class KitManager {

    private final GameManager gameManager;
    private final HashMap<String, Kit> kitMap;
    private final HashMap<UUID, Kit> playerKitMap;
    private final Inventory inventory;
    private final ItemStack selectKitItem = new ItemBuilder(Material.CHEST).setDisplayName(ChatColor.GOLD + "Select a Kit").build();

    public KitManager(GameManager gameManager) {
        this.gameManager = gameManager;
        this.kitMap = new HashMap<>();
        this.playerKitMap = new HashMap<>();
        this.inventory = Bukkit.createInventory(null, 27, "Select kit");
    }

    public void loadKitsCache() {
        Arrays.stream(KitList.values()).forEach(kitList -> {
            Kit kit = new Kit(kitList.getName(), kitList.getKitIcon(), kitList.getHelmed(), kitList.getChestplate(), kitList.getLeggings(), kitList.getBoots());
            kit.setItems(kitList.getItems());
            kitMap.put(kit.getName(), kit);
            inventory.addItem(kit.getIcon());
            Bukkit.getConsoleSender().sendMessage(SkyWars.MAIN + "The kit " + kit.getName() + ChatColor.GRAY + " has been " + ChatColor.GREEN + "created");
        });
    }

    public void openKitSelectInventory(Player player, ItemStack clickItem) {
        if(player == null) return;
        if(clickItem == null) return;
        if(inventory == null) return;
        if(!clickItem.getItemMeta().getDisplayName().equals(selectKitItem.getItemMeta().getDisplayName())) return;
        player.openInventory(inventory);
        player.playSound(player, Sound.BLOCK_BARREL_OPEN, 0.5F, 1);
    }

    public void putPlayerAtKitMap(Player player, String kitName) {
        if(player == null) return;
        UUID uuid = player.getUniqueId();

        if(kitName == null) return;
        if(kitMap.get(kitName) == null) return;
        Kit kit = kitMap.get(kitName);

        playerKitMap.put(uuid, kit);
        player.sendMessage(SkyWars.MAIN + "You have selected the " + kitName + ChatColor.GRAY + " kit");
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 0.5F, 1);
        player.closeInventory();
    }

    public void givePlayerKit() {
        gameManager.getGamePlayers().forEach(player -> {
            PlayerInventory inventory = player.getInventory();
            UUID uuid = player.getUniqueId();

            if(playerKitMap.get(uuid) == null) {
                Kit kit = kitMap.get(KitList.ENDER_KIT.getName());
                inventory.setHelmet(kit.getHelmed()); inventory.setChestplate(kit.getChestplate());
                inventory.setLeggings(kit.getLeggings()); inventory.setBoots(kit.getBoots());
                kit.getItems().forEach(inventory::addItem);
            } else {
                Kit kit = playerKitMap.get(uuid);
                inventory.clear();
                inventory.setHelmet(kit.getHelmed()); inventory.setChestplate(kit.getChestplate());
                inventory.setLeggings(kit.getLeggings()); inventory.setBoots(kit.getBoots());
                kit.getItems().forEach(inventory::addItem);
            }
        });
    }

    public ItemStack getSelectKitItem() {
        return selectKitItem;
    }
}
