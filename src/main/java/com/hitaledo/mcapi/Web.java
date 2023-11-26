package com.hitaledo.mcapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import static spark.Spark.*;

public class Web {
    public static void enable(App plugin) {
        Integer port;
        String password;
        FileConfiguration config = plugin.getConfig();
        try {
            port = config.getInt("Config.port");
        } catch (Exception e) {
            port = 80;
            plugin.getLogger().info(ChatColor.GREEN + "Port config not found. Using default value: " + port);
        }
        try {
            password = config.getString("Config.password");
        } catch (Exception e) {
            password = "password";
            plugin.getLogger().info(ChatColor.GREEN + "Password config not found. Using default value: " + password);
        }
        final String finalPassword = password;
        port(port);
        get("/", (req, res) -> "Minecraft API enabled!");
        post("/command", (req, res) -> {
            if (!finalPassword.equals(req.queryParams("password"))) {
                return false;
            }
            String command = req.queryParams("command");
            try {
                Bukkit.getScheduler().runTask(plugin, () -> {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
                });
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