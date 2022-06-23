package com.tqqn.wardenspawn;

import com.tqqn.wardenspawn.commands.TurnOffSpawningCommand;
import com.tqqn.wardenspawn.managers.SpawnManager;
import com.tqqn.wardenspawn.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class WardenSpawnMain extends JavaPlugin {

    private final SpawnManager spawnManager;

    public WardenSpawnMain() {
        this.spawnManager = new SpawnManager();
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Plugin has been loaded.");
        Bukkit.getLogger().info("Plugin made by Tqqn.");
        Bukkit.getLogger().info("Version 1.0");

        registerCommands();

        spawningWardenRunnable();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin disabled.");
    }

    public void registerCommands() {
        this.getCommand("spawnwarden").setExecutor(new TurnOffSpawningCommand(this));
    }

    public SpawnManager getSpawnManager() {
        return this.spawnManager;
    }

    public String getPREFIX() {
        return "&cWardenSpawn";
    }

    public void spawningWardenRunnable() {
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (spawnManager.isSpawningAllowed()) {
                    spawnManager.spawnWarden(onlinePlayer.getLocation());
                    onlinePlayer.sendMessage(Color.translate("&cWatch out!"));
                } else {
                    return;
                }
            }
        }, 1200L, 1200L);
    }
}
