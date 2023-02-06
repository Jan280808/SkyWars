package org.example.skywars.gamemanager.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.example.skywars.gamemanager.GameManager;
import org.example.skywars.gamemanager.builder.ScoreBoardBuilder;

public class ScoredBoardManager {

    private final GameManager gameManager;
    private final ScoreBoardBuilder builder;

    public ScoredBoardManager(GameManager gameManager) {
        this.gameManager = gameManager;
        this.builder = new ScoreBoardBuilder(ChatColor.GOLD + "SkyWars");
    }

    private void updateBoard() {
        builder.setScore(8, ChatColor.GOLD + "");

        builder.setScore(7, "Map");
        builder.setScore(6, ChatColor.WHITE + "???");
        builder.setScore(5, ChatColor.GRAY + "");

        builder.setScore(4, "Players");
        builder.setScore(3, ChatColor.AQUA + "" + gameManager.getGamePlayers().size());
        builder.setScore(2, ChatColor.YELLOW + "");

        builder.setScore(1, "Kit");
        builder.setScore(0, "???");
    }

    public void setScoreBoard() {
        updateBoard();
        gameManager.getGamePlayers().forEach(builder::setSidebar);
    }

}
