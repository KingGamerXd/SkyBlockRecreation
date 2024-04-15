package net.hamza.skyblock;

import net.hamza.skyblock.command.abstraction.SkyBlockCommand;
import net.hamza.skyblock.util.SkyBlockLogger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public final class SkyBlock extends JavaPlugin {
    private static final String PACKAGE_NAME = "net.hamza.skyblock";

    @Override
    public void onEnable() {

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerListeners(){
        SkyBlockLogger.info("registering listeners...");

        int registered = 0;

        for (Class< ? extends Listener> clazz : new Reflections(PACKAGE_NAME + ".listener")
                .getSubTypesOf(Listener.class)){
            try {
                Listener listener = clazz.getDeclaredConstructor().newInstance();
                this.getServer().getPluginManager().registerEvents(listener , this);
                registered++;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        SkyBlockLogger.info("Successfully registered " + registered + " Listeners");
    }


    private void registerCommands(){
        SkyBlockLogger.info("Registering commands...");
        int registered = 0;
        for (Class< ? extends SkyBlockCommand> clazz : new Reflections(PACKAGE_NAME + ".command")
                .getSubTypesOf(SkyBlockCommand.class)){
            try {
                SkyBlockCommand skyBlockCommand = clazz.getDeclaredConstructor().newInstance();
                skyBlockCommand.register();
                registered++;
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        SkyBlockLogger.info("Successfully registered " + registered + " commands.");
    }

}
