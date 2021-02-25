package sbr.sbr.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import sbr.sbr.commands.banker;
import sbr.sbr.guis.banker.bankerWithdraw;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class InvClick extends chatcolors implements Listener {
    public static HashMap<UUID, String> currentGui = new HashMap<>();
    @EventHandler
    public void InventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerMain")) {
            if (e.getSlot() == 11) {
                p.closeInventory();
            }
            if (e.getSlot() == 13) {
                currentGui.remove(p.getUniqueId());
                p.openInventory(bankerWithdraw.getGui());
                currentGui.put(p.getUniqueId(), "bankerWithdraw");
            }
            e.setCancelled(true);
        }
        if (currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerWithdraw")) {
            if (e.getSlot() == 13) {
                int bankBal;
                int purseBal;
                try {
                    ResultSet bank = main.prepareStatement("SELECT * FROM bank WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                    ResultSet purse = main.prepareStatement("SELECT * FROM purse WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                    bank.next();
                    purse.next();
                    bankBal = bank.getInt("Balance");
                    purseBal = purse.getInt("Balance");
                    int bankHalf = bankBal / 2;
                    int newPurse = purseBal + bankHalf;
                    main.prepareStatement("UPDATE bank SET Balance = '" + bankHalf + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                    main.prepareStatement("UPDATE purse SET Balance = '" + newPurse + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                    p.closeInventory();
                } catch (SQLException x) {
                    x.printStackTrace();
                }
            }
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void OnInvClose (InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        currentGui.remove(p.getUniqueId());
    }
}
