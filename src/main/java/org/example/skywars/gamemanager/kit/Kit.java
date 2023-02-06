package org.example.skywars.gamemanager.kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kit {

    private final String name;
    private final ItemStack icon;
    private final ItemStack helmed;
    private final ItemStack chestplate;
    private final ItemStack leggings;
    private final ItemStack boots;
    private final List<ItemStack> items;

    public Kit(String name, ItemStack icon, ItemStack helmed, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        this.name = name;
        this.icon = icon;
        this.helmed = helmed;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ItemStack getIcon() {
        return icon;
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

    public void setItems(Material[] materials) {
        Arrays.stream(materials).forEach(material -> {
            ItemStack itemStack = new ItemStack(material);
            items.add(itemStack);
        });
    }

    public List<ItemStack> getItems() {
        return items;
    }
}
