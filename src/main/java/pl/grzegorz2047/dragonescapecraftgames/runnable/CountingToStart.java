/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.grzegorz2047.dragonescapecraftgames.runnable;

import de.kumpelblase2.remoteentities.api.RemoteEntityType;
import de.kumpelblase2.remoteentities.entities.RemoteEnderDragon;
import org.bukkit.Bukkit;
import pl.grzegorz2047.dragonescapecraftgames.DragonEscape;
import pl.grzegorz2047.dragonescapecraftgames.PluginData;

/**
 *
 * @author Grzegorz
 */
public class CountingToStart {
    
    public CountingToStart(int time){
        countdown(time);
    }
    
    private boolean countdownRunning;
    int counttime = 0;
    public int threadsync = 0;




    void countdown(int time){
        this.threadsync += 1;
        this.countdownRunning = true;
        this.counttime = time;
        PluginData.Time=time;
        
        
        if(time<=0){
            RemoteEnderDragon entity = (RemoteEnderDragon) PluginData.npcManager.createNamedEntity(RemoteEntityType.EnderDragon, PluginData.spawnmonsterlocation, "Lowca");
            PluginData.monster = entity;
            PluginData.monster.shouldDestroyBlocks(true);
            PluginData.monster.setStationary(true);
            PluginData.IDtask = Bukkit.getScheduler().scheduleSyncRepeatingTask(DragonEscape.plugin, new DragonLocation(), 0, 4l);
        }
        if (time > 0) {
           new CountdownThread(time).start();
        }
        
        else if ((time <= 0)) {

            this.countdownRunning = false;
        }
    }

    public static String formatIntoHHMMSS(int secsIn)
    {
        int remainder = secsIn % 3600,
        minutes = remainder / 60,
        seconds = remainder % 60;

        return ( (minutes < 10 ? "0" : "") + minutes
        + ":" + (seconds< 10 ? "0" : "") + seconds );
    }

    public int getCountdownTime()
    {
        return this.counttime;
    }

    class CountdownThread extends Thread
    {
     int time;
     int trun = CountingToStart.this.threadsync;

     public CountdownThread(int t) { this.time = t; }

       @Override
       public void run() {
           this.time -= 1;
           try { Thread.sleep(1000L); } catch (InterruptedException localException) {}
           if (this.trun == CountingToStart.this.threadsync){
            CountingToStart.this.countdown(this.time);
           }
       }
    }
}
