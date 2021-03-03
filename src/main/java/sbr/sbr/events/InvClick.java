package sbr.sbr.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerChatEvent;
import sbr.sbr.commands.hub;
import sbr.sbr.guis.banker.bankerDeposit;
import sbr.sbr.guis.banker.bankerWithdraw;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;
import sbr.sbr.utils.utils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class InvClick extends chatcolors implements Listener {
    public static Player p;
    final HashMap<UUID, String> newChat = new HashMap<>();
    HashMap<UUID, Integer> SlotClicked = new HashMap<>();
    String itemName, itemType;
    @SuppressWarnings("deprecation")
    @EventHandler
    public void InventoryClick(InventoryClickEvent e) {
        p = (Player) e.getWhoClicked();
        if (utils.currentGui.get(p.getUniqueId()) != null) {
            if (utils.currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerMain")) {
                if (e.getRawSlot() == 11) SlotClicked.put(p.getUniqueId(), 11);
                if (e.getRawSlot() == 13) SlotClicked.put(p.getUniqueId(), 13);
                if (e.getRawSlot() == 31) SlotClicked.put(p.getUniqueId(), 31);
                bankerMain();
                e.setCancelled(true);
                return;
            }
            if (utils.currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerWithdraw")) {
                if (e.getRawSlot() == 11) SlotClicked.put(p.getUniqueId(), 11);
                if (e.getRawSlot() == 13) SlotClicked.put(p.getUniqueId(), 13);
                if (e.getRawSlot() == 15) SlotClicked.put(p.getUniqueId(), 15);
                if (e.getRawSlot() == 31) SlotClicked.put(p.getUniqueId(), 31);
                bankerWithdraw();
                e.setCancelled(true);
                return;
            }
            if (utils.currentGui.get(p.getUniqueId()).equalsIgnoreCase("bankerDeposit")) {
                if (e.getRawSlot() == 11) SlotClicked.put(p.getUniqueId(), 11);
                if (e.getRawSlot() == 13) SlotClicked.put(p.getUniqueId(), 13);
                if (e.getRawSlot() == 15) SlotClicked.put(p.getUniqueId(), 15);
                if (e.getRawSlot() == 31) SlotClicked.put(p.getUniqueId(), 31);
                bankerDeposit();
                e.setCancelled(true);
                return;
            }
            if (utils.currentGui.get(p.getUniqueId()).equalsIgnoreCase("hubMenu")) {
                if (e.getRawSlot() >= 10 && e.getRawSlot() <= 16) {
                    SlotClicked.put(p.getUniqueId(), e.getRawSlot());
                    String itemNameA = Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName();
                    itemName = itemNameA.substring(2, 7);
                    itemType = e.getCurrentItem().getI18NDisplayName();
                }
                if (e.getRawSlot() >= 19 && e.getRawSlot() <= 25) {
                    SlotClicked.put(p.getUniqueId(), e.getRawSlot());
                    String itemNameA = Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName();
                    itemName = itemNameA.substring(2, 7);
                    itemType = e.getCurrentItem().getI18NDisplayName();
                }
                if (e.getRawSlot() >= 28 && e.getRawSlot() <= 34) {
                    SlotClicked.put(p.getUniqueId(), e.getRawSlot());
                    String itemNameA = Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName();
                    itemName = itemNameA.substring(2, 7);
                    itemType = e.getCurrentItem().getI18NDisplayName();
                }
                if (e.getRawSlot() >= 37 && e.getRawSlot() <= 43) {
                    SlotClicked.put(p.getUniqueId(), e.getRawSlot());
                    String itemNameA = Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName();
                    itemName = itemNameA.substring(2, 7);
                    itemType = e.getCurrentItem().getI18NDisplayName();
                }
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
        utils.currentGui.remove(p.getUniqueId());
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
                        utils.balance(p);
                        int bankBal = utils.bankBal;
                        int purseBal = utils.purseBal;
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
                        utils.balance(p);
                        int bankBal = utils.bankBal;
                        int purseBal = utils.purseBal;
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
                p.openInventory(bankerDeposit.getGui(p));
                utils.currentGui.put(p.getUniqueId(), "bankerDeposit");
            }
            if (SlotClicked.get(p.getUniqueId()) == 13) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
                p.openInventory(bankerWithdraw.getGui(p));
                utils.currentGui.put(p.getUniqueId(), "bankerWithdraw");
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
                    utils.balance(p);
                    int bankBal = utils.bankBal;
                    int purseBal = utils.purseBal;
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
                    utils.balance(p);
                    int bankBal = utils.bankBal;
                    int purseBal = utils.purseBal;
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
                    utils.balance(p);
                    int bankBal = utils.bankBal;
                    int purseBal = utils.purseBal;
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
                    utils.balance(p);
                    int bankBal = utils.bankBal;
                    int purseBal = utils.purseBal;
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
        }
        catch (NullPointerException ignored){}
    }
    public void hubMenu() {
        try {
            if (SlotClicked.get(p.getUniqueId()) >= 10 && SlotClicked.get(p.getUniqueId()) <= 16) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
                if (itemType.equalsIgnoreCase("Red Concrete")) {
                    p.sendMessage(color("&cYou are already in this hub!"));
                    return;
                }
                if (hub.hubs.contains(itemName) && Bukkit.getWorld(itemName) != null) {
                    Bukkit.createWorld(new WorldCreator(itemName));
                    World setHub = Bukkit.getWorld(itemName);
                    p.teleport(new Location(setHub, 0.5, 71, 0.5, 180, 0));
                    utils.currentWorld.remove(p.getUniqueId());
                    utils.currentWorld.put(p.getUniqueId(), itemName);
                    p.sendMessage(color("&aSent you to hub &b" + itemName + "&a!"));
                }
            }
            if (SlotClicked.get(p.getUniqueId()) >= 19 && SlotClicked.get(p.getUniqueId()) <= 25) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
                if (itemType.equalsIgnoreCase("Red Concrete")) {
                    p.sendMessage(color("&cYou are already in this hub!"));
                    return;
                }
                if (hub.hubs.contains(itemName) && Bukkit.getWorld(itemName) != null) {
                    Bukkit.createWorld(new WorldCreator(itemName));
                    World setHub = Bukkit.getWorld(itemName);
                    p.teleport(new Location(setHub, 0.5, 71, 0.5, 180, 0));
                    utils.currentWorld.remove(p.getUniqueId());
                    utils.currentWorld.put(p.getUniqueId(), itemName);
                    p.sendMessage(color("&aSent you to hub &b" + itemName + "&a!"));
                }
            }
            if (SlotClicked.get(p.getUniqueId()) >= 28 && SlotClicked.get(p.getUniqueId()) <= 34) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
                if (itemType.equalsIgnoreCase("Red Concrete")) {
                    p.sendMessage(color("&cYou are already in this hub!"));
                    return;
                }
                if (hub.hubs.contains(itemName) && Bukkit.getWorld(itemName) != null) {
                    Bukkit.createWorld(new WorldCreator(itemName));
                    World setHub = Bukkit.getWorld(itemName);
                    p.teleport(new Location(setHub, 0.5, 71, 0.5, 180, 0));
                    utils.currentWorld.remove(p.getUniqueId());
                    utils.currentWorld.put(p.getUniqueId(), itemName);
                    p.sendMessage(color("&aSent you to hub &b" + itemName + "&a!"));
                }
            }
            if (SlotClicked.get(p.getUniqueId()) >= 37 && SlotClicked.get(p.getUniqueId()) <= 43) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
                if (itemType.equalsIgnoreCase("Red Concrete")) {
                    p.sendMessage(color("&cYou are already in this hub!"));
                    return;
                }
                if (hub.hubs.contains(itemName) && Bukkit.getWorld(itemName) != null) {
                    Bukkit.createWorld(new WorldCreator(itemName));
                    World setHub = Bukkit.getWorld(itemName);
                    p.teleport(new Location(setHub, 0.5, 71, 0.5, 180, 0));
                    utils.currentWorld.remove(p.getUniqueId());
                    utils.currentWorld.put(p.getUniqueId(), itemName);
                    p.sendMessage(color("&aSent you to hub &b" + itemName + "&a!"));
                }
            }
            if (SlotClicked.get(p.getUniqueId()) == 49) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
            }
            if (SlotClicked.get(p.getUniqueId()) == 50) {
                SlotClicked.remove(p.getUniqueId());
                p.closeInventory();
                Random r = new Random();
                String randomHub = hub.hubList[r.nextInt(hub.hubList.length)];
                if (!randomHub.equals(utils.currentWorld.get(p.getUniqueId()))) {
                    Bukkit.createWorld(new WorldCreator(randomHub));
                    World setHub = Bukkit.getWorld(randomHub);
                    p.teleport(new Location(setHub, 0.5, 71, 0.5, 180, 0));
                    utils.currentWorld.remove(p.getUniqueId());
                    utils.currentWorld.put(p.getUniqueId(), randomHub);
                    p.sendMessage(color("&aSent you to hub &b" + randomHub + "&a!"));
                }
                else {
                    p.sendMessage(color("&cError: You are trying to go to the same hub."));
                }
            }
        } catch (NullPointerException ignored) {}
    }
}