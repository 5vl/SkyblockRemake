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

public class bankerMain extends chatcolors {
    @SuppressWarnings("deprecation")
    public static Inventory getGui(Player p) {
        utils.balance(p);
        int totalBal = utils.bankBal;
        Inventory gui = Bukkit.createInventory(null, 36, ChatColor.GREEN + "Banker");

        // Items
        ItemStack withdraw;
        ItemMeta withdrawMeta;
        List<String> withdrawLore = new ArrayList<>();

        ItemStack deposit;
        ItemMeta depositMeta;
        List<String> depositLore = new ArrayList<>();

        ItemStack close;
        ItemMeta closeMeta;
        List<String> closeLore = new ArrayList<>();

        ItemStack glass;
        ItemMeta glassMeta;

        // Withdraw button
        withdraw = new ItemStack(Material.DISPENSER);
        withdrawMeta = withdraw.getItemMeta();
        withdrawMeta.setDisplayName(color("&cWithdraw"));
        withdrawLore.add(color("&7Total money: &6" + totalBal));
        withdrawMeta.setLore(withdrawLore);
        withdraw.setItemMeta(withdrawMeta);

        // Deposit button
        deposit = new ItemStack(Material.CHEST);
        depositMeta = deposit.getItemMeta();
        depositMeta.setDisplayName(color("&aDeposit"));
        depositLore.add(color("&7Total money: &6" + totalBal));
        depositMeta.setLore(depositLore);
        deposit.setItemMeta(depositMeta);

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
        gui.setItem(11, deposit);
        gui.setItem(12, glass);
        gui.setItem(13, withdraw);
        gui.setItem(14, glass);
        gui.setItem(15, glass);
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
