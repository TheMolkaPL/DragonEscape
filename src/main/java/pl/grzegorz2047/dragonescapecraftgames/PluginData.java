/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.grzegorz2047.dragonescapecraftgames;

import de.kumpelblase2.remoteentities.EntityManager;
import de.kumpelblase2.remoteentities.entities.RemoteEnderDragon;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 *
 * @author Grzegorz
 */
public class PluginData {
    
    public static Location playersspawn;
    public static int minplayerstostart=5;
    public static int maxplayerforgame=10;
    public static String prefix = "[DG]";
    public static List<String> serializedlocations = new ArrayList<>();
    public static List<Location> listofcheckpoints = new ArrayList<>();
    public static int checkpointnumber=0;
    public static RemoteEnderDragon monster;
    public static EntityManager npcManager;
    public static int IDtask;
    public static Location spawnmonsterlocation;
    public static double speedofmonster = 0.5;
    public static int Time;
    public enum Status { WAITING, COUNTING, INGAME };
    public static Status ArenaStatus = Status.WAITING;
    public static Status getArenaStatus(){
        return ArenaStatus;
    }
    public static void setArenaStatus(Status s){
        ArenaStatus=s;
    }
    void loadAllCheckPoints(){
        if(PluginData.serializedlocations.isEmpty()){
            System.out.println("Brak zapisanych checkpointow!");
            return;
        }
        for(String location : PluginData.serializedlocations ){
            String[] splitted = location.split(":");
            String w = splitted[0];
            double x = Double.parseDouble(splitted[1]);
            double y = Double.parseDouble(splitted[2]);
            double z = Double.parseDouble(splitted[3]);
            this.addCheckPoint(w, (int)x, (int)y, (int)z);
        }
        
    }
    void loadDragonLocation(){
        String location = DragonEscape.plugin.getConfig().getString("StartLocation");
            try{
                String[] splitted = location.split(":");
                String w = splitted[0];
                double x = Double.parseDouble(splitted[1]);
                double y = Double.parseDouble(splitted[2]);
                double z = Double.parseDouble(splitted[3]);
                this.setSpawnMonsterLocation(w, x, y, z);
                System.out.println("Pomyslnie zaladowano lokacje startowa smoka!");
            }
            catch(Exception ex){
                System.out.println("Brak lokacji smoka!");
            }

    }
    void loadPlayersSpawn(){
        try{
            String location = DragonEscape.plugin.getConfig().getString("PlayersSpawn");
            String[] splitted = location.split(":");
            String w = splitted[0];
            double x = Double.parseDouble(splitted[1]);
            double y = Double.parseDouble(splitted[2]);
            double z = Double.parseDouble(splitted[3]);
            this.setPlayersSpawn(w, x, y, z);
            System.out.println("Pomyslnie zaladowano lokacje startowa graczy!");
        }
        catch(Exception ex){
            System.out.println("Brak spawnu gracza");
        }

    }
    private void addCheckPoint(String w, int x, int y, int z){
        Location loc = new Location(Bukkit.getWorld(w),x,y,z);
        if(loc==null){
            System.out.println("Wystapil blad null przy dodawaniu checkpointu do listy!");
            return;
        }
        if(PluginData.listofcheckpoints.add(loc)){
            System.out.println("Pomyslnie dodano checkpoint!");
        }
    }
    void setSpawnMonsterLocation(String w, double x, double y, double z){
        PluginData.spawnmonsterlocation = new Location(Bukkit.getWorld(w), x, y, z);
    }
    void setPlayersSpawn(String w, double x, double y, double z){
        PluginData.playersspawn = new Location(Bukkit.getWorld(w), x, y, z);
    }
    public static Location getDragonLocation(){
        return monster.getBukkitEntity().getLocation();
    }
    
    
    
}
