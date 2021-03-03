package sbr.sbr.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitScheduler;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;
import sbr.sbr.utils.utils;

public class healthMana extends chatcolors implements Listener {
    @EventHandler
    public void takeDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            p.sendMessage(color("&cYou got hit by: &4" + e.getCause()));
            e.setCancelled(true);
        }
    }
    @SuppressWarnings("deprecation")
    public static void setter(Player p) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(main.instance, () -> {
            int maxHealth = utils.maxHealth.get(p.getUniqueId());
            int currentHealth = utils.currentHealth.get(p.getUniqueId());
            int maxMana = utils.maxMana.get(p.getUniqueId());
            int currentMana = utils.currentMana.get(p.getUniqueId());
            p.sendActionBar(color("&c♥" + currentHealth + "/" + maxHealth + "          " + "&b✎" + currentMana + "/" + maxMana));
        }, 0L, 10);
    }
}
