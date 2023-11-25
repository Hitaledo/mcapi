package com.hitaledo.mcapi;
import spark.Spark;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
public class Web {
    public static void enable(App plugin) {
        Integer port = 80;
        FileConfiguration dataConfig = plugin.dataConfig;
        if(!dataConfig.contains("port")){
            plugin.getLogger().info(ChatColor.GREEN + "Port config not found. Using default value: " + port);
        } else {
            port = dataConfig.getInt("port");
        }
        Spark.port(port);
        Spark.get("/", (req, res) -> "Minecraft API!");
    }
    public static void disable() {
        Spark.stop();
    }
}