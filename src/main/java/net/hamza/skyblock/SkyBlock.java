package net.hamza.skyblock;

import net.hamza.skyblock.util.SkyBlockLogger;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyBlock extends JavaPlugin {

    @Override
    public void onEnable() {
        SkyBlockLogger.info("Plugin enabled successfully");
    }

    @Override
    public void onDisable() {

    }
}
