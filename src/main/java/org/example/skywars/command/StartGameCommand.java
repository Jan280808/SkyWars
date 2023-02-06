package org.example.skywars.command;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.example.skywars.SkyWars;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.state.LobbyState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StartGameCommand implements TabExecutor {

    private final GameManager gameManager;

    public StartGameCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) return false;
        Player player = (Player) sender;
        if(!player.hasPermission(SkyWars.PERMISSION_HOST)) return false;

        if(gameManager.getGamePlayers().size() < 2) {
            player.sendMessage(SkyWars.MAIN + ChatColor.RED + "There are not enough players online");
            player.playSound(player, Sound.BLOCK_ANVIL_LAND, 0.5F, 1);
            return false;
        }
        if(gameManager.getCurrentState() instanceof LobbyState lobbyState) {
            lobbyState.startWithCommand();
            player.sendMessage(SkyWars.MAIN + "You " + ChatColor.GREEN + "started " + ChatColor.GRAY + "the game");
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 0.5F, 1);
            return true;
        }
        player.sendMessage(SkyWars.MAIN + ChatColor.RED + "The game has already stared...");
        player.playSound(player, Sound.BLOCK_ANVIL_LAND, 0.5F, 1);
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
