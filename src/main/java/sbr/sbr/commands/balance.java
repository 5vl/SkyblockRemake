package sbr.sbr.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;
import java.sql.ResultSet;
import java.sql.SQLException;

public class balance extends chatcolors implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        try {
            ResultSet rs = main.prepareStatement("SELECT * FROM purse WHERE UUID = '" + p.getUniqueId().toString() + "';").executeQuery();
            rs.next();
            int bal = rs.getInt("Balance");
            int argint;
            if (args.length == 0) {
                p.sendMessage(color("&bYour balance is: &a" + bal));
            }
            if (args.length > 0) {
                try {
                    argint = Integer.parseInt(args[0]);
                    int nbal = bal + argint;
                    main.prepareStatement("UPDATE purse SET Balance = '" + nbal + "' WHERE UUID = '" + p.getUniqueId().toString() + "';").executeUpdate();
                    p.sendMessage(color("&dYou added &b" + argint + " &dto your balance."));
                    p.sendMessage(color("&aYou now have: &b" + nbal));
                } catch (NumberFormatException x) {
                    p.sendMessage(color("&c&lArgument must be an number or nothing!"));
                }
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return false;
    }
}
