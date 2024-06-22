package net.hamza.skyblock.packet;

import io.netty.channel.*;
import net.hamza.skyblock.SkyBlock;
import net.hamza.skyblock.event.packet.PacketPreSentEvent;
import net.hamza.skyblock.event.packet.PacketReceiveEvent;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;


public class PacketReader {

    private final Player player;


    public PacketReader(Player player){
        this.player = player;
    }

    public void inject() {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        EntityPlayer entityPlayer = craftPlayer.getHandle();
        Channel channel = entityPlayer.playerConnection.networkManager.channel;
        ChannelPipeline pipeline = channel.pipeline();
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler(){
            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                PacketReceiveEvent event = new PacketReceiveEvent(player , (Packet<?>) packet);
                SkyBlock.getInstance().getServer().getPluginManager().callEvent(event);
                if (!event.isCancelled()) super.channelRead(channelHandlerContext, packet);
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
                PacketPreSentEvent event = new PacketPreSentEvent(player , (Packet<?>) packet);
                SkyBlock.getInstance().getServer().getPluginManager().callEvent(event);
                if (!event.isCancelled()) super.write(channelHandlerContext, packet , channelPromise);
            }
        };
       pipeline.addBefore("packet_handler" , player.getName() , channelDuplexHandler);
    }


}
