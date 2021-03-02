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
import sbr.sbr.events.InvClick;
import sbr.sbr.guis.hubMenu;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;

import java.util.*;

public class hub extends chatcolors implements CommandExecutor {
    public static final String[] hubList = {"Hub1A", "Hub1B", "Hub1C", "Hub1D", "Hub1E", "Hub1F", "Hub2A", "Hub2B", "Hub2C", "Hub2D", "Hub2E", "Hub2F", "Hub3A", "Hub3B", "Hub3C", "Hub3D", "Hub3E", "Hub3F", "Hub4A", "Hub4B", "Hub4C", "Hub4D", "Hub4E", "Hub4F", "Hub5A", "Hub5B", "Hub5C", "Hub5D"};
    public static final String hubs = "Hub1A Hub1B Hub1C Hub1D Hub1E Hub1F Hub2A Hub2B Hub2C Hub2D Hub2E Hub2F Hub3A Hub3B Hub3C Hub3D Hub3E Hub3F Hub4A Hub4B Hub4C Hub4D Hub4E Hub4F Hub5A Hub5B Hub5C Hub5D";
    public static Player p;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("You can only run this command as a player!");
            return true;
        }
        p = (Player) sender;
        if (args.length == 0) {
            Random r = new Random();
            String randomHub = hubList[r.nextInt(hubList.length)];
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
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("rn")) {
                p.sendMessage(color(("&aYou are currently in hub &b" + main.currentWorld.get(p.getUniqueId()) + "&a!")));
            }
            else if (args[0].equalsIgnoreCase("list")) {
                p.sendMessage(hubs);
            }
            else if (args[0].equalsIgnoreCase("lol")) {
                p.openInventory(hubMenu.getGui());
                InvClick.currentGui.put(p.getUniqueId(), "hubMenu");
            }
            else if (hubs.contains(args[0]) && Bukkit.getWorld(args[0]) != null) {
                Bukkit.createWorld(new WorldCreator(args[0]));
                World setHub = Bukkit.getWorld(args[0]);
                p.teleport(new Location(setHub, 0.5, 71, 0.5, 180, 0));
                main.currentWorld.remove(p.getUniqueId());
                main.currentWorld.put(p.getUniqueId(), args[0]);
                p.sendMessage(color("&aSent you to hub &b" + args[0] + "&a!"));
            }
            else {
                p.sendMessage(color("&4That is not a correct hub name!"));
            }
        }
        else {
            p.sendMessage(color("&cCorrect command usage: &6/hub&c, &6/hub [hubname]"));
        }
        return true;
    }
}
