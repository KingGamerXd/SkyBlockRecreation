package net.hamza.skyblock.listener;

import net.hamza.skyblock.SkyBlock;
import net.hamza.skyblock.player.SkyBlockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        SkyBlockPlayer skyBlockPlayer = SkyBlockPlayer.of(event.getPlayer().getUniqueId());
        new BukkitRunnable(){

            @Override
            public void run() {
                if (Bukkit.getPlayer(skyBlockPlayer.getUuid()) == null || !Bukkit.getPlayer(skyBlockPlayer.getUuid()).isOnline()){
                    this.cancel();
                    return;
                }
                skyBlockPlayer.tick();
            }
        }.runTaskTimer(SkyBlock.getInstance() , 20 , 20);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        SkyBlockPlayer skyBlockPlayer = SkyBlockPlayer.of(event.getPlayer().getUniqueId());
        skyBlockPlayer.unload();
    }

}
