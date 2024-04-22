package net.hamza.skyblock.player;

import lombok.Getter;
import net.hamza.skyblock.enums.SkyBlockStat;
import net.hamza.skyblock.util.SkyBlockUtil;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkyBlockPlayer {


    private static final Map<UUID , SkyBlockPlayer> PLAYER_CACHE = new HashMap<>();
    @Getter
    private final UUID uuid;

    private final Map<SkyBlockStat , Double> baseStats;
    private final Map<SkyBlockStat , Double> liveStats;


    private SkyBlockPlayer(UUID uuid){
        this.uuid = uuid;
        this.baseStats = new HashMap<>();
        this.liveStats = new HashMap<>();
        PLAYER_CACHE.put(uuid , this);
    }


    public void tick() {
        Player player = toBukkitPlayer();
        SkyBlockUtil.sendActionBar(this ,
                ChatColor.RED + "" + Math.floor(getBaseStat(SkyBlockStat.HEALTH)) + "/" + Math.floor(getLiveStat(SkyBlockStat.HEALTH)) + SkyBlockStat.HEALTH.getSymbol()
        );

    }

    public Player toBukkitPlayer(){
        return uuid != null ? Bukkit.getPlayer(uuid) : null;
    }


    public static SkyBlockPlayer of(UUID uuid){
        if (uuid == null) return null;
        if (PLAYER_CACHE.containsKey(uuid)) return PLAYER_CACHE.get(uuid);
        return new SkyBlockPlayer(uuid);
    }

    public double getLiveStat(SkyBlockStat skyBlockStat){
        return liveStats.getOrDefault(skyBlockStat , skyBlockStat.getBaseValue());
    }
    public void setLiveStat(SkyBlockStat stat , double value){
        liveStats.put(stat , value);
    }

    public double getBaseStat(SkyBlockStat skyBlockStat){
        return baseStats.getOrDefault(skyBlockStat , skyBlockStat.getBaseValue());
    }

    public void setBaseStat(SkyBlockStat stat , double value){
        baseStats.put(stat , value);
    }

    public void sendPacket(Packet<?> packet){
        CraftPlayer craftPlayer = (CraftPlayer) toBukkitPlayer();
        EntityPlayer entityPlayer = craftPlayer.getHandle();
        PlayerConnection connection = entityPlayer.playerConnection;
        connection.sendPacket(packet);
    }

    public void unload(){
        PLAYER_CACHE.remove(uuid);
    }

}
