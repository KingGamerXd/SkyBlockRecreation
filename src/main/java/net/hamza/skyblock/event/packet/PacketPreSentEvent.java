package net.hamza.skyblock.event.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hamza.skyblock.event.SkyBlockEvent;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
public class PacketPreSentEvent extends SkyBlockEvent {
      private final Player player;
      private final Packet<?> packet;
}