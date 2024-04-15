package net.hamza.skyblock;

import net.hamza.skyblock.command.abstraction.SkyBlockCommand;
import net.hamza.skyblock.util.SkyBlockLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public final class SkyBlock extends JavaPlugin {
    private static final String PACKAGE_NAME = "net.hamza.skyblock";

    @Override
    public void onEnable() {
        registerCommands();

    }

    @Override
    public void onDisable() {

    }


    private void registerCommands(){
        SkyBlockLogger.info("registering commands...");
        for (Class< ? extends SkyBlockCommand> clazz : new Reflections(PACKAGE_NAME + ".command")
                .getSubTypesOf(SkyBlockCommand.class)){
            try {
                SkyBlockCommand skyBlockCommand = clazz.getDeclaredConstructor().newInstance();
                getCommand(skyBlockCommand.getCommandParameters().name()).setExecutor(skyBlockCommand);
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        SkyBlockLogger.info("Successfully registered commands.");
    }

}
