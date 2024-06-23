package net.hamza.skyblock.command.abstraction;

import lombok.Getter;
import net.hamza.skyblock.SkyBlock;
import net.hamza.skyblock.player.SkyBlockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class SkyBlockCommand implements CommandExecutor {

    private final CommandParameters commandParameters;
    private final SkyBlockCommandImpl skyBlockCommandImpl;
    private final String name;

    public SkyBlockCommand() {
        commandParameters = getClass().getDeclaredAnnotation(CommandParameters.class);
        name = getClass().getSimpleName().toLowerCase().replace("command", "");
        Objects.requireNonNull(commandParameters, "Missing command parameters annotation");
        this.skyBlockCommandImpl = new SkyBlockCommandImpl(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((!SkyBlockPlayer.of((Player) sender).getRank().isAboveOrEqual(commandParameters.rank())) || (commandParameters.requireOperator() && !sender.isOp())) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        execute((Player) sender, args);
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }


    public void execute(Player player, String[] args) {

    }

    public void register() {
        ((CraftServer) Bukkit.getServer()).getCommandMap().register(SkyBlock.getInstance().getName(), skyBlockCommandImpl);
    }

    public static class SkyBlockCommandImpl extends BukkitCommand {

        private final SkyBlockCommand skyBlockCommand;

        protected SkyBlockCommandImpl(SkyBlockCommand command) {
            super(
                    command.getName(),
                    command.commandParameters.description(),
                    command.commandParameters.usages(),
                    Arrays.asList(command.commandParameters.aliases())
            );
            this.skyBlockCommand = command;
        }

        @Override
        public boolean execute(CommandSender commandSender, String label, String[] args) {
            skyBlockCommand.onCommand(commandSender, this, label, args);
            return true;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
            List<String> arguments = skyBlockCommand.onTabComplete(sender, this, alias, args);
            if (arguments == null || arguments.isEmpty()) {
                return tabComplete(sender, alias, args);
            }
            return arguments;
        }
    }
}

