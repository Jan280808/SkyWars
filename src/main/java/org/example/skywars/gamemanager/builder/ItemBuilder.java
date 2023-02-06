package org.example.skywars.gamemanager.builder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.util.*;

public class ItemBuilder {

    /**
     *  The ItemBuilder offer many function to create &. modify an ItemStack
     */

    private final ItemStack stack;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        this.stack = new ItemStack(material);
        this.meta = stack.getItemMeta();
    }

    /**
     * set the displayName of an ItemStack
     * */


    public ItemBuilder setDisplayName(String name) {
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        stack.setAmount(amount);
        return this;
    }

    public ItemBuilder setRandomEnchantArmor() {
        List<Enchantment> list = new ArrayList<>();
        list.add(Enchantment.PROTECTION_PROJECTILE); list.add(Enchantment.PROTECTION_FIRE); list.add(Enchantment.THORNS);
        meta.addEnchant(list.get(new Random().nextInt(list.size())), 1, true);
        return this;
    }

    public ItemBuilder setRandomEnchantWeapon() {
        List<Enchantment> list = new ArrayList<>();
        list.add(Enchantment.DURABILITY); list.add(Enchantment.DAMAGE_ALL); list.add(Enchantment.KNOCKBACK);
        meta.addEnchant(list.get(new Random().nextInt(list.size())), 1, true);
        return this;
    }

    /**
     * creates a list which will be added to the lore
     */

    public ItemBuilder setLore(String... name) {
        List<String> loreList = new ArrayList<>();
        Collections.addAll(loreList, name);
        meta.setLore(loreList);
        return this;
    }


    /**
     * adds a SkullMeta to the ItemStack
     */

    public ItemBuilder setSkull(String skullOwner) {
        if(stack.getType() != Material.PLAYER_HEAD)
            return this;
        SkullMeta skullMeta = (SkullMeta) meta;
        PlayerProfile playerProfile = Bukkit.createPlayerProfile(UUID.randomUUID(), skullOwner);
        ((SkullMeta) meta).setOwnerProfile(playerProfile.clone());
        stack.setItemMeta(skullMeta);
        return this;
    }

    /**
     * returns an ItemStack
     */

    public ItemStack build() {
        stack.setItemMeta(meta);
        return stack;
    }
}
