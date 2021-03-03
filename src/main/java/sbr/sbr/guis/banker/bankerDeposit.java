package sbr.sbr.guis.banker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sbr.sbr.utils.chatcolors;
import sbr.sbr.utils.utils;
import java.util.ArrayList;
import java.util.List;

public class bankerDeposit extends chatcolors {
    @SuppressWarnings("deprecation")
    public static Inventory getGui(Player p) {
        utils.balance(p);
        int totalBal = utils.purseBal;
        Inventory gui = Bukkit.createInventory(null, 36, ChatColor.AQUA + "Deposit");

        // Items
        ItemStack depositHalf;
        ItemMeta depositHalfMeta;
        List<String> depositHalfLore = new ArrayList<>();

        ItemStack depositAll;
        ItemMeta depositAllMeta;
        List<String> depositAllLore = new ArrayList<>();

        ItemStack depositCustom;
        ItemMeta depositCustomMeta;
        List<String> depositCustomLore = new ArrayList<>();

        ItemStack close;
        ItemMeta closeMeta;
        List<String> closeLore = new ArrayList<>();

        ItemStack glass;
        ItemMeta glassMeta;

        // Deposit half
        depositHalf = new ItemStack(Material.CHEST);
        depositHalfMeta = depositHalf.getItemMeta();
        int half = totalBal / 2;
        depositHalfMeta.setDisplayName(color("&bDeposit &6" + half + " &bcoins."));
        depositHalfLore.add(color("&7Total bank balance: &6" + totalBal));
        depositHalfMeta.setLore(depositHalfLore);
        depositHalf.setItemMeta(depositHalfMeta);

        // Deposit all
        depositAll = new ItemStack(Material.CHEST);
        depositAllMeta = depositAll.getItemMeta();
        depositAllMeta.setDisplayName(color("&bDeposit all coins (&6" + totalBal + "&b)"));
        depositAllLore.add(color("&7Total bank balance: &6" + totalBal));
        depositAllMeta.setLore(depositAllLore);
        depositAll.setItemMeta(depositAllMeta);

        // Deposit custom
        depositCustom = new ItemStack(Material.OAK_SIGN);
        depositCustomMeta = depositCustom.getItemMeta();
        depositCustomMeta.setDisplayName(color("&bDeposit a custom amount of coins."));
        depositCustomLore.add(color("&7Total bank balance: &6" + totalBal));
        depositCustomMeta.setLore(depositCustomLore);
        depositCustom.setItemMeta(depositCustomMeta);

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
        gui.setItem(11, depositHalf);
        gui.setItem(12, glass);
        gui.setItem(13, depositAll);
        gui.setItem(14, glass);
        gui.setItem(15, depositCustom);
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
