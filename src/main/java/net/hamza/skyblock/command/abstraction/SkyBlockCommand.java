package net.hamza.skyblock.command.abstraction;

import net.hamza.skyblock.command.abstraction.CommandParameters;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class SkyBlockCommand implements CommandExecutor {

    private final CommandParameters commandParameters;

    public SkyBlockCommand(){
        commandParameters = getClass().getDeclaredAnnotation(CommandParameters.class);
        Objects.requireNonNull(commandParameters , "Missing command parameters annotation");
    }

    public CommandParameters getCommandParameters() {
        return commandParameters;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!commandParameters.permission().isEmpty()){
            if (!sender.hasPermission(commandParameters.permission())){
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }
        }

        if (commandParameters.requirePlayer()){
            if (!(sender instanceof Player)){
                sender.sendMessage(ChatColor.RED + "You are not allowed to use this command!");
                return true;
            }
            execute((Player) sender, args);
            return true;
        }
        execute(sender , args);
        return true;
    }

    public void execute(CommandSender sender , String[] args){

    }

    public void execute(Player player , String[] args){

    }



}
