package sbr.sbr;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sbr.sbr.commands.balance;
import sbr.sbr.commands.banker;
import sbr.sbr.commands.hub;
import sbr.sbr.commands.npc;
import sbr.sbr.events.InvClick;
import sbr.sbr.events.onPlayerJoin;

import java.sql.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class main extends JavaPlugin {
    public static main instance;
    private static Connection connection;
    private static String host, database, username, password;
    private static int port;
    public static final HashMap<UUID, String> currentWorld = new HashMap<>();
    public static int bankBal;
    public static int purseBal;

    @Override
    public void onEnable() {
        instance = this;
        PluginManager plm = Bukkit.getPluginManager();
        host = "localhost";
        port = 3306;
        database = "sbr";
        username = "admin";
        password = "fivevl";
        try {
            openConnection();
            System.out.println("MySQL Database Connected.");
        } catch (SQLException x) {
            x.printStackTrace();
        }
        plm.registerEvents(new onPlayerJoin(), this);
        plm.registerEvents(new InvClick(), this);
        Objects.requireNonNull(getCommand("balance")).setExecutor(new balance());
        Objects.requireNonNull(getCommand("npc")).setExecutor(new npc());
        Objects.requireNonNull(getCommand("banker")).setExecutor(new banker());
        Objects.requireNonNull(getCommand("hub")).setExecutor(new hub());
        loadWorlds();
    }
    @Override
    public void onDisable() {
        try {
            connection.close();
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }
    public static void openConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false", username, password);
    }
    public static PreparedStatement prepareStatement(String query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return ps;
    }
    public void loadWorlds() {
        for (String world:hub.hubList) {
            Bukkit.createWorld(new WorldCreator(world));
            System.out.println("Loaded world " + world);
        }
    }
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