package com.hitaledo.mcapi;
import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
public class App extends JavaPlugin {
    String pluginName = "Minecraft API";
    File data;
    FileConfiguration dataConfig;
    File config;
    @Override
    public void onEnable() {
        data = new File(getDataFolder(), "data.yml");
        dataConfig = YamlConfiguration.loadConfiguration(data);
        try{
            dataConfig.save(data);
        }catch(Exception exeption){
            exeption.printStackTrace();
            getLogger().info(ChatColor.RED + "Error creating data file");
        }
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
