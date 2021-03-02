package sbr.sbr.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import sbr.sbr.commands.hub;
import sbr.sbr.guis.banker.bankerDeposit;
import sbr.sbr.guis.banker.bankerWithdraw;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class InvClick extends chatcolors implements Listener {
    public static final HashMap<UUID, String> currentGui = new HashMap<>();
    final HashMap<UUID, String> newChat = new HashMap<>();
    public static Player p;
    HashMap<UUID, Integer> SlotClicked = new HashMap<>();
    @EventHandler
    public void InventoryClick(InventoryClickEvent e) {
        p = (Player) e.getWhoClicked();
        if (currentGui.get(p.getUniqueId()) != null) {
            if (currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerMain")) {
                if (e.getRawSlot() == 11) SlotClicked.put(p.getUniqueId(), 11);
                if (e.getRawSlot() == 13) SlotClicked.put(p.getUniqueId(), 13);
                if (e.getRawSlot() == 31) SlotClicked.put(p.getUniqueId(), 31);
                bankerMain();
                e.setCancelled(true);
                return;
            }
            if (currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerWithdraw")) {
                if (e.getRawSlot() == 11) SlotClicked.put(p.getUniqueId(), 11);
                if (e.getRawSlot() == 13) SlotClicked.put(p.getUniqueId() ,13);
                if (e.getRawSlot() == 15) SlotClicked.put(p.getUniqueId(), 15);
                if (e.getRawSlot() == 31) SlotClicked.put(p.getUniqueId(), 31);
                bankerWithdraw();
                e.setCancelled(true);
                return;
            }
            if (currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerDeposit")) {
                if (e.getRawSlot() == 11) SlotClicked.put(p.getUniqueId(), 11);
                if (e.getRawSlot() == 13) SlotClicked.put(p.getUniqueId(), 13);
                if (e.getRawSlot() == 15) SlotClicked.put(p.getUniqueId(), 15);
                if (e.getRawSlot() == 31) SlotClicked.put(p.getUniqueId(), 31);
                bankerDeposit();
                e.setCancelled(true);
            }
            if (currentGui.get(p.getUniqueId()).equalsIgnoreCase("hubMenu")) {
                
                if (e.getRawSlot() == 49) SlotClicked.put(p.getUniqueId(), 49);
                if (e.getRawSlot() == 50) SlotClicked.put(p.getUniqueId(), 50);
                hubMenu();
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void OnInvClose(InventoryCloseEvent e) {
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
                        main.balance(p);
                        int bankBal = main.bankBal;
                        int purseBal = main.purseBal;
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
                        main.balance(p);
                        int bankBal = main.bankBal;
                        int purseBal = main.purseBal;
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
    public void bankerMain() {
        try {
            if (SlotClicked.get(p.getUniqueId()) == 11) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
                p.openInventory(bankerDeposit.getGui());
                currentGui.put(p.getUniqueId(), "bankerDeposit");
            }
            if (SlotClicked.get(p.getUniqueId()) == 13) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
                p.openInventory(bankerWithdraw.getGui());
                currentGui.put(p.getUniqueId(), "bankerWithdraw");
            }
            if (SlotClicked.get(p.getUniqueId()) == 31) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
            }
        } catch (NullPointerException ignored){}
    }
    public void bankerWithdraw() {
        try {
            if (SlotClicked.get(p.getUniqueId()) == 11) {
                SlotClicked.remove(p.getUniqueId());
                try {
                    main.balance(p);
                    int bankBal = main.bankBal;
                    int purseBal = main.purseBal;
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
            if (SlotClicked.get(p.getUniqueId()) == 13) {
                SlotClicked.remove(p.getUniqueId());
                try {
                    main.balance(p);
                    int bankBal = main.bankBal;
                    int purseBal = main.purseBal;
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
            if (SlotClicked.get(p.getUniqueId()) == 15) {
                SlotClicked.remove(p.getUniqueId());
                newChat.put(p.getUniqueId(), "bankerWithdraw");
                p.closeInventory();
                p.sendMessage(color("&7Put the amount you want to withdraw in the chat."));
            }
            if (SlotClicked.get(p.getUniqueId()) == 31) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
            }
        }
        catch (NullPointerException ignored){}
    }
    public void bankerDeposit() {
        try {
            if (SlotClicked.get(p.getUniqueId()) == 11) {
                SlotClicked.remove(p.getUniqueId());
                try {
                    main.balance(p);
                    int bankBal = main.bankBal;
                    int purseBal = main.purseBal;
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
            if (SlotClicked.get(p.getUniqueId()) == 13) {
                SlotClicked.remove(p.getUniqueId());
                try {
                    main.balance(p);
                    int bankBal = main.bankBal;
                    int purseBal = main.purseBal;
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
            if (SlotClicked.get(p.getUniqueId()) == 15) {
                SlotClicked.remove(p.getUniqueId());
                newChat.put(p.getUniqueId(), "bankerDeposit");
                p.closeInventory();
                p.sendMessage(color("&7Put the amount you want to deposit in the chat."));
            }
            if (SlotClicked.get(p.getUniqueId()) == 31) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
            }
        } catch (NullPointerException ignored){}
    }
    public void hubMenu() {
        try {
            if (SlotClicked.get(p.getUniqueId()) == 49) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
            }
            if (SlotClicked.get(p.getUniqueId()) == 50) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
                Random r = new Random();
                String randomHub = hub.hubList[r.nextInt(hub.hubList.length)];
                if (!randomHub.equals(main.currentWorld.get(p.getUniqueId()))) {
                    Bukkit.createWorld(new WorldCreator(randomHub));
                    World setHub = Bukkit.getWorld(randomHub);
                    p.teleport(new Location(setHub, 0.5, 71, 0.5, 180, 0));
                    main.currentWorld.remove(p.getUniqueId());
                    main.currentWorld.put(p.getUniqueId(), randomHub);
                    p.sendMessage(color("&aSent you to hub &b" + randomHub + "&a!"));
                }
                else {
                    p.sendMessage(color("&cError: You are trying to go to the same hub."));
                }
            }
        } catch (NullPointerException ignored) {}
    }
}