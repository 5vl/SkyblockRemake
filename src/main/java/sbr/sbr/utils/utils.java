package sbr.sbr.utils;

import org.bukkit.entity.Player;
import sbr.sbr.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class utils {
    public static final HashMap<UUID, String> currentGui = new HashMap<>();
    public static final HashMap<UUID, String> currentWorld = new HashMap<>();
    public static final HashMap<UUID, Integer> currentHealth = new HashMap<>();
    public static final HashMap<UUID, Integer> maxHealth = new HashMap<>();
    public static final HashMap<UUID, Integer> currentMana = new HashMap<>();
    public static final HashMap<UUID, Integer> maxMana = new HashMap<>();
    public static int bankBal;
    public static int purseBal;
    public static void balance(Player player) {
        try {
            ResultSet bank = main.prepareStatement("SELECT * FROM bank WHERE UUID = '" + player.getUniqueId() + "';").executeQuery();
            ResultSet purse = main.prepareStatement("SELECT * FROM purse WHERE UUID = '" + player.getUniqueId() + "';").executeQuery();
            bank.next();
            purse.next();
            bankBal = bank.getInt("Balance");
            purseBal = purse.getInt("Balance");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
