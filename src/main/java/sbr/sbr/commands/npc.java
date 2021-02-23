package sbr.sbr.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sbr.sbr.utils.chatcolors;

public class npc extends chatcolors implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(color("Incorrect command usage."));
                p.sendMessage(color("List all NPC's: /npc list"));
                p.sendMessage(color("Create an NPC that is on /npc list:"));
                p.sendMessage(color("/npc create [npc]"));
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    p.sendMessage(color("List of NPC's:"));
                    p.sendMessage(color("Banker"));
                }
                else if (args[0].equalsIgnoreCase("create")) {
                    p.sendMessage(color("Please select an NPC from the list"));
                    p.sendMessage(color("with /npc list."));
                    p.sendMessage(color("After that do /npc create [npc]"));
                }
                else {
                    p.sendMessage(color("Incorrect command usage."));
                    p.sendMessage(color("List all NPC's: /npc list"));
                    p.sendMessage(color("Create an NPC that is on /npc list:"));
                    p.sendMessage(color("/npc create [npc]"));
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (args[1].equalsIgnoreCase("banker")) {
                        p.sendMessage(color("Spawned the Banker NPC!"));
                    }
                    else {
                        p.sendMessage(color("Incorrect command usage."));
                        p.sendMessage(color("List all NPC's: /npc list"));
                        p.sendMessage(color("Create an NPC that is on /npc list:"));
                        p.sendMessage(color("/npc create [npc]"));
                    }
                }
                else {
                    p.sendMessage(color("Incorrect command usage."));
                    p.sendMessage(color("List all NPC's: /npc list"));
                    p.sendMessage(color("Create an NPC that is on /npc list:"));
                    p.sendMessage(color("/npc create [npc]"));
                }
            }
        }
        else {
            System.out.println("This command can only be ran ingame!");
        }
        return false;
    }
}
