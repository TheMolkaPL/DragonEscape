/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.grzegorz2047.dragonescapecraftgames.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import pl.grzegorz2047.dragonescapecraftgames.PluginData;
import pl.grzegorz2047.dragonescapecraftgames.PluginData.Status;

/**
 *
 * @author Grzegorz
 */
public class PlayerLogin implements Listener {
    
    @EventHandler
    void onLogin(PlayerLoginEvent e){
        if(!PluginData.getArenaStatus().equals(Status.INGAME)){
            if(Bukkit.getOnlinePlayers().length>PluginData.maxplayerforgame){
                e.disallow(Result.KICK_OTHER, "Arena jest pelna!");
            }
        }
        else{
            if(!e.getPlayer().isOp()){
                e.disallow(Result.KICK_OTHER, "Na arenie trwa rozgrywka!");
            }
        }
    }
}
