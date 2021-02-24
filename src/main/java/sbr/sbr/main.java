package sbr.sbr;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sbr.sbr.commands.balance;
import sbr.sbr.commands.banker;
import sbr.sbr.commands.npc;
import sbr.sbr.events.InvClick;
import sbr.sbr.events.onPlayerJoin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public final class main extends JavaPlugin {
    private static Connection connection;
    private static String host, database, username, password;
    private static int port;

    @Override
    public void onEnable() {
        PluginManager plm = Bukkit.getPluginManager();
        host = "sql11.freemysqlhosting.net";
        port = 3306;
        database = "sql11395031";
        username = "sql11395031";
        password = "6VF5N7cZiG";
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
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
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
}