package net.hamza.skyblock.command.abstraction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class SkyBlockCommand implements CommandExecutor {

    private final CommandParameters commandParameters;
    private final SkyBlockCommandImpl skyBlockCommandImpl;

    public SkyBlockCommand(){
        commandParameters = getClass().getDeclaredAnnotation(CommandParameters.class);
        Objects.requireNonNull(commandParameters , "Missing command parameters annotation");
        this.skyBlockCommandImpl = new SkyBlockCommandImpl(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!commandParameters.permission().isEmpty()){
            if (!sender.hasPermission(commandParameters.permission())){
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }
        }
        execute((Player) sender, args);
        return true;
    }



    public void execute(Player player , String[] args){

    }

    public void register(){
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("", skyBlockCommandImpl);
    }

    public static class SkyBlockCommandImpl extends BukkitCommand {

        private final SkyBlockCommand skyBlockCommand;
        protected SkyBlockCommandImpl(SkyBlockCommand command) {
            super(command.commandParameters.name());
            this.skyBlockCommand = command;
        }

        @Override
        public boolean execute(CommandSender commandSender, String label, String[] args) {
            skyBlockCommand.onCommand(commandSender , this , label , args);
            return true;
        }
    }
}

