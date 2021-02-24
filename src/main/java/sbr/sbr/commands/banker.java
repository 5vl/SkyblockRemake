package sbr.sbr.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sbr.sbr.events.InvClick;
import sbr.sbr.guis.banker.bankerMain;
import sbr.sbr.utils.chatcolors;

public class banker extends chatcolors implements CommandExecutor {
    public static Player p;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            p = (Player) sender;
            p.openInventory(bankerMain.getGui());
            InvClick.currentGui.put(p.getUniqueId(), "bankerMain");
        }
        else {
            System.out.println("This command can only be ran by a player.");
        }
        return false;
    }
}
