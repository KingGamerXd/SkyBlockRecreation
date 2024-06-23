package net.hamza.skyblock.command;

import net.hamza.skyblock.command.abstraction.CommandParameters;
import net.hamza.skyblock.command.abstraction.SkyBlockCommand;
import net.hamza.skyblock.player.SkyBlockPlayer;
import net.hamza.skyblock.rank.SkyBlockRank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandParameters(description = "Modify player coins", rank = SkyBlockRank.ADMIN)
public class CoinsCommand extends SkyBlockCommand {

    @Override
    public void execute(Player player, String[] args) {
        if (args.length < 2){
            player.sendMessage("length : " + args.length);
            player.sendMessage(ChatColor.RED + "usages /coins <add/remove/set> value");
            return;
        }
        SkyBlockPlayer skyBlockPlayer = SkyBlockPlayer.of(player);
        long value = Long.parseLong(args[1]);
        switch (args[0]){
            case "add":
                 skyBlockPlayer.addCoins(value);
                 break;
            case "remove":
                 skyBlockPlayer.subCoins(value);
                 break;
            case "set":
                  skyBlockPlayer.setCoins(value);
                  break;
            default:
                player.sendMessage(ChatColor.RED + "usages /coins <add/remove/set> value");

        player.sendMessage("Successfully updated your coins to " + skyBlockPlayer.getCoins());
        }
    }
}
