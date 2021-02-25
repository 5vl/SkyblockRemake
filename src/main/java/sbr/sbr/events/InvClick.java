package sbr.sbr.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import sbr.sbr.commands.banker;
import sbr.sbr.guis.banker.bankerDeposit;
import sbr.sbr.guis.banker.bankerWithdraw;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class InvClick extends chatcolors implements Listener {
    public static final HashMap<UUID, String> currentGui = new HashMap<>();
    @EventHandler
    public void InventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (currentGui.get(p.getUniqueId()) != null) {
            if (currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerMain")) {
                if (e.getRawSlot() == 11) {
                    p.closeInventory();
                    p.openInventory(bankerDeposit.getGui());
                    currentGui.put(p.getUniqueId(), "bankerDeposit");
                }
                if (e.getRawSlot() == 13) {
                    p.closeInventory();
                    p.openInventory(bankerWithdraw.getGui());
                    currentGui.put(p.getUniqueId(), "bankerWithdraw");
                }
                if (e.getRawSlot() == 32) {
                    p.closeInventory();
                }
                e.setCancelled(true);
                return;
            }
            if (currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerWithdraw")) {
                if (e.getRawSlot() == 11) {
                    try {
                        ResultSet bank = main.prepareStatement("SELECT * FROM bank WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        ResultSet purse = main.prepareStatement("SELECT * FROM purse WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        bank.next();
                        purse.next();
                        int bankBal = bank.getInt("Balance");
                        int purseBal = purse.getInt("Balance");
                        int bankHalf = bankBal / 2;
                        int newPurse = purseBal + bankHalf;
                        main.prepareStatement("UPDATE bank SET Balance = '" + bankHalf + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        main.prepareStatement("UPDATE purse SET Balance = '" + newPurse + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        p.closeInventory();
                        p.sendMessage(color("&aWithdrew &6" + bankHalf + " &afrom your bank."));
                    } catch (SQLException x) {
                        x.printStackTrace();
                        p.sendMessage(color("&4&lSomething went wrong, please report this to the devs ASAP."));
                    }
                }
                if (e.getRawSlot() == 13) {
                    try {
                        ResultSet bank = main.prepareStatement("SELECT * FROM bank WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        ResultSet purse = main.prepareStatement("SELECT * FROM purse WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        bank.next();
                        purse.next();
                        int bankBal = bank.getInt("Balance");
                        int purseBal = purse.getInt("Balance");
                        int newPurse = purseBal + bankBal;
                        main.prepareStatement("UPDATE bank SET Balance = '0' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        main.prepareStatement("UPDATE purse SET Balance = '" + newPurse + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        p.closeInventory();
                        p.sendMessage(color("&aWithdrew &6" + bankBal + " &afrom your bank."));
                        p.sendMessage(color("&aYou now have &60 &ain your bank."));
                    } catch (SQLException x) {
                        x.printStackTrace();
                        p.sendMessage(color("&4&lSomething went wrong, please report this to the devs ASAP."));
                    }
                }
                if (e.getRawSlot() ==  32) {
                    p.closeInventory();
                }
                e.setCancelled(true);
                return;
            }
            if (currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerDeposit")) {
                if (e.getRawSlot() == 11) {
                    try {
                        ResultSet bank = main.prepareStatement("SELECT * FROM bank WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        ResultSet purse = main.prepareStatement("SELECT * FROM purse WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        bank.next();
                        purse.next();
                        int bankBal = bank.getInt("Balance");
                        int purseBal = purse.getInt("Balance");
                        int purseHalf = purseBal / 2;
                        int newBank = bankBal + purseHalf;
                        main.prepareStatement("UPDATE bank SET Balance = '" + newBank + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        main.prepareStatement("UPDATE purse SET Balance = '" + purseHalf + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        p.closeInventory();
                        p.sendMessage(color("&aDeposited &6" + purseHalf + " &ato your bank."));
                        p.sendMessage(color("&aYou now have &6" + newBank + " &ain your bank."));
                    } catch (SQLException x) {
                        x.printStackTrace();
                        p.sendMessage(color("&4&lSomething went wrong, please report this to the devs ASAP."));
                    }
                }
                if (e.getRawSlot() == 13) {
                    try {
                        ResultSet bank = main.prepareStatement("SELECT * FROM bank WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        ResultSet purse = main.prepareStatement("SELECT * FROM purse WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        bank.next();
                        purse.next();
                        int bankBal = bank.getInt("Balance");
                        int purseBal = purse.getInt("Balance");
                        int newBank = bankBal + purseBal;
                        main.prepareStatement("UPDATE bank SET Balance = '" + newBank + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        main.prepareStatement("UPDATE purse SET Balance = '0' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        p.closeInventory();
                        p.sendMessage(color("&aDeposited &6" + purseBal + " &ato your bank."));
                        p.sendMessage(color("&aYou now have &6" + newBank + " &ain your bank."));
                    } catch (SQLException x) {
                        x.printStackTrace();
                        p.sendMessage(color("&4&lSomething went wrong, please report this to the devs ASAP."));
                    }
                }
                if (e.getRawSlot() == 32) {
                    p.closeInventory();
                }
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void OnInvClose (InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        currentGui.remove(p.getUniqueId());
    }
}
