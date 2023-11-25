package com.hitaledo.mcapi;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import static spark.Spark.*;
public class Web {
    public static void enable(App plugin) {
        Integer port;
        FileConfiguration config = plugin.getConfig();
        try {
            port = config.getInt("Config.port");
        } catch (Exception e) {
            port = 80;
            plugin.getLogger().info(ChatColor.GREEN + "Port config not found. Using default value: " + port);
        }
        port(port);
        get("/", (req, res) -> "Minecraft API!");
    }
    public static void disable() {
        stop();
    }
}