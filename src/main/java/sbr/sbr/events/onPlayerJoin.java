package sbr.sbr.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.*;
import sbr.sbr.commands.hub;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;

import java.sql.SQLException;
import java.util.Random;

public class onPlayerJoin extends chatcolors implements Listener {
    Player p;
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        p = e.getPlayer();
        randomHub();
        scoreboard();
        data();
    }

    public void randomHub() {
        Random r = new Random();
        String randomHub = hub.hubList[r.nextInt(hub.hubList.length)];
        Bukkit.createWorld(new WorldCreator(randomHub));
        World setHub = Bukkit.getWorld(randomHub);
        p.teleport(new Location(setHub, 0.5, 71, 0.5, 180, 0));
        main.currentWorld.put(p.getUniqueId(), randomHub);
        p.sendMessage(color("&aYou are currently in hub &b" + randomHub + "&a!"));
    }

    @SuppressWarnings("deprecation")
    public void scoreboard() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(main.instance, () -> {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            Objective objective = board.registerNewObjective("sb", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(color("&e&lSkyblock"));
            main.balance(p);
            int bank = main.bankBal;
            int purse = main.purseBal;
            Score L1 = objective.getScore(color("&7" + main.currentWorld.get(p.getUniqueId())));
            L1.setScore(15);
            Score L2 = objective.getScore(color("&r "));
            L2.setScore(14);
            Score L3 = objective.getScore(color("&bBank: &6" + bank));
            L3.setScore(13);
            Score L4 = objective.getScore(color("&bPurse: &6" + purse));
            L4.setScore(12);
            p.setScoreboard(board);
        }, 0L, 5);
    }

    public void data() {
        if (!p.hasPlayedBefore()) {
            try {
                main.prepareStatement("INSERT INTO bank(UUID, Balance) VALUES('" + p.getUniqueId().toString() + "', 0);").executeUpdate();
                main.prepareStatement("INSERT INTO purse(UUID, Balance) VALUES('" + p.getUniqueId().toString() + "', 0);").executeUpdate();
            } catch (SQLException x) {
                x.printStackTrace();
            }
            p.sendMessage(color("&c&lWelcome to the server!"));
        }
        else {
            p.sendMessage(color("&a&lWelcome back to the server!"));
        }
    }
}
