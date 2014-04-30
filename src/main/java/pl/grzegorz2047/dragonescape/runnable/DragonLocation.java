/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.grzegorz2047.dragonescape.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import pl.grzegorz2047.dragonescape.PluginData;
import pl.grzegorz2047.dragonescape.PluginData.Status;

/**
 *
 * @author Grzegorz
 */
public class DragonLocation implements Runnable {

    public DragonLocation(){
        PluginData.setArenaStatus(Status.INGAME);
        PotionEffect effect=new PotionEffect(PotionEffectType.SLOW, 2147483647, 10, false);
        PluginData.monster.getBukkitEntity().addPotionEffect(effect);
    }
    
    @Override
    public void run() {
        PluginData.monster.setStationary(false);
        PluginData.monster.getBukkitEntity().setRemoveWhenFarAway(false);
        Location loc = PluginData.getDragonLocation();
        Location checkpointlocation;
        if(PluginData.checkpointnumber<PluginData.listofcheckpoints.size()){
            checkpointlocation = PluginData.listofcheckpoints.get(PluginData.checkpointnumber);
            PluginData.monster.lookAt(PluginData.listofcheckpoints.get(PluginData.checkpointnumber));
            int r = 6;
            for(double x = loc.getX() - r; x <= loc.getX() + r; x++){
                for(double y = loc.getY() - r; y <= loc.getY() + r; y++){
                    for(double z = loc.getZ() - r; z <= loc.getZ() + r; z++){
                        Location loca = new Location(loc.getWorld(), x, y, z);
                        if(loca.getBlock().getType()!=Material.AIR){
                            loca.getBlock().setType(Material.AIR);
                        }
                    }
                }
            }
            if(Bukkit.getOnlinePlayers().length<2){
                //Bukkit.getOnlinePlayers()[0].kickPlayer("Dotrwales!");
                //Bukkit.shutdown();
            }
        }
        else{
            Bukkit.broadcastMessage("Smok zakonczyl podroz!");
            PluginData.monster.setStationary(true);
            Bukkit.getScheduler().cancelTask(PluginData.IDtask);
            return;
        }
        
        if(loc == null || checkpointlocation == null){
            System.out.println("Bledne wystartowanie zadania! Loc jest "+loc+"\n a checkpoint "+checkpointlocation);
            return;
        }
        if(loc.distance(checkpointlocation)<2){
            Bukkit.broadcastMessage("Dotarl blisko checkpointu, teraz ustawia następny checkpoint!");
            PluginData.checkpointnumber++;
        }
        else{
            Bukkit.broadcastMessage(""+(int)loc.distance(checkpointlocation));
            Vector dir = checkpointlocation.toVector().subtract(loc.toVector()).normalize();
            PluginData.monster.getBukkitEntity().setVelocity(dir.multiply(PluginData.speedofmonster));
        }
    }
    
}
