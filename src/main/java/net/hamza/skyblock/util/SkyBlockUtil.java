package net.hamza.skyblock.util;

import net.hamza.skyblock.player.SkyBlockPlayer;
import net.minecraft.server.v1_8_R3.*;
public class SkyBlockUtil {




    public static void runAsync(Runnable runnable){
        new Thread(runnable).start();
    }

    public static void sendActionBar(SkyBlockPlayer player , String content){
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(new ChatComponentText(content) , (byte) 2);
        player.sendPacket(packetPlayOutChat);
    }

}
