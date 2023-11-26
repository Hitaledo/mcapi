package com.hitaledo.mcapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import io.javalin.Javalin;

public class Web {
    private static Javalin webApp;

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
        webApp = Javalin.create().start(port);
        webApp.get("/", ctx -> ctx.result("Minecraft API enabled!"));
        webApp.post("/command", ctx -> {
            if (!finalPassword.equals(ctx.formParam("password"))) {
                ctx.result("false");
            } else {
                String command = ctx.formParam("command");
                try {
                    Bukkit.getScheduler().runTask(plugin, () -> {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
                    });
                    ctx.result("true");
                } catch (Exception e) {
                    ctx.result("false");
                }
            }
        });
    }

    public static void disable() {
        if (webApp != null) {
            webApp.stop();
        }
    }
}