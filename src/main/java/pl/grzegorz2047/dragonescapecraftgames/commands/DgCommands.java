/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.grzegorz2047.dragonescapecraftgames.commands;

import pl.grzegorz2047.dragonescapecraftgames.runnable.DragonLocation;
import pl.grzegorz2047.dragonescapecraftgames.DragonEscape;
import de.kumpelblase2.remoteentities.api.RemoteEntityType;
import de.kumpelblase2.remoteentities.entities.RemoteEnderDragon;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.dragonescapecraftgames.PluginData;
import pl.grzegorz2047.dragonescapecraftgames.PluginData;
import pl.grzegorz2047.dragonescapecraftgames.PluginData.Status;

/**
 *
 * @author Grzegorz
 */
public class DgCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player p = (Player)sender;
        if(args.length>0){
            if(args[0].equalsIgnoreCase("setplayersspawn")){
                if(p.isOp()|| p.hasPermission("DragonEscape.SetPlayerSpawn")){
                    String location = p.getWorld().getName()+":"+p.getLocation().getX()+":"+p.getLocation().getY()+":"+p.getLocation().getZ();
                    DragonEscape.plugin.getConfig().set("PlayersSpawn", location);
                    p.sendMessage(PluginData.prefix+"Pomyslnie ustalono startowa lokacje Graczy!");
                    return true;
                }
            }
            if(args[0].equalsIgnoreCase("setstartlocation")){
                if(p.isOp()|| p.hasPermission("DragonEscape.SetStartLocation")){
                    String location = p.getWorld().getName()+":"+p.getLocation().getX()+":"+p.getLocation().getY()+":"+p.getLocation().getZ();
                    DragonEscape.plugin.getConfig().set("StartLocation", location);
                    p.sendMessage(PluginData.prefix+"Pomyslnie ustalono startowa lokacje Smoka!");
                    return true;
                }
            }
            if(args[0].equalsIgnoreCase("addCheckPoint")){
                if(p.isOp() || p.hasPermission("DragonEscape.AddCheckPoint")){
                    String location = p.getWorld().getName()+":"+p.getLocation().getX()+":"+p.getLocation().getY()+":"+p.getLocation().getZ();
                    PluginData.serializedlocations.add(location);
                    DragonEscape.plugin.getConfig().set("ListOfCheckPoints",PluginData.serializedlocations);
                    DragonEscape.plugin.saveConfig(); 
                    p.sendMessage(PluginData.prefix+"Pomyslnie dodano CheckPoint.");
                    return true;
                }
            }
            if(args[0].equalsIgnoreCase("removecheckpoints")){
                if(p.isOp() || p.hasPermission("DragonEscape.RemoveCheckPoints")){
                    PluginData.serializedlocations.clear();
                    DragonEscape.plugin.getConfig().set("ListOfCheckPoints",PluginData.serializedlocations);
                    DragonEscape.plugin.saveConfig();
                    p.sendMessage(PluginData.prefix+"Pomyslnie wyczyszczono CheckPointy.");
                    return true;
                }

            }
            if(args[0].equalsIgnoreCase("start")){
                if(p.isOp() || p.hasPermission("DragonEscape.Start")){
                    if(!Bukkit.getScheduler().isCurrentlyRunning(PluginData.IDtask)){
                        if(!PluginData.getArenaStatus().equals(Status.INGAME)){
                            if(PluginData.spawnmonsterlocation==null){
                                p.sendMessage(PluginData.prefix+"Nie ustawiles startowej lokacji smoka! Ustaw lokacje i zrestartuj serwer!");
                            }
                            RemoteEnderDragon entity = (RemoteEnderDragon) PluginData.npcManager.createNamedEntity(RemoteEntityType.EnderDragon, PluginData.spawnmonsterlocation, "Lowca");
                            PluginData.monster = entity;
                            PluginData.monster.shouldDestroyBlocks(true);
                            PluginData.monster.setStationary(true);
                            PluginData.IDtask = Bukkit.getScheduler().scheduleSyncRepeatingTask(DragonEscape.plugin, new DragonLocation(), 0, 10l);
                            p.sendMessage(PluginData.prefix+"Pomyslnie wystartowano rozgrywke!");
                            return true;
                        }
                    }
                    else{
                        p.sendMessage(PluginData.prefix+"Proces jest juz uruchomiony! Rozgrywka trwa?");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
}
