/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.grzegorz2047.dragonescape.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.grzegorz2047.dragonescape.PluginData;
import pl.grzegorz2047.dragonescape.PluginData.Status;

/**
 *
 * @author Grzegorz
 */
public class PlayerDead implements Listener{
    
    @EventHandler
    void onDead(PlayerDeathEvent e){
        if(PluginData.getArenaStatus().equals(Status.INGAME)){
             e.getEntity().kickPlayer("Przegrales!");
        }
       
    }
    
}
