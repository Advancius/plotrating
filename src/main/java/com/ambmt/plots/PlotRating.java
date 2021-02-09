package com.ambmt.plots;

import com.ambmt.plots.commands.ReloadConfig;
import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.api.PlotAPI;
import com.plotsquared.core.events.PlotRateEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public final class PlotRating extends JavaPlugin implements CommandExecutor {
    List<String> list = OwnerList.getList();


    @Override
    public void onEnable() {

        System.out.println("Plot Rating Loaded");
        PlotAPI plotApi = new PlotAPI();
        plotApi.registerListener(new P2Listener());
        final FileConfiguration config = getConfig();
        saveDefaultConfig();
        PluginManager pm = getServer().getPluginManager();
        this.getCommand("plotratingreload").setExecutor((CommandExecutor) new ReloadConfig(this));



    }

    public class P2Listener {



        @Subscribe
        public void onPlotRateEvent(PlotRateEvent event) {
            String senderCommand = getConfig().getString("SenderCommand");
            String plotOwnerCommand = getConfig().getString("PlotOwnerCommand");
            String plotOwnerCommandAfter = getConfig().getString("PlotOwnerCommandAfter");
            String senderCommandAfter = getConfig().getString("SenderCommandAfter");
            String sendRatingMessage = getConfig().getString("SendRatingMessage");
            String sendErrorMessage = getConfig().getString("SendErrorMessage");
            UUID owner = event.getPlot().getOwner();
            Player owners = Bukkit.getPlayer(owner);
            if(getConfig().getBoolean("SendRatingMessage") == true)  {
                event.getRater().sendMessage(ChatColor.LIGHT_PURPLE + "Thank you for rating the plot ");
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), senderCommand + " " + event.getRater() + " " + senderCommandAfter);
            System.out.println(senderCommand);
            try {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plotOwnerCommand + " " + Objects.requireNonNull(Bukkit.getPlayer(owner)).getName() + " " + plotOwnerCommandAfter);
            } catch (NullPointerException e) {
                System.out.println("Error Occurred, owner of plot is most likely offline ");
                if(getConfig().getBoolean("SendErrorMessage") == true) {
                    event.getRater().sendMessage(ChatColor.YELLOW + ("Player is offline, thank you for rating the plot"));
                }

            }

        }
    }

        @Override
        public void onDisable() {
            System.out.println("Shutting Down Rating Plugin");
        }
}