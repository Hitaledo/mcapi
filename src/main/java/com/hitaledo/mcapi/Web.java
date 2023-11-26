package com.hitaledo.mcapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
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
        get("/", (req, res) -> "Minecraft API enabled!");
        post("/command", (req, res) -> {
            String command = req.queryParams("command");
            ConsoleCommandSender console = plugin.getServer().getConsoleSender();
            try {
                Bukkit.dispatchCommand(console, command);
            } catch (Exception e) {
                return false;
            }
            return true;
        });
    }

    public static void disable() {
        stop();
    }
}