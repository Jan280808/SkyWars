package org.example.skywars.command;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.example.skywars.SkyWars;
import org.example.skywars.gamemanager.GameManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class SetLootChestCommand implements TabExecutor {

    private final GameManager gameManager;

    private final String normalLoot = "normalLoot",
                         rareLoot = "rareLoot";

    public SetLootChestCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override @Deprecated
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) return false;
        Player player = (Player) sender;
        if(!player.hasPermission(SkyWars.PERMISSION_ADMIN)) return false;
        if(args.length != 1) return false;

        if(player.getTargetBlock(null, 4).getType() != Material.CHEST)  {
            player.sendMessage(SkyWars.MAIN + ChatColor.RED + "You have to look at a chest");
            player.playSound(player, Sound.BLOCK_ANVIL_LAND, 0.5F, 1);
            return false;
        }
        Location location = player.getTargetBlock(null, 4).getLocation();
        if(args[0].equals(normalLoot)) {
            gameManager.getLootManager().setLocation(false, location);
            Bukkit.broadcastMessage(SkyWars.MAIN + "A " + ChatColor.GOLD + "NormalLootChest " + ChatColor.GRAY + "location was set");

        } else if(args[0].equals(rareLoot)) {
            gameManager.getLootManager().setLocation(true, location);
            Bukkit.broadcastMessage(SkyWars.MAIN + "A " + ChatColor.GOLD + "RareLootChest " + ChatColor.GRAY + "location was set");

        } else
            return false;

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) return null;
        Player player = (Player) sender;
        if(!player.hasPermission(SkyWars.PERMISSION_ADMIN)) return null;

        if(args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add(normalLoot); list.add(rareLoot);
            return list;
        }
        return null;
    }
}
