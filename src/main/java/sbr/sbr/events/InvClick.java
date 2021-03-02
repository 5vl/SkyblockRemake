package sbr.sbr.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerChatEvent;
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
    final HashMap<UUID, String> newChat = new HashMap<>();
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
                if (e.getRawSlot() == 31) {
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
                if (e.getRawSlot() == 15) {
                    newChat.put(p.getUniqueId(), "bankerWithdraw");
                    p.closeInventory();
                    p.sendMessage(color("&7Put the amount you want to withdraw in the chat."));
                }
                if (e.getRawSlot() == 31) {
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
                if (e.getRawSlot() == 15) {
                    newChat.put(p.getUniqueId(), "bankerDeposit");
                    p.closeInventory();
                    p.sendMessage(color("&7Put the amount you want to deposit in the chat."));
                }
                if (e.getRawSlot() == 31) {
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
    @SuppressWarnings("deprecation")
    @EventHandler
    public void OnChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        try {
            if (newChat.get(p.getUniqueId()).equalsIgnoreCase("bankerWithdraw")) {
                e.setCancelled(true);
                newChat.remove(p.getUniqueId());
                try {
                    String msg = e.getMessage();
                    int number = Integer.parseInt(msg);
                    if (number < 1) {
                        p.sendMessage(color("&cAmount must be higher than 0!"));
                        return;
                    }
                    try {
                        ResultSet bank = main.prepareStatement("SELECT * FROM bank WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        ResultSet purse = main.prepareStatement("SELECT * FROM purse WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        bank.next();
                        purse.next();
                        int bankBal = bank.getInt("Balance");
                        int purseBal = purse.getInt("Balance");
                        int newBank = bankBal - number;
                        if (newBank < 0) {
                            p.sendMessage(color("&cYou do not have enough coins in your bank to do that!"));
                            return;
                        }
                        int newPurse = purseBal + number;
                        main.prepareStatement("UPDATE bank SET Balance = '" + newBank + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        main.prepareStatement("UPDATE purse SET Balance = '" + newPurse + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        p.closeInventory();
                        p.sendMessage(color("&aWithdrew &6" + number + " &afrom your bank."));
                        p.sendMessage(color("&aYou now have &6" + newBank + " &ain your bank."));
                    } catch (SQLException x) {
                        x.printStackTrace();
                        p.sendMessage(color("&4&lSomething went wrong, please report this to the devs ASAP."));
                    }
                } catch (NumberFormatException ignored) {
                    p.sendMessage(color("&cYou have to put in a correct amount!"));
                }
            }
            if (newChat.get(p.getUniqueId()).equalsIgnoreCase("bankerDeposit")) {
                e.setCancelled(true);
                newChat.remove(p.getUniqueId());
                try {
                    String msg = e.getMessage();
                    int number = Integer.parseInt(msg);
                    if (number < 1) {
                        p.sendMessage(color("&cAmount must be higher than 0!"));
                        return;
                    }
                    try {
                        ResultSet bank = main.prepareStatement("SELECT * FROM bank WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        ResultSet purse = main.prepareStatement("SELECT * FROM purse WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
                        bank.next();
                        purse.next();
                        int bankBal = bank.getInt("Balance");
                        int purseBal = purse.getInt("Balance");
                        int newBank = bankBal + number;
                        int newPurse = purseBal - number;
                        if (newPurse < 0) {
                            p.sendMessage(color("&cYou do not have enough coins in your purse to do that!"));
                            return;
                        }
                        main.prepareStatement("UPDATE bank SET Balance = '" + newBank + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        main.prepareStatement("UPDATE purse SET Balance = '" + newPurse + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                        p.closeInventory();
                        p.sendMessage(color("&aDeposited &6" + number + " &ato your bank."));
                        p.sendMessage(color("&aYou now have &6" + newBank + " &ain your bank."));
                    } catch (SQLException x) {
                        x.printStackTrace();
                        p.sendMessage(color("&4&lSomething went wrong, please report this to the devs ASAP."));
                    }
                } catch (NumberFormatException ignored) {
                    p.sendMessage(color("&cYou have to put in a correct amount!"));
                }
            }
        } catch (NullPointerException ignored) {}
    }
}