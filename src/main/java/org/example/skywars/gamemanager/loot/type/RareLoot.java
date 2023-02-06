package org.example.skywars.gamemanager.loot.type;

import org.bukkit.Material;

public enum RareLoot {
    ;

    private final Material material;
    private final int maxAmount;
    private final double probability;

    RareLoot(Material material, int maxAmount, double probability) {
        this.material = material;
        this.maxAmount = maxAmount;
        this.probability = probability;
    }

    public Material getMaterial() {
        return material;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public double getProbability() {
        return probability;
    }
}
