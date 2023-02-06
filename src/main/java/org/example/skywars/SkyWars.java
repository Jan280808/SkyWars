package org.example.skywars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.skywars.command.SetLocationCommand;
import org.example.skywars.command.SetLootChestCommand;
import org.example.skywars.command.StartGameCommand;
import org.example.skywars.event.*;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.state.GameState;

import java.util.Objects;

public final class SkyWars extends JavaPlugin {

    public static SkyWars inst;
    public static final String MAIN = ChatColor.GOLD + "SkyWars" + ChatColor.DARK_GRAY + " ● " + ChatColor.GRAY;
    public static final String PERMISSION_ADMIN = "skywars.admin",
                               PERMISSION_HOST = "skywars.host";
    private GameManager gameManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        inst = this;
        this.gameManager = new GameManager();
        gameManager.setCurrentState(GameState.LOBBY_STATE);
        registerListener(Bukkit.getPluginManager());
        registerCommands();

        Objects.requireNonNull(Bukkit.getWorld("world")).setDifficulty(Difficulty.NORMAL);
        Objects.requireNonNull(Bukkit.getWorld("world")).setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListener(PluginManager pluginManager) {
        pluginManager.registerEvents(new PlayerConnection(gameManager), this);
        pluginManager.registerEvents(new PlayerDamage(gameManager), this);
        pluginManager.registerEvents(new BlockEvents(gameManager), this);
        pluginManager.registerEvents(new PlayerBlockInteraction(gameManager), this);
        pluginManager.registerEvents(new PlayerClickInventory(gameManager), this);
        pluginManager.registerEvents(new PlayerInteraction(gameManager), this);
        pluginManager.registerEvents(new PlayerOpenInventory(gameManager), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("setLocation")).setExecutor(new SetLocationCommand(gameManager));
        Objects.requireNonNull(getCommand("start")).setExecutor(new StartGameCommand(gameManager));
        Objects.requireNonNull(getCommand("setLootChest")).setExecutor(new SetLootChestCommand(gameManager));
    }
}
