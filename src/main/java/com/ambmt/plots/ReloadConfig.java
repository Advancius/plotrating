package com.ambmt.plots;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class ReloadConfig implements CommandExecutor {
    public static PlotRating plugin;

    public ReloadConfig(PlotRating instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reload-PlotRating")) {
            if (args.length > 0) {
                sender.sendMessage(ChatColor.RED + "To many arguments.");
                return false;
            }
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (hasReload(player)) {
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.YELLOW + "Reloaded " + ChatColor.GRAY + plugin.getDescription().getFullName());
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have access to that.");
                }
            } else {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.YELLOW + "Reloaded " + ChatColor.GRAY + plugin.getDescription().getFullName());
            }
            return true;
        }
        return false;
    }

    public boolean hasReload(Player player) {
        if (player.hasPermission("plotrating.reload")) {
            return true;
        } else if (player.hasPermission("plotra.*")) {
            return true;
        }
        return false;
    }
}
