/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.grzegorz2047.dragonescape;
import pl.grzegorz2047.dragonescape.commands.DgCommands;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import de.kumpelblase2.remoteentities.RemoteEntities;
import de.kumpelblase2.remoteentities.api.RemoteEntityType;
import de.kumpelblase2.remoteentities.entities.RemoteEnderDragon;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.grzegorz2047.dragonescape.PluginData;
import pl.grzegorz2047.dragonescape.events.PlayerDead;
import pl.grzegorz2047.dragonescape.events.PlayerJoin;
import pl.grzegorz2047.dragonescape.events.PlayerLogin;
import pl.grzegorz2047.dragonescape.events.PlayerMove;
/**
 *
 * @author Grzegorz
 */


public class DragonEscape extends JavaPlugin implements Listener {
    public static DragonEscape plugin;
    @Override
    public void onEnable()
    {
        plugin=this;
        this.saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        PluginData.npcManager = RemoteEntities.createManager(this);
        PluginData.serializedlocations = this.getConfig().getStringList("ListOfCheckPoints");
        PluginData pd = new PluginData();
        pd.loadAllCheckPoints();
        pd.loadDragonLocation();
        pd.loadPlayersSpawn();
        this.getCommand("dg").setExecutor(new DgCommands());
        loadAllListeners();
    }
    void loadAllListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerDead(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new PlayerLogin(), this);
        
    }
        
}
