/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.grzegorz2047.dragonescape.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.grzegorz2047.dragonescape.PluginData;
import pl.grzegorz2047.dragonescape.PluginData.Status;

/**
 *
 * @author Grzegorz
 */
public class PlayerMove implements Listener{
    
    
    @EventHandler
    void onMove(PlayerMoveEvent e){
        if(PluginData.getArenaStatus().equals(Status.INGAME)){
            if(PluginData.monster!=null){
               if(PluginData.getDragonLocation().distance(e.getPlayer().getLocation())<6){
                    e.getPlayer().kickPlayer(PluginData.prefix+"Przegrales!");
               }    
            }
        }
        else{
            if(!e.getPlayer().isOp()){
                e.setCancelled(true);
            } 
        }
    }
}
