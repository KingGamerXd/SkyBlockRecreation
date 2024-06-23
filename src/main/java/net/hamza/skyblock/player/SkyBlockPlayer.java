package net.hamza.skyblock.player;

import lombok.Getter;
import lombok.Setter;
import net.hamza.skyblock.database.SkyBlockDB;
import net.hamza.skyblock.rank.SkyBlockRank;
import net.hamza.skyblock.util.SkyBlockUtil;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bson.Document;
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
    @Getter
    @Setter
    private long coins;
    @Getter
    private SkyBlockRank rank;

    private final SkyBlockDB database;



    private SkyBlockPlayer(UUID uuid){
        this.uuid = uuid;
        this.database = new SkyBlockDB("users" , new Document("_id" , uuid.toString()));
        load();
        PLAYER_CACHE.put(uuid , this);
    }

    public void tick() {
       // nothing here yet!
    }

    public void setRank(SkyBlockRank rank){
        this.rank = rank;
        toBukkitPlayer().setDisplayName(rank.getPrefix() + " " + toBukkitPlayer().getDisplayName());
    }

    public void load(){
        if (!database.isExist()) save();
        coins = database.getLong("coins" , 0L);
        setRank(SkyBlockRank.valueOf(database.getString("rank" , String.valueOf(SkyBlockRank.DEFAULT))));
    }
    public void save(){
       database.setProperty("coins" , coins);
       database.setProperty("rank" , rank.toString());
       database.save();
    }

    public void addCoins(long value){
        coins += value;
    }
    public void subCoins(long value){
        if (value > coins) return;
        coins -= value;
    }

    public Player toBukkitPlayer(){
        return uuid != null ? Bukkit.getPlayer(uuid) : null;
    }

    public static SkyBlockPlayer of(UUID uuid){
        if (uuid == null) return null;
        if (PLAYER_CACHE.containsKey(uuid)) return PLAYER_CACHE.get(uuid);
        return new SkyBlockPlayer(uuid);
    }

    public static SkyBlockPlayer of(Player player){
        if (player == null) return null;
        return of(player.getUniqueId());
    }

    public void sendPacket(Packet<?> packet){
        CraftPlayer craftPlayer = (CraftPlayer) toBukkitPlayer();
        EntityPlayer entityPlayer = craftPlayer.getHandle();
        PlayerConnection connection = entityPlayer.playerConnection;
        connection.sendPacket(packet);
    }

    public void unload(){
        save();
        PLAYER_CACHE.remove(uuid);
    }

}
