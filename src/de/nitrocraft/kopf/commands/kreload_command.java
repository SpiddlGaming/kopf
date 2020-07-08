package de.nitrocraft.kopf.commands;

import de.nitrocraft.kopf.data.Data;
import de.nitrocraft.kopf.data.file.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class kreload_command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        if(commandSender.hasPermission("nitro.kopf.reload") || commandSender.hasPermission("nitro.kopf.admin")){
            FileManager.relaodFile();
            commandSender.sendMessage(Data.Prefix + "§aDas Plugin hat sich ohne Probleme neugestartet");
        }else{
            commandSender.sendMessage(Data.noPerm);
        }


        return false;
    }
}
