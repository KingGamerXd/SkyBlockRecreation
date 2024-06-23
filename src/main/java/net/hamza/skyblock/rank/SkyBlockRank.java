package net.hamza.skyblock.rank;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.ChatColor;

@Getter
public enum SkyBlockRank {

    OWNER(ChatColor.RED + "[" + ChatColor.RED + "OWNER" + ChatColor.RED + "] ", 10),
    ADMIN(ChatColor.RED + "[" + ChatColor.RED + "ADMIN" + ChatColor.RED + "] ", 9),
    GM(ChatColor.DARK_GREEN + "[" + ChatColor.DARK_GREEN + "GM" + ChatColor.DARK_GREEN + "] ", 8),
    MOD(ChatColor.DARK_GREEN + "[" + ChatColor.DARK_GREEN + "MOD" + ChatColor.DARK_GREEN + "] ", 7),
    HELPER(ChatColor.RED + "[" + ChatColor.WHITE + "HELPER" + ChatColor.BLUE + "] ", 6),
    BUILDER(ChatColor.DARK_AQUA + "[" + ChatColor.DARK_AQUA + "BUILDER" + ChatColor.DARK_AQUA + "] ", 5),
    YOUTUBER(ChatColor.RED + "[" + ChatColor.WHITE + "YOUTUBE" + ChatColor.RED + "] ", 4),
    YT(ChatColor.GOLD + "[" + ChatColor.GOLD + "YT" + ChatColor.GOLD + "] ", 3),
    VIP(ChatColor.RED + "[" + ChatColor.WHITE + "VIP" + ChatColor.RED + "] ", 2),
    DEFAULT(ChatColor.GRAY + " " + ChatColor.GRAY, 1);

    private final String prefix;
    private final int level;
    SkyBlockRank(String prefix, int level) {
        this.prefix = prefix;
        this.level = level;
    }
    public boolean isBelowOrEqual(SkyBlockRank rank) {
        return this.level <= rank.level;
    }

    public boolean isAboveOrEqual(SkyBlockRank rank) {
        return this.level >= rank.level;
    }

    public boolean hasRank(SkyBlockRank requiredRank) {
        return this.level >= requiredRank.level;
    }

    public boolean isStaff() {
        return this.level >= HELPER.level;
    }
}
