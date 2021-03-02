package sbr.sbr.guis.banker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sbr.sbr.commands.banker;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class bankerWithdraw extends chatcolors {
    @SuppressWarnings("deprecation")
    public static Inventory getGui() {
        int totalBal = 0;
        try {
            ResultSet rs = main.prepareStatement("SELECT * FROM bank WHERE UUID = '" + banker.p.getUniqueId().toString() + "';").executeQuery();
            rs.next();
            totalBal = rs.getInt("Balance");
        } catch (SQLException x) {
            x.printStackTrace();
        }
        Inventory gui = Bukkit.createInventory(null, 36, ChatColor.AQUA + "Withdraw");

        // Items
        ItemStack withdrawHalf;
        ItemMeta withdrawHalfMeta;
        List<String> withdrawHalfLore = new ArrayList<>();

        ItemStack withdrawAll;
        ItemMeta withdrawAllMeta;
        List<String> withdrawAllLore = new ArrayList<>();

        ItemStack withdrawCustom;
        ItemMeta withdrawCustomMeta;
        List<String> withdrawCustomLore = new ArrayList<>();

        ItemStack close;
        ItemMeta closeMeta;
        List<String> closeLore = new ArrayList<>();

        ItemStack glass;
        ItemMeta glassMeta;

        // Withdraw half
        withdrawHalf = new ItemStack(Material.DISPENSER);
        withdrawHalfMeta = withdrawHalf.getItemMeta();
        int half = totalBal / 2;
        withdrawHalfMeta.setDisplayName(color("&bWithdraw &6" + half + " &bcoins."));
        withdrawHalfLore.add(color("&7Total bank balance: &6" + totalBal));
        withdrawHalfMeta.setLore(withdrawHalfLore);
        withdrawHalf.setItemMeta(withdrawHalfMeta);

        // Withdraw all
        withdrawAll = new ItemStack(Material.DROPPER);
        withdrawAllMeta = withdrawAll.getItemMeta();
        withdrawAllMeta.setDisplayName(color("&bWithdraw all coins (&6" + totalBal + "&b)"));
        withdrawAllLore.add(color("&7Total bank balance: &6" + totalBal));
        withdrawAllMeta.setLore(withdrawAllLore);
        withdrawAll.setItemMeta(withdrawAllMeta);

        // Withdraw custom
        withdrawCustom = new ItemStack(Material.OAK_SIGN);
        withdrawCustomMeta = withdrawCustom.getItemMeta();
        withdrawCustomMeta.setDisplayName(color("&bWithdraw a custom amount of coins."));
        withdrawCustomLore.add(color("&7Total bank balance: &6" + totalBal));
        withdrawCustomMeta.setLore(withdrawCustomLore);
        withdrawCustom.setItemMeta(withdrawCustomMeta);

        // Close button
        close = new ItemStack(Material.BARRIER);
        closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(color("&cClose"));
        closeLore.add(color("&7Closes this GUI."));
        closeMeta.setLore(closeLore);
        close.setItemMeta(closeMeta);

        // Glass panes
        glass = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);

        // Set items
        gui.setItem(0, glass);
        gui.setItem(1, glass);
        gui.setItem(2, glass);
        gui.setItem(3, glass);
        gui.setItem(4, glass);
        gui.setItem(5, glass);
        gui.setItem(6, glass);
        gui.setItem(7, glass);
        gui.setItem(8, glass);
        gui.setItem(9, glass);
        gui.setItem(10, glass);
        gui.setItem(11, withdrawHalf);
        gui.setItem(12, glass);
        gui.setItem(13, withdrawAll);
        gui.setItem(14, glass);
        gui.setItem(15, withdrawCustom);
        gui.setItem(16, glass);
        gui.setItem(17, glass);
        gui.setItem(18, glass);
        gui.setItem(19, glass);
        gui.setItem(20, glass);
        gui.setItem(21, glass);
        gui.setItem(22, glass);
        gui.setItem(23, glass);
        gui.setItem(24, glass);
        gui.setItem(25, glass);
        gui.setItem(26, glass);
        gui.setItem(27, glass);
        gui.setItem(28, glass);
        gui.setItem(29, glass);
        gui.setItem(30, glass);
        gui.setItem(31, close);
        gui.setItem(32, glass);
        gui.setItem(33, glass);
        gui.setItem(34, glass);
        gui.setItem(35, glass);

        return gui;
    }
}
