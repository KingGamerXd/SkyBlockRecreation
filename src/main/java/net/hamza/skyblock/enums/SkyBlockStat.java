package net.hamza.skyblock.enums;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum SkyBlockStat {

    HEALTH("❤" , ChatColor.RED , 100),
    DEFENSE("❈" , ChatColor.GREEN , 0),
    STRENGTH("❁" , ChatColor.RED , 0),
    INTELLIGENCE("✎" , ChatColor.AQUA , 0),
    SPEED("✦" , ChatColor.WHITE , 100 , true),
    CRIT_DAMAGE("☠" , ChatColor.BLUE , 50),
    CRIT_CHANCE("☣" , ChatColor.BLUE , 30);

    private final String symbol;
    private final ChatColor color;
    private final double baseValue;
    private final boolean isPercent;

    SkyBlockStat(String symbol , ChatColor color , double baseValue , boolean isPercent){
        this.symbol = symbol;
        this.color = color;
        this.baseValue = baseValue;
        this.isPercent = isPercent;
    }

    SkyBlockStat(String symbol , ChatColor color , double baseValue){
        this.symbol = symbol;
        this.color = color;
        this.baseValue = baseValue;
        this.isPercent = false;
    }





}
