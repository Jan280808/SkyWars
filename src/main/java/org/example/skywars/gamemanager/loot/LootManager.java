package org.example.skywars.gamemanager.loot;

import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.example.skywars.SkyWars;
import org.example.skywars.gamemanager.builder.FileBuilder;
import org.example.skywars.gamemanager.builder.ItemBuilder;
import org.example.skywars.gamemanager.loot.type.NormalLootPool;

import java.util.*;

public class LootManager {

    private final FileBuilder normalFileBuilder;
    private final FileBuilder rareFileBuilder;
    private final List<LootChest> normalLootChestList;
    private final Map<Integer, ItemStack> normalMap;

    public LootManager() {
        this.normalFileBuilder = new FileBuilder("NormalChestLocation");
        this.rareFileBuilder = new FileBuilder("RareChestLocation");
        this.normalLootChestList = new ArrayList<>();
        this.normalMap = new HashMap<>();
    }

    int rareAmount, normalAmount;
    public void setLocation(boolean kindOfLootChest, Location location) {
        if(kindOfLootChest) {
            rareFileBuilder.setLocation("lootChest"+rareAmount, location);
            rareAmount = rareAmount+1;
            Bukkit.getConsoleSender().sendMessage(SkyWars.MAIN + "a location for the rare_LootChest"+rareAmount + " has been set");
        } else {
            normalFileBuilder.setLocation("lootChest"+normalAmount, location);
            normalAmount = normalAmount+1;
            Bukkit.getConsoleSender().sendMessage(SkyWars.MAIN + "a location for the normal_LootChest"+normalAmount + " has been set");
        }
    }

    public void loadLootChestCache() {
        normalFileBuilder.getStrings(false).forEach(s -> {
            Inventory inventory = Bukkit.createInventory(null, 27, "");
            Location location = normalFileBuilder.getLocation(s);
            location.getBlock().setType(Material.CHEST);

            LootChest lootChest = new LootChest(location, inventory);
            normalLootChestList.add(lootChest);
            Bukkit.getConsoleSender().sendMessage(SkyWars.MAIN + "an " + ChatColor.BLUE + "normal_LootChest" + ChatColor.GRAY + " has been " + ChatColor.GREEN + " created");
        });
        //activateEffectScheduler();
    }

    //too complication...
    public void fillChest() {
        normalLootChestList.forEach(lootChest -> {
            Location location = lootChest.getLocation();
            if(location.getBlock().getState() instanceof Chest chest) {
                Inventory inventory = chest.getInventory();
                inventory.clear();

                //file items code
                Arrays.stream(NormalLootPool.values()).forEach(normalLootPool -> {
                    if(normalLootPool.getObject() instanceof Material material) {
                        Random random = new Random();
                        int randomIntAmount = random.nextInt(normalLootPool.getMaxAmount()) + 1;
                        ItemStack itemStack = new ItemBuilder(material).setAmount(randomIntAmount).build();
                        normalMap.put(normalLootPool.getID(), itemStack);

                    } else {
                        normalMap.put(normalLootPool.getID(), (ItemStack) normalLootPool.getObject());
                    }

                });
                for(int i = 0; i < 20; i++) {
                    Random random = new Random();
                    int randomInt = random.nextInt(NormalLootPool.values().length) + 1;
                    ItemStack itemStack = normalMap.get(randomInt);
                    inventory.setItem(new Random().nextInt(inventory.getSize()), itemStack);
                }
            }
        });
    }

    protected int taskID;
    private Particle.DustOptions dustOptions;
    private void activateEffectScheduler() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.inst, () -> normalLootChestList.forEach(lootChest -> {
            Location location = lootChest.getLocation().clone().add(+0.5, 1, +0.5);
            if(!lootChest.getOpened()) {
                dustOptions = new Particle.DustOptions(Color.RED, 2);
                location.getWorld().spawnParticle(Particle.REDSTONE, location, 1, dustOptions);

            } else if(lootChest.getInventory().getContents().length != 0) {
                dustOptions = new Particle.DustOptions(Color.YELLOW, 2);
                location.getWorld().spawnParticle(Particle.REDSTONE, location, 1, dustOptions);
            }

        }), 0, 20);
    }

    public void setOpenStatus(Location location) {
        normalLootChestList.forEach(lootChest -> {
            if(lootChest.getLocation() == location) {
                lootChest.setOpened();
            }
        });
    }
}
