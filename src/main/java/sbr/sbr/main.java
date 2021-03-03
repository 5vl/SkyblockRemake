package sbr.sbr;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sbr.sbr.commands.*;
import sbr.sbr.commands.tabcompletion.adminTab;
import sbr.sbr.events.InvClick;
import sbr.sbr.events.healthMana;
import sbr.sbr.events.onPlayerJoin;
import java.sql.*;
import java.util.Objects;

public final class main extends JavaPlugin {
    public static main instance;
    private static Connection connection;
    private static String host, database, username, password;
    private static int port;
    private PluginManager plm;

    @Override
    public void onEnable() {
        instance = this;
        plm = Bukkit.getPluginManager();
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
        events();
        commands();
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
    public void commands() {
        Objects.requireNonNull(getCommand("npc")).setExecutor(new npc());
        Objects.requireNonNull(getCommand("hub")).setExecutor(new hub());
        Objects.requireNonNull(getCommand("admin")).setExecutor(new admin());
        Objects.requireNonNull(getCommand("admin")).setTabCompleter(new adminTab());
    }
    public void events() {
        plm.registerEvents(new onPlayerJoin(), this);
        plm.registerEvents(new InvClick(), this);
        plm.registerEvents(new healthMana(), this);
    }
}