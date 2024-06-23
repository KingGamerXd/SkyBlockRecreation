package net.hamza.skyblock.listener;

import net.hamza.skyblock.event.packet.PacketPreSentEvent;
import net.hamza.skyblock.event.packet.PacketReceiveEvent;
import net.hamza.skyblock.util.SkyBlockLogger;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PacketListener implements Listener {

    @EventHandler
    public void onReceive(PacketReceiveEvent event){
        Player player = event.getPlayer();
        Packet<?> packet = event.getPacket();
        SkyBlockLogger.info(player.getName() + " is sending " + packet.getClass().getSimpleName());
    }

    @EventHandler
    public void onSent(PacketPreSentEvent event){
        Player player = event.getPlayer();
        Packet<?> packet = event.getPacket();
        SkyBlockLogger.info(player.getName() + " is receiving " + packet.getClass().getSimpleName());
    }
}
