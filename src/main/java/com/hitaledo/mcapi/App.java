package com.hitaledo.mcapi;
import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
public class App extends JavaPlugin {
    String pluginName = "Minecraft API";
    File config;
    @Override
    public void onEnable() {
        config = new File(getDataFolder(), "config.yml");
        if (!config.exists()){
            saveDefaultConfig();
        }
        Web.enable(this);
        getLogger().info(ChatColor.GREEN + pluginName + " has been enabled!");
    }
    @Override
    public void onDisable() {
        Web.disable();
        getLogger().info(ChatColor.GREEN + pluginName + " has been disabled!");
    }
}