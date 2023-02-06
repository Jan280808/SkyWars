package org.example.skywars.gamemanager.loot.type;

import org.bukkit.Material;
import org.example.skywars.gamemanager.builder.ItemBuilder;

public enum NormalLootPool {
    BLOCK_BRICK(1, 58, Material.BRICKS),
    BLOCK_WOOD(2, 25, Material.DARK_OAK_WOOD),
    GOLD_SWORD(3, 1, Material.GOLDEN_SWORD),
    FOOD_CARROT(4, 8, Material.GOLDEN_CARROT),
    ITEM_ARROW(5, 5, Material.ARROW),
    ITEM_DIAMOND(6, 5, Material.DIAMOND),
    ITEM_IRON(7, 9, Material.IRON_INGOT),
    ITEM_TNT(8, 8, Material.TNT),
    ITEM_COBWEB(9, 4, Material.COBWEB),
    ITEM_XP_BOTTLE(10, 32, Material.EXPERIENCE_BOTTLE),
    FOOD_GOLDEN_APPLE(11, 3, Material.GOLDEN_APPLE),
    ITEM_WATTER_BUCKET(12, 1, Material.WATER_BUCKET),
    FOOD_STEAK(13, 23, Material.PUMPKIN_PIE),
    ITEM_ENDER_PEARL(14, 1, Material.ENDER_PEARL),
    ITEM_SHIELD(15, 1, Material.SHIELD),
    ITEM_FLINT_AND_STEAL(16, 1, Material.FLINT_AND_STEEL),
    FOOD_MUTTON(17, 12, Material.COOKED_MUTTON),
    FOOD_BEEF(18, 18, Material.COOKED_BEEF),
    FOOD_BREAD(19, 5, Material.BREAD),
    ITEM_FIRE_CHARGE(20, 2, Material.FIRE_CHARGE),
    ITEM_GOLD_INGOT(21, 7, Material.GOLD_INGOT),
    ITEM_FISHING_ROD(22, 1, Material.FISHING_ROD),
    ITEM_SPYGLASS(23, 1, Material.SPYGLASS),
    BLOCK_PLANKS(24, 23, Material.WARPED_PLANKS),
    BLOCK_CUT_STONE(25, 31, Material.SMOOTH_STONE),
    BLOCK_OAK_PLANKS(26, 18, Material.OAK_PLANKS),
    BLOCK_DEEP_SLATE(27, 16, Material.POLISHED_DEEPSLATE),
    Block_BLACKSTONE_BRICK(28, 17, Material.POLISHED_BLACKSTONE_BRICKS),
    BLOCK_ORANGE_CONCRETE(29, 11, Material.ORANGE_CONCRETE),
    BLOCK_SCAFFOLDING(30, 9, Material.SCAFFOLDING),

    ARMOR_DIAMOND_HELM(31, 1, new ItemBuilder(Material.DIAMOND_HELMET).setRandomEnchantArmor().build()),
    ARMOR_DIAMOND_CHEST_PLATE(32, 1, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setRandomEnchantArmor().build()),
    ARMOR_IRON_HELM(33, 1, new ItemBuilder(Material.IRON_HELMET).setRandomEnchantArmor().build()),
    ARMOR_IRON_CHESTPLATE(34, 1, new ItemBuilder(Material.IRON_CHESTPLATE).setRandomEnchantArmor().build()),
    ARMOR_IRON_LEGGING(35, 1, new ItemBuilder(Material.IRON_LEGGINGS).setRandomEnchantArmor().build()),
    ARMOR_GOLD_BOOTS(36, 1, new ItemBuilder(Material.GOLDEN_BOOTS).setRandomEnchantArmor().build()),

    WEAPON_GOLDEN_AXT(37, 1, new ItemBuilder(Material.GOLDEN_AXE).setRandomEnchantWeapon().build()),
    WEAPON_GOLDEN_SWORD(38, 1, new ItemBuilder(Material.GOLDEN_SWORD).setRandomEnchantWeapon().build()),
    WEAPON_IRON_SWORD(39, 1, new ItemBuilder(Material.IRON_SWORD).setRandomEnchantWeapon().build()),
    WEAPON_IRON_AXT(40, 1, new ItemBuilder(Material.IRON_SWORD).setRandomEnchantWeapon().build());





    private final int ID;
    private final int maxAmount;
    private final Object object;

    NormalLootPool(int id, int maxAmount, Object object) {
        ID = id;
        this.maxAmount = maxAmount;
        this.object = object;
    }

    public int getID() {
        return ID;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public Object getObject() {
        return object;
    }
}
