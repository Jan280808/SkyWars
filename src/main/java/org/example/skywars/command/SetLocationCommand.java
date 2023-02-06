package org.example.skywars.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.example.skywars.SkyWars;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.state.map.Locations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetLocationCommand implements TabExecutor {

    private final GameManager gameManager;
    private final List<String> settableLocList;

    public SetLocationCommand(GameManager gameManager) {
        this.gameManager = gameManager;
        this.settableLocList = new ArrayList<>();
        Arrays.stream(Locations.values()).forEach(locations -> settableLocList.add(locations.getString()));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender)
            return false;
        Player player = (Player) sender;
        if(!player.hasPermission(SkyWars.PERMISSION_ADMIN)) return false;

        if(args.length == 0) {
            player.sendMessage("");
            return false;
        }
        Location playerLoc = player.getLocation();
        if(settableLocList.contains(args[0])) {
            gameManager.getMap().setLocation(args[0], playerLoc);
            player.sendMessage(SkyWars.MAIN + "a new location was set with the id " + ChatColor.GOLD + args[0]);
            return true;

        } else {
            player.sendMessage(SkyWars.MAIN + ChatColor.RED + "the tried set location " + ChatColor.GOLD + args[0] + ChatColor.RED + " does not exist");
            player.playSound(playerLoc, Sound.BLOCK_ANVIL_PLACE, 1, 1);
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender)
            return null;
        Player player = (Player) sender;
        if(!player.hasPermission(SkyWars.PERMISSION_ADMIN)) return null;
        return settableLocList;
    }
}
