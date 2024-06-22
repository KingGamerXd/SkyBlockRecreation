package net.hamza.skyblock.event.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hamza.skyblock.event.SkyBlockEvent;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@AllArgsConstructor
@Getter
public class PacketReceiveEvent extends SkyBlockEvent {
    private final Player player;
    private final Packet<?> packet;
}
