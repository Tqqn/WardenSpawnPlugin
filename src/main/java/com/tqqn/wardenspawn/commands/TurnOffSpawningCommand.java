package com.tqqn.wardenspawn.commands;

import com.tqqn.wardenspawn.WardenSpawnMain;
import com.tqqn.wardenspawn.utils.Color;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class TurnOffSpawningCommand implements CommandExecutor, TabCompleter {
    private final WardenSpawnMain plugin;

    //spawning true/false
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length != 1) {
            player.sendMessage(Color.translate(plugin.getPREFIX() + " &cPlease add true or false."));
            return true;
        }
        if (args[0].equalsIgnoreCase("true")) {
            if (plugin.getSpawnManager().isSpawningAllowed()) {
                player.sendMessage(Color.translate(plugin.getPREFIX() + " &cThe spawning is already enabled!"));
                return true;
            } else {
                plugin.getSpawnManager().setSpawningBoolean(true);
                player.sendMessage(plugin.getPREFIX() + " &cThe spawning has been enabled!");
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("false")) {
            if (!(plugin.getSpawnManager().isSpawningAllowed())) {
                player.sendMessage(Color.translate(plugin.getPREFIX() + " &cThe spawning is already disabled!"));
                return true;
            } else {
                plugin.getSpawnManager().setSpawningBoolean(false);
                player.sendMessage(Color.translate(plugin.getPREFIX() + " &cThe spawning has been disabled!"));
                return true;
            }
        } else {
            player.sendMessage(Color.translate("&cWardenSpawnPlugin - Commands:"));
            player.sendMessage(Color.translate("&f&lspawnwarden &r&6true/false"));
            player.sendMessage("");
            player.sendMessage(Color.translate("&7Plugin made by &1Tqqn&7."));
            player.sendMessage(Color.translate("&7Version: &f&l1.0"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> truefalse = new ArrayList<>();
        truefalse.add("true");
        truefalse.add("false");
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList(args), new ArrayList<>());
        }
        return Collections.emptyList();
    }
}
