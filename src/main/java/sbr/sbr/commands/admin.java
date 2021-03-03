package sbr.sbr.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sbr.sbr.guis.banker.bankerMain;
import sbr.sbr.guis.hubMenu;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;
import sbr.sbr.utils.utils;

import java.sql.SQLException;

public class admin extends chatcolors implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("You can only run this command as a player.");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("sbr.admin")) {
            p.sendMessage(color("&4You do not have permission to execute this command."));
            return true;
        }
        if (args.length == 0) {
            p.sendMessage(color("&aCorrect command usage:"));
            p.sendMessage(color("&a/admin hub [hub]"));
            p.sendMessage(color("&a/admin hubselector"));
            p.sendMessage(color("&a/admin purse [add/remove/current] [player] [amount]"));
            p.sendMessage(color("&a/admin bank [add/remove/current] [player] [amount]"));
            p.sendMessage(color("&a/admin bankgui"));
            p.sendMessage(color("&a/admin send [player] [location]"));
            p.sendMessage(color("&a/admin find [player]"));
            p.sendMessage(color("&a/admin mana [add/remove/current] [player] [amount]"));
            p.sendMessage(color("&a/admin maxmana [add/remove/current] [player] [amount]"));
            p.sendMessage(color("&a/admin health [add/remove/current] [player] [amount]"));
            p.sendMessage(color("&a/admin maxhealth [add/remove/current] [player] [amount]"));
        }
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("hubselector")) {
                p.openInventory(hubMenu.getGui(p));
                utils.currentGui.put(p.getUniqueId(), "hubMenu");
            }
            if (args[0].equalsIgnoreCase("hub")) {
                if (args.length == 2) {
                    if (utils.currentWorld.get(p.getUniqueId()).equalsIgnoreCase(args[1])) {
                        p.sendMessage(color("&cYou are trying to teleport to the same hub!"));
                    }
                    else if (hub.hubs.contains(args[1]) && Bukkit.getWorld(args[1]) != null) {
                        Bukkit.createWorld(new WorldCreator(args[1]));
                        World setHub = Bukkit.getWorld(args[1]);
                        p.teleport(new Location(setHub, 0.5, 71, 0.5, 180, 0));
                        utils.currentWorld.remove(p.getUniqueId());
                        utils.currentWorld.put(p.getUniqueId(), args[1]);
                        p.sendMessage(color("&aSent you to hub &b" + args[1] + "&a!"));
                    } else {
                        p.sendMessage(color("&4That is not a correct hub name!"));
                    }
                } else {
                    p.sendMessage(color("&aCorrect command usage:"));
                    p.sendMessage(color("&a/admin hub [hub]"));
                }
            }
            if (args[0].equalsIgnoreCase("purse")) {
                if (args.length == 3) {
                    if (args[1].equalsIgnoreCase("current")) {
                        try {
                            Player pg = Bukkit.getPlayer(args[2]);
                            assert pg != null;
                            utils.balance(pg);
                            int bal = utils.purseBal;
                            p.sendMessage(color("&aPlayer &b" + pg.getDisplayName() + " &ahas &6" + bal + " &ain their purse."));
                        } catch (NullPointerException x) {
                            p.sendMessage(color("&cThat is not a valid player."));
                        }
                    }
                } else if (args.length == 4) {
                    if (args[1].equalsIgnoreCase("add")) {
                        try {
                            try {
                                Player pg = Bukkit.getPlayer(args[2]);
                                assert pg != null;
                                utils.balance(pg);
                                int bal = utils.purseBal;
                                try {
                                    int argint = Integer.parseInt(args[3]);
                                    if (argint < 1) {
                                        p.sendMessage(color("&cPlease provide a number higher than 0"));
                                        return true;
                                    }
                                    int nbal = bal + argint;
                                    main.prepareStatement("UPDATE purse SET Balance = '" + nbal + "' WHERE UUID = '" + pg.getUniqueId().toString() + "';").executeUpdate();
                                    p.sendMessage(color("&dYou added &6" + argint + " &dto &b" + pg.getDisplayName() + "&d's purse."));
                                    p.sendMessage(color("&aThey now have: &6" + nbal));
                                    pg.sendMessage(color("&dAn admin added &6" + argint + " &dto your purse."));
                                    pg.sendMessage(color("&aYou now have: &6" + nbal));
                                } catch (NumberFormatException x) {
                                    p.sendMessage(color("&c&lAmount must be a number!"));
                                }
                            } catch (NullPointerException x) {
                                p.sendMessage(color("&cThat is not a valid player!"));
                            }
                        } catch (SQLException x) {
                            x.printStackTrace();
                            p.sendMessage(color("&4&lSomething went wrong, please contact a dev ASAP."));
                        }
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        try {
                            try {
                                Player pg = Bukkit.getPlayer(args[2]);
                                assert pg != null;
                                utils.balance(pg);
                                int bal = utils.purseBal;
                                try {
                                    int argint = Integer.parseInt(args[3]);
                                    if (argint < 1) {
                                        p.sendMessage(color("&cPlease provide a number higher than 0"));
                                        return true;
                                    }
                                    int nbal = bal - argint;
                                    if (nbal < 0) {
                                        p.sendMessage(color("&cError: Balance cannot be lower than 0"));
                                        return true;
                                    }
                                    main.prepareStatement("UPDATE purse SET Balance = '" + nbal + "' WHERE UUID = '" + pg.getUniqueId().toString() + "';").executeUpdate();
                                    p.sendMessage(color("&dYou removed &6" + argint + " &dfrom &b" + pg.getDisplayName() + "&d's purse."));
                                    p.sendMessage(color("&aThey now have: &6" + nbal));
                                    pg.sendMessage(color("&dAn admin removed &6" + argint + " &dfrom your purse."));
                                    pg.sendMessage(color("&aYou now have: &6" + nbal));
                                } catch (NumberFormatException x) {
                                    p.sendMessage(color("&c&lAmount must be a number!"));
                                }
                            } catch (NullPointerException x) {
                                p.sendMessage(color("&cThat is not a valid player!"));
                            }
                        } catch (SQLException x) {
                            x.printStackTrace();
                            p.sendMessage(color("&4&lSomething went wrong, please contact a dev ASAP."));
                        }
                    }
                } else {
                    p.sendMessage(color("&aCorrect command usage:"));
                    p.sendMessage(color("&a/admin purse [add/remove/current] [player] [amount]"));
                }
            }
            if (args[0].equalsIgnoreCase("bank")) {
                if (args.length == 3) {
                    if (args[1].equalsIgnoreCase("current")) {
                        try {
                            Player pg = Bukkit.getPlayer(args[2]);
                            assert pg != null;
                            utils.balance(pg);
                            int bal = utils.bankBal;
                            p.sendMessage(color("&aPlayer &b" + pg.getDisplayName() + " &ahas &6" + bal + " &ain their bank."));
                        } catch (NullPointerException x) {
                            p.sendMessage(color("&cThat is not a valid player."));
                        }
                    }
                } else if (args.length == 4) {
                    if (args[1].equalsIgnoreCase("add")) {
                        try {
                            try {
                                Player pg = Bukkit.getPlayer(args[2]);
                                assert pg != null;
                                utils.balance(pg);
                                int bal = utils.bankBal;
                                try {
                                    int argint = Integer.parseInt(args[3]);
                                    if (argint < 1) {
                                        p.sendMessage(color("&cPlease provide a number higher than 0"));
                                        return true;
                                    }
                                    int nbal = bal + argint;
                                    main.prepareStatement("UPDATE bank SET Balance = '" + nbal + "' WHERE UUID = '" + pg.getUniqueId().toString() + "';").executeUpdate();
                                    p.sendMessage(color("&dYou added &6" + argint + " &dto &b" + pg.getDisplayName() + "&d's bank."));
                                    p.sendMessage(color("&aThey now have: &6" + nbal));
                                    pg.sendMessage(color("&dAn admin added &6" + argint + " &dto your bank."));
                                    pg.sendMessage(color("&aYou now have: &6" + nbal));
                                } catch (NumberFormatException x) {
                                    p.sendMessage(color("&c&lAmount must be a number!"));
                                }
                            } catch (NullPointerException x) {
                                p.sendMessage(color("&cThat is not a valid player!"));
                            }
                        } catch (SQLException x) {
                            x.printStackTrace();
                            p.sendMessage(color("&4&lSomething went wrong, please contact a dev ASAP."));
                        }
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        try {
                            try {
                                Player pg = Bukkit.getPlayer(args[2]);
                                assert pg != null;
                                utils.balance(pg);
                                int bal = utils.bankBal;
                                try {
                                    int argint = Integer.parseInt(args[3]);
                                    if (argint < 1) {
                                        p.sendMessage(color("&cPlease provide a number higher than 0"));
                                        return true;
                                    }
                                    int nbal = bal - argint;
                                    if (nbal < 0) {
                                        p.sendMessage(color("&cError: Balance cannot be lower than 0"));
                                        return true;
                                    }
                                    main.prepareStatement("UPDATE bank SET Balance = '" + nbal + "' WHERE UUID = '" + pg.getUniqueId().toString() + "';").executeUpdate();
                                    p.sendMessage(color("&dYou removed &6" + argint + " &dfrom &b" + pg.getDisplayName() + "&d's bank."));
                                    p.sendMessage(color("&aThey now have: &6" + nbal));
                                    pg.sendMessage(color("&dAn admin removed &6" + argint + " &dfrom your bank."));
                                    pg.sendMessage(color("&aYou now have: &6" + nbal));
                                } catch (NumberFormatException x) {
                                    p.sendMessage(color("&c&lAmount must be a number!"));
                                }
                            } catch (NullPointerException x) {
                                p.sendMessage(color("&cThat is not a valid player!"));
                            }
                        } catch (SQLException x) {
                            x.printStackTrace();
                            p.sendMessage(color("&4&lSomething went wrong, please contact a dev ASAP."));
                        }
                    }
                } else {
                    p.sendMessage(color("&aCorrect command usage:"));
                    p.sendMessage(color("&a/admin bank [add/remove/current] [player] [amount]"));
                }
            }
            if (args[0].equalsIgnoreCase("bankgui")) {
                p.openInventory(bankerMain.getGui(p));
                utils.currentGui.put(p.getUniqueId(), "bankerMain");
            }
            if (args[0].equalsIgnoreCase("send")) {
                if (args.length > 1) {
                    try {
                        Player pg = Bukkit.getPlayer(args[1]);
                        assert pg != null;
                        if (args.length > 2) {
                            if (hub.hubs.contains(args[2]) && Bukkit.getWorld(args[2]) != null) {
                                Bukkit.createWorld(new WorldCreator(args[2]));
                                World setHub = Bukkit.getWorld(args[2]);
                                pg.teleport(new Location(setHub, 0.5, 71, 0.5, 180, 0));
                                utils.currentWorld.remove(pg.getUniqueId());
                                utils.currentWorld.put(pg.getUniqueId(), args[2]);
                                p.sendMessage(color("&aSent &d" + args[1] + " &ato &b" + args[2] + "&a!"));
                                pg.sendMessage(color("&aAn admin has sent you to &b" + args[2]));
                            } else {
                                p.sendMessage(color("&4That is not a correct hub name!"));
                            }
                        } else {
                            p.sendMessage(color("&aCorrect command usage:"));
                            p.sendMessage(color("&a/admin send [player] [location]"));
                        }
                    } catch (NullPointerException x) {
                        p.sendMessage(color("&cThat is not a correct player!"));
                    }
                } else {
                    p.sendMessage(color("&aCorrect command usage:"));
                    p.sendMessage(color("&a/admin send [player] [location]"));
                }
            }
            if (args[0].equalsIgnoreCase("find")) {
                if (args.length == 2) {
                    try {
                        Player pg = Bukkit.getPlayer(args[1]);
                        assert pg != null;
                        String where = utils.currentWorld.get(pg.getUniqueId());
                        p.sendMessage(color("&b" + pg.getDisplayName() + " &ais in &d" + where));
                    } catch (NullPointerException x) {
                        p.sendMessage(color("&cThat is not a correct player!"));
                    }
                }
                else {
                    p.sendMessage(color("&aCorrect command usage:"));
                    p.sendMessage(color("&a/admin find [player]"));
                }
            }
        }
        return true;
    }
}