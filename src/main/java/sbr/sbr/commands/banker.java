package sbr.sbr.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sbr.sbr.guis.bankergui;
import sbr.sbr.utils.chatcolors;

public class banker extends chatcolors implements CommandExecutor {
    public static Player p;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            p = (Player) sender;
            p.openInventory(bankergui.getGui());
        }
        else {
            System.out.println("This command can only be ran by a player.");
        }
        return false;
    }
}
