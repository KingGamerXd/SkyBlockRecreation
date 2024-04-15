package net.hamza.skyblock.command;

import net.hamza.skyblock.command.abstraction.CommandParameters;
import net.hamza.skyblock.command.abstraction.SkyBlockCommand;
import org.bukkit.entity.Player;

@CommandParameters(name = "test" )
public class TestCommand extends SkyBlockCommand {



    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage("you executed test command");
    }
}
