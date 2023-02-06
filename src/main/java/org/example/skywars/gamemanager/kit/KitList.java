package org.example.skywars.gamemanager.kit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.example.skywars.gamemanager.builder.ItemBuilder;

public enum KitList {
    ENDER_KIT(ChatColor.DARK_PURPLE + "EnderMan", new ItemBuilder(Material.ENDER_PEARL).setDisplayName(ChatColor.DARK_PURPLE + "EnderMan").build(),
            null, new ItemBuilder(Material.LEATHER_CHESTPLATE).setDisplayName("lul").build(), null, null, Material.ENDER_PEARL, Material.END_STONE),

    TANK(ChatColor.AQUA + "Tank", new ItemBuilder(Material.DIAMOND_CHESTPLATE).setDisplayName(ChatColor.AQUA + "Tank").build(),
            new ItemBuilder(Material.DIAMOND_HELMET).build(), new ItemBuilder(Material.DIAMOND_CHESTPLATE).build(), new ItemBuilder(Material.DIAMOND_LEGGINGS).build(), new ItemBuilder(Material.DIAMOND_BOOTS).build(), Material.WOODEN_SWORD);

    private final String name;
    private final ItemStack kitIcon;
    private final ItemStack helmed, chestplate, leggings, boots;
    private final Material[] items;

    KitList(String name, ItemStack kitIcon, ItemStack helmed, ItemStack chestplate, ItemStack leggings, ItemStack boots, Material... items) {
        this.name = name;
        this.kitIcon = kitIcon;
        this.helmed = helmed;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public ItemStack getKitIcon() {
        return kitIcon;
    }

    public ItemStack getHelmed() {
        return helmed;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public Material[] getItems() {
        return items;
    }
}
