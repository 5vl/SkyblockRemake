package sbr.sbr.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;

import java.sql.SQLException;

public class onPlayerJoin extends chatcolors implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
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
