package com.ambmt.plots;

import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.api.PlotAPI;
import com.plotsquared.core.events.PlotRateEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public final class PlotRating extends JavaPlugin {
    List<String> list = OwnerList.getList();


    @Override
    public void onEnable() {
        System.out.println("Plot Rating Loaded");
        PlotAPI plotApi = new PlotAPI();
        plotApi.registerListener(new P2Listener());
        final FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
        PluginManager pm = getServer().getPluginManager();


    }

    public class P2Listener {


        final FileConfiguration config = getConfig();

        @Subscribe
        public void onPlotRateEvent(PlotRateEvent event) {
            String senderCommand = config.getString("SenderCommand");
            String plotOwnerCommand = config.getString("PlotOwnerCommand");
            String plotOwnerCommandAfter = config.getString("PlotOwnerCommandAfter");
            String senderCommandAfter = config.getString("SenderCommandAfter");
            event.getPlot().getMembers();
            UUID owner = event.getPlot().getOwner();
            Player owners = Bukkit.getPlayer(owner);
            event.getRater().sendMessage(ChatColor.DARK_PURPLE + "Thank you for rating the plot ");
            event.getRater().sendMessage("It worked");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), senderCommand + " " + event.getRater() + " " + senderCommandAfter);
            //if (Bukkit.getPlayer(owner) != null) {
            try {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plotOwnerCommand + " " + Objects.requireNonNull(Bukkit.getPlayer(owner)).getName() + " " + plotOwnerCommandAfter);
            }catch(NullPointerException e){
                System.out.println("Error Occurred, owner of plot is most likely offline ");

            }


                //} else if (Bukkit.getPlayer(owner) == null) {
               // Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "QueuedCommand" + " " + Objects.requireNonNull(Bukkit.getPlayer(owner)).getName() + " " + plotOwnerCommand + plotOwnerCommandAfter);
            //}

        }
    }

        @Override
        public void onDisable() {
            System.out.println("Shutting Down Rating Plugin");
        }
}
