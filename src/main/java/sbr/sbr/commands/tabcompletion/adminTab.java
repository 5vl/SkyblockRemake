package sbr.sbr.commands.tabcompletion;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class adminTab implements TabCompleter {
    @SuppressWarnings("deprecation")
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("hub");
            arguments.add("hubselector");
            arguments.add("purse");
            arguments.add("bank");
            arguments.add("bankgui");
            arguments.add("send");
            arguments.add("find");
            arguments.add("mana");
            arguments.add("maxmana");
            arguments.add("health");
            arguments.add("maxhealth");
            return arguments;
        }
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("hub")) {
                List<String> arguments = new ArrayList<>();
                arguments.add("Hub1A");
                arguments.add("Hub1B");
                arguments.add("Hub1C");
                arguments.add("Hub1D");
                arguments.add("Hub1E");
                arguments.add("Hub1F");
                arguments.add("Hub2A");
                arguments.add("Hub2B");
                arguments.add("Hub2C");
                arguments.add("Hub2D");
                arguments.add("Hub2E");
                arguments.add("Hub2F");
                arguments.add("Hub3A");
                arguments.add("Hub3B");
                arguments.add("Hub3C");
                arguments.add("Hub3D");
                arguments.add("Hub3E");
                arguments.add("Hub3F");
                arguments.add("Hub4A");
                arguments.add("Hub4B");
                arguments.add("Hub4C");
                arguments.add("Hub4D");
                arguments.add("Hub4E");
                arguments.add("Hub4F");
                arguments.add("Hub5A");
                arguments.add("Hub5B");
                arguments.add("Hub5C");
                arguments.add("Hub5D");
                return arguments;
            }
            if (args[0].equalsIgnoreCase("purse")) {
                if (args.length == 2) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("add");
                    arguments.add("remove");
                    arguments.add("current");
                    return arguments;
                }
                if (args.length == 3) {
                    List<String> arguments = new ArrayList<>();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        arguments.add(player.getDisplayName());
                    }
                    return arguments;
                }
            }
            if (args[0].equalsIgnoreCase("bank")) {
                if (args.length == 2) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("add");
                    arguments.add("remove");
                    arguments.add("current");
                    return arguments;
                }
                if (args.length == 3) {
                    List<String> arguments = new ArrayList<>();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        arguments.add(player.getDisplayName());
                    }
                    return arguments;
                }
            }
            if (args[0].equalsIgnoreCase("send")) {
                if (args.length == 2) {
                    List<String> arguments = new ArrayList<>();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        arguments.add(player.getDisplayName());
                    }
                    return arguments;
                }
                if (args.length == 3) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("Hub1A");
                    arguments.add("Hub1B");
                    arguments.add("Hub1C");
                    arguments.add("Hub1D");
                    arguments.add("Hub1E");
                    arguments.add("Hub1F");
                    arguments.add("Hub2A");
                    arguments.add("Hub2B");
                    arguments.add("Hub2C");
                    arguments.add("Hub2D");
                    arguments.add("Hub2E");
                    arguments.add("Hub2F");
                    arguments.add("Hub3A");
                    arguments.add("Hub3B");
                    arguments.add("Hub3C");
                    arguments.add("Hub3D");
                    arguments.add("Hub3E");
                    arguments.add("Hub3F");
                    arguments.add("Hub4A");
                    arguments.add("Hub4B");
                    arguments.add("Hub4C");
                    arguments.add("Hub4D");
                    arguments.add("Hub4E");
                    arguments.add("Hub4F");
                    arguments.add("Hub5A");
                    arguments.add("Hub5B");
                    arguments.add("Hub5C");
                    arguments.add("Hub5D");
                    return arguments;
                }
            }
            if (args[0].equalsIgnoreCase("find")) {
                if (args.length == 2) {
                    List<String> arguments = new ArrayList<>();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        arguments.add(player.getDisplayName());
                    }
                    return arguments;
                }
            }
            if (args[0].equalsIgnoreCase("mana")) {
                if (args.length == 2) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("add");
                    arguments.add("remove");
                    return arguments;
                }
                if (args.length == 3) {
                    List<String> arguments = new ArrayList<>();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        arguments.add(player.toString());
                    }
                    return arguments;
                }
            }
            if (args[0].equalsIgnoreCase("maxmana")) {
                if (args.length == 3) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("add");
                    arguments.add("remove");
                    return arguments;
                }
                if (args.length == 4) {
                    List<String> arguments = new ArrayList<>();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        arguments.add(player.toString());
                    }
                    return arguments;
                }
            }
            if (args[0].equalsIgnoreCase("health")) {
                if (args.length == 3) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("add");
                    arguments.add("remove");
                    return arguments;
                }
                if (args.length == 4) {
                    List<String> arguments = new ArrayList<>();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        arguments.add(player.toString());
                    }
                    return arguments;
                }
            }
            if (args[0].equalsIgnoreCase("maxhealth")) {
                if (args.length == 3) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("add");
                    arguments.add("remove");
                    return arguments;
                }
                if (args.length == 4) {
                    List<String> arguments = new ArrayList<>();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        arguments.add(player.toString());
                    }
                    return arguments;
                }
            }
        }
        return null;
    }
}
