package net.hamza.skyblock.command;

import net.hamza.skyblock.command.abstraction.CommandParameters;
import net.hamza.skyblock.command.abstraction.SkyBlockCommand;
import net.hamza.skyblock.gui.GUITest;
import org.bukkit.entity.Player;

@CommandParameters(name = "test" )
public class TestCommand extends SkyBlockCommand {



    @Override
    public void execute(Player player, String[] args) {
        GUITest guiTest = new GUITest();
        guiTest.open(player);
    }
}
