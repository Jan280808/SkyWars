package org.example.skywars.event;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;
import org.example.skywars.SkyWars;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.state.PlayState;

import java.util.Random;

public class PlayerDamage implements Listener {

    private final GameManager gameManager;


    public PlayerDamage(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity().getType() != EntityType.PLAYER) return;
        Player player = (Player) event.getEntity();

        if(gameManager.getCurrentState() instanceof PlayState playState) {
            if(playState.getCountdown().isRunning) {
                event.setCancelled(true);
                return;
            }
            if(!gameManager.getGamePlayers().contains(player)) return;

            spawnDamageParticle(player);

            double playerHealth = player.getHealth();
            double damage = event.getDamage();

            if(damage < playerHealth) return;
            if(player.getKiller() != null) {
                Player killer = player.getKiller();
                Bukkit.broadcastMessage(SkyWars.MAIN + "The player " + ChatColor.RED + player.getName() + ChatColor.GRAY + " was killed by " + ChatColor.GOLD + killer.getName());
                killer.playSound(killer, Sound.ENTITY_PLAYER_LEVELUP, 0.5F, 1F);

            } else {
                Bukkit.broadcastMessage(SkyWars.MAIN + "The player " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " has" + ChatColor.RED +  " died");
                player.playSound(player, Sound.BLOCK_ANVIL_LAND, 0.5F, 1F);
            }

            //respawn logic
            player.setGameMode(GameMode.SPECTATOR);
            gameManager.getGamePlayers().remove(player);
            //gameManager.getScoredBoardManager().setScoreBoard();
            gameManager.getMap().teleportSpectatorPlayer(player);

            if(gameManager.getGamePlayers().size() != 1) Bukkit.broadcastMessage(SkyWars.MAIN + "There are still " + gameManager.getGamePlayers().size() + " players alive");

            //gameEnd logic
            gameManager.checkIfPlayerHasWon();

        } else
            event.setCancelled(true);
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {

    }

    private void spawnDamageParticle(Player target) {
        for(int i = 0; i <8; i++) {
            Random random = new Random();
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, random.nextInt(1)+2);
            double angle = random.nextFloat() * 2 * Math.PI;
            double x = 0.2 * Math.cos(angle);
            double y = 2 * Math.cos(angle);
            double z = 0.6 * Math.sin(angle);
            Location randomLocation = target.getLocation().clone().add(new Vector(x, y, z));
            target.getWorld().spawnParticle(Particle.REDSTONE, randomLocation, 0, dustOptions);
        }
    }
}
