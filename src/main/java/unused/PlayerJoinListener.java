/*
package com.ambmt.plots;

import com.ambmt.plots.OwnerList;
import com.ambmt.plots.PlotRating;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.Objects;

public class PlayerJoinListener implements Listener {
    PlotRating plugin;
    List<String> list = OwnerList.getList();

    public PlayerJoinListener(PlotRating instance) {
            plugin = instance;
    }




        @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String senderCommand = plugin.getConfig().getString("SenderCommand");
        String plotOwnerCommand = plugin.getConfig().getString("PlotOwnerCommand");
        String plotOwnerCommandAfter = plugin.getConfig().getString("PlotOwnerCommandAfter");
        String senderCommandAfter = plugin.getConfig().getString("SenderCommandAfter");
        Player player = e.getPlayer();
        if(list.contains(String.valueOf(player))) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plotOwnerCommand + " " + Objects.requireNonNull(Bukkit.getPlayer(String.valueOf(player))).getName() + " " + plotOwnerCommandAfter);
            System.out.println(player);
            player.sendMessage("lol");
        }




        }


}
*/
