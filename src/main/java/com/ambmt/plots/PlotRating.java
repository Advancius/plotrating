package com.ambmt.plots;

import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.api.PlotAPI;
import com.plotsquared.core.events.PlotRateEvent;
import org.bukkit.Bukkit;
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
        //pm.registerEvents(new PlayerJoinListener(this), this);





    }
    public class P2Listener{





        final FileConfiguration config = getConfig();

        @Subscribe
        public void onPlotRateEvent(PlotRateEvent event){
            String senderCommand = config.getString("SenderCommand");
            String plotOwnerCommand = config.getString("PlotOwnerCommand");
            String plotOwnerCommandAfter = config.getString("PlotOwnerCommandAfter");
            String senderCommandAfter = config.getString("SenderCommandAfter");
            //double rating = event.getPlot().getAverageRating();
            UUID owner = event.getPlot().getOwner();
            Player owners = Bukkit.getPlayer(owner);
            //if(Bukkit.getPlayerExact(String.valueOf(owners)) != null) {
            event.getRater().sendMessage("It worked");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), senderCommand + " " + event.getRater() + " " + senderCommandAfter);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plotOwnerCommand + " " + Objects.requireNonNull(Bukkit.getPlayer(owner)).getName() + " " + plotOwnerCommandAfter);
            }
            //else if(Bukkit.getPlayerExact(String.valueOf(owners))== null){
               // list.add(String.valueOf(owners));
            //}

    }
        @Override
    public void onDisable() {
        System.out.println("Shutting Down Rating Plugin");
    }
}
