package net.hamza.skyblock.command;

import net.hamza.skyblock.command.abstraction.CommandParameters;
import net.hamza.skyblock.command.abstraction.SkyBlockCommand;
import net.hamza.skyblock.player.SkyBlockPlayer;
import net.hamza.skyblock.rank.SkyBlockRank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;


@CommandParameters(description = "Modify player rank" , usages = "/setrank rank" , requireOperator = true)
public class SetRankCommand extends SkyBlockCommand {

    @Override
    public void execute(Player player, String[] args) {
        SkyBlockRank rank = null;
        try {
            rank = SkyBlockRank.valueOf(args[0].toUpperCase());
        }catch (IllegalArgumentException exception){
            player.sendMessage(ChatColor.RED + "Invalid rank!");
        }
        if (rank == null) return;
        SkyBlockPlayer.of(player).setRank(rank);
        player.sendMessage("Successfully modified rank!");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> availableRanks = new ArrayList<>();
        Arrays.stream(SkyBlockRank.values()).forEach(skyBlockRank -> availableRanks.add(skyBlockRank.name().toLowerCase()));
        return availableRanks;
    }
}
