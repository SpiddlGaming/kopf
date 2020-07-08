package de.nitrocraft.kopf.kopfManager;

import de.nitrocraft.kopf.Main;
import de.nitrocraft.kopf.data.Data;
import de.nitrocraft.kopf.data.file.FileManager;
import de.nitrocraft.kopf.data.mysql.SQLManager;
import org.bukkit.entity.Player;

public class Kopf {

    public static boolean canGetSkull(Player player){
        long end;
        if(player.hasPermission("kopf.admin")){
            return true;
        }
        if(Data.useMySQL){
            end = Main.players.get(player).getTime() + Data.kopfCooldown*1000L;
        }else{
            end = FileManager.getFile().getLong("Users." + player.getUniqueId().toString() + ".Time") + Data.kopfCooldown*1000L;
        }


        if(System.currentTimeMillis() >= end){
            return true;
        }else{
            return false;
        }
    }


    public static Long getRemindingTIme(Player player){
        if(Data.useMySQL){
            return (Main.players.get(player).getTime() + Data.kopfCooldown*1000L);
        }else{
            return (FileManager.getFile().getLong("Users." + player.getUniqueId().toString() + ".Time") + Data.kopfCooldown*1000L);
        }
    }

    public static void setWatingTime(Player player){
        if(Data.useMySQL){
            SQLManager.updateData(player, System.currentTimeMillis());
        }else{
            FileManager.getFile().setValue("Users." + player.getUniqueId().toString() + ".Time", System.currentTimeMillis());
            FileManager.getFile().save();
        }
    }


}
