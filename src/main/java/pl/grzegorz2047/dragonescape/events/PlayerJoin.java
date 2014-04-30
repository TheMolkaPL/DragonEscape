/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.grzegorz2047.dragonescape.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.grzegorz2047.dragonescape.PluginData;
import pl.grzegorz2047.dragonescape.PluginData.Status;
import pl.grzegorz2047.dragonescape.runnable.CountingToStart;

/**
 *
 * @author Grzegorz
 */
public class PlayerJoin implements Listener{
    
    
    @EventHandler
    void onJoin(PlayerJoinEvent e){
        if(PluginData.getArenaStatus().equals(Status.WAITING)){
            if(PluginData.playersspawn==null){
                System.out.println("Ustaw spawn graczy komenda /dg setplayersspawn !");
            }
            else{
                e.getPlayer().teleport(PluginData.playersspawn);
            }
            
            if(Bukkit.getOnlinePlayers().length>=7){
                CountingToStart cts = new CountingToStart(30);
            }
        }
    }
}
