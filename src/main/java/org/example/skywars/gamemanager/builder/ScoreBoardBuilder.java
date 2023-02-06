package org.example.skywars.gamemanager.builder;

import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardObjective;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardScore;
import net.minecraft.server.ScoreboardServer;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreboardObjective;
import net.minecraft.world.scores.criteria.IScoreboardCriteria;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreBoardBuilder {

    private final ScoreboardObjective objective;
    private final PacketPlayOutScoreboardObjective removePack;
    private final PacketPlayOutScoreboardObjective createPack;

    private final String title;

    private final HashMap<Integer, Object> lines = new HashMap<>();

    public ScoreBoardBuilder(String title) {
        Scoreboard scoreboard = new Scoreboard();
        objective = new ScoreboardObjective(scoreboard
                , title, IScoreboardCriteria.a, IChatBaseComponent.a(title),
                IScoreboardCriteria.EnumScoreboardHealthDisplay.a);
        removePack = new PacketPlayOutScoreboardObjective(objective, 1);
        createPack = new PacketPlayOutScoreboardObjective(objective, 0);
        this.title = title;
    }

    public void setScore(int score, String text) {
        lines.put(score, new PacketPlayOutScoreboardScore(ScoreboardServer.Action.a, title, text, score));
    }

    public void update(ArrayList<Player> p) {
        objective.a(IChatBaseComponent.a(title));
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, objective);
        int max = 0;
        for(Integer i : lines.keySet()) {
            if(i > max) {
                max = i;
            }
        }
        for(Player pl : p) {
            sendPacket(pl, removePack);
            sendPacket(pl, createPack);
            sendPacket(pl, display);
        }
        for(int i = max; i >= 0; i--) {
            if(lines.containsKey(i)) {
                for(Player pl : p) {
                    sendPacket(pl, (Packet<?>) lines.get(i));
                }
            }
        }
    }

    public void setSidebar(Player player) {
        objective.a(IChatBaseComponent.a(title));
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, objective);
        int max = 0;
        for(Integer i : lines.keySet()) {
            if(i > max) {
                max = i;
            }
        }
        sendPacket(player, removePack);
        sendPacket(player, createPack);
        sendPacket(player, display);
        for(int i = max; i >= 0; i--) {
            if(lines.containsKey(i)) {
                sendPacket(player, (Packet<?>) lines.get(i));
            }
        }
    }

    private void sendPacket(Player player, Packet<?> packet) {
        ((CraftPlayer) player).getHandle().b.a(packet);
    }
}
