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
                p.sendMessage(color("List all NPC's: /npc list"));
                p.sendMessage(color("Create an NPC that is on /npc list:"));
                p.sendMessage(color("/npc create [name]"));
            }
        }
        else {
            System.out.println("This command can only be ran ingame!");
        }
        return false;
    }
}
