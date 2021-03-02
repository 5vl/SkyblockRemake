package sbr.sbr.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sbr.sbr.main;
import sbr.sbr.utils.chatcolors;
import java.util.ArrayList;
import java.util.List;

public class hubMenu extends chatcolors {
    @SuppressWarnings("deprecation")
    public static Inventory getGui() {
        Inventory gui = Bukkit.createInventory(null, 54, ChatColor.AQUA + "Hub Menu");

        // Items
        ItemStack hub;
        ItemMeta hubMeta;
        List<String> hubLore = new ArrayList<>();

        ItemStack close;
        ItemMeta closeMeta;
        List<String> closeLore = new ArrayList<>();

        ItemStack randomHub;
        ItemMeta randomHubMeta;
        List<String> randomHubLore = new ArrayList<>();

        ItemStack glass;
        ItemMeta glassMeta;

        // Hub
        int test = 1;
        int hubNumber = 1;
        char hubLetter = 'A';
        for (int i = 10; i < 17; i++) {
            if (hubLetter == 'G') {
                hubLetter = 'A';
                hubNumber++;
            }
            String hubName = "Hub" + hubNumber + hubLetter;
            if (hubName.equalsIgnoreCase(main.currentWorld.get(sbr.sbr.commands.hub.p.getUniqueId()))) {
                hub = new ItemStack(Material.RED_CONCRETE, test);
                hubMeta = hub.getItemMeta();
                hubMeta.setDisplayName(color("&a" + hubName));
                hubLore.add(color("&cu r in dis hub boi"));
            }
            else {
                hub = new ItemStack(Material.WHITE_CONCRETE, test);
                hubMeta = hub.getItemMeta();
                hubMeta.setDisplayName(color("&a" + hubName));
                hubLore.add(color("&bhaha"));
            }
            hubMeta.setLore(hubLore);
            hub.setItemMeta(hubMeta);
            gui.setItem(i, hub);
            hubLore.clear();
            test++;
            hubLetter++;
        }
        for (int i = 19; i < 26; i++) {
            if (hubLetter == 'G') {
                hubLetter = 'A';
                hubNumber++;
            }
            String hubName = "Hub" + hubNumber + hubLetter;
            if (hubName.equalsIgnoreCase(main.currentWorld.get(sbr.sbr.commands.hub.p.getUniqueId()))) {
                hub = new ItemStack(Material.RED_CONCRETE, test);
                hubMeta = hub.getItemMeta();
                hubMeta.setDisplayName(color("&a" + hubName));
                hubLore.add(color("&cu r in dis hub boi"));
            }
            else {
                hub = new ItemStack(Material.WHITE_CONCRETE, test);
                hubMeta = hub.getItemMeta();
                hubMeta.setDisplayName(color("&a" + hubName));
                hubLore.add(color("&bhaha"));
            }
            hubMeta.setLore(hubLore);
            hub.setItemMeta(hubMeta);
            gui.setItem(i, hub);
            hubLore.clear();
            test++;
            hubLetter++;
        }
        for (int i = 28; i < 35; i++) {
            if (hubLetter == 'G') {
                hubLetter = 'A';
                hubNumber++;
            }
            String hubName = "Hub" + hubNumber + hubLetter;
            if (hubName.equalsIgnoreCase(main.currentWorld.get(sbr.sbr.commands.hub.p.getUniqueId()))) {
                hub = new ItemStack(Material.RED_CONCRETE, test);
                hubMeta = hub.getItemMeta();
                hubMeta.setDisplayName(color("&a" + hubName));
                hubLore.add(color("&cu r in dis hub boi"));
            }
            else {
                hub = new ItemStack(Material.WHITE_CONCRETE, test);
                hubMeta = hub.getItemMeta();
                hubMeta.setDisplayName(color("&a" + hubName));
                hubLore.add(color("&bhaha"));
            }
            hubMeta.setLore(hubLore);
            hub.setItemMeta(hubMeta);
            gui.setItem(i, hub);
            hubLore.clear();
            test++;
            hubLetter++;
        }
        for (int i = 37; i < 44; i++) {
            if (hubLetter == 'G') {
                hubLetter = 'A';
                hubNumber++;
            }
            String hubName = "Hub" + hubNumber + hubLetter;
            if (hubName.equalsIgnoreCase(main.currentWorld.get(sbr.sbr.commands.hub.p.getUniqueId()))) {
                hub = new ItemStack(Material.RED_CONCRETE, test);
                hubMeta = hub.getItemMeta();
                hubMeta.setDisplayName(color("&a" + hubName));
                hubLore.add(color("&cu r in dis hub boi"));
            }
            else {
                hub = new ItemStack(Material.WHITE_CONCRETE, test);
                hubMeta = hub.getItemMeta();
                hubMeta.setDisplayName(color("&a" + hubName));
                hubLore.add(color("&bhaha"));
            }
            hubMeta.setLore(hubLore);
            hub.setItemMeta(hubMeta);
            gui.setItem(i, hub);
            hubLore.clear();
            test++;
            hubLetter++;
        }

        // Close
        close = new ItemStack(Material.BARRIER);
        closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(color("&cClose"));
        closeLore.add(color("&7Closes this GUI."));
        closeMeta.setLore(closeLore);
        close.setItemMeta(closeMeta);

        // Random hub
        randomHub = new ItemStack(Material.COMPASS);
        randomHubMeta = randomHub.getItemMeta();
        randomHubMeta.setDisplayName(color("&bGo to a random hub."));
        randomHubLore.add(color("&7This will bring you"));
        randomHubLore.add(color("&7to a random hub!"));
        randomHubMeta.setLore(randomHubLore);
        randomHub.setItemMeta(randomHubMeta);

        // Glass
        glass = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(color(" "));
        glass.setItemMeta(glassMeta);

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
        gui.setItem(17, glass);
        gui.setItem(18, glass);
        gui.setItem(26, glass);
        gui.setItem(27, glass);
        gui.setItem(35, glass);
        gui.setItem(36, glass);
        gui.setItem(44, glass);
        gui.setItem(45, glass);
        gui.setItem(46, glass);
        gui.setItem(47, glass);
        gui.setItem(48, glass);
        gui.setItem(49, close);
        gui.setItem(50, randomHub);
        gui.setItem(51, glass);
        gui.setItem(52, glass);
        gui.setItem(53, glass);

        return gui;
    }
}
