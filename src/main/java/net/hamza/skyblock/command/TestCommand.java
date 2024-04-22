package net.hamza.skyblock.command;

import net.hamza.skyblock.command.abstraction.CommandParameters;
import net.hamza.skyblock.command.abstraction.SkyBlockCommand;
import net.hamza.skyblock.item.ItemBuilder;
import net.hamza.skyblock.item.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandParameters(name = "test" )
public class TestCommand extends SkyBlockCommand {



    @Override
    public void execute(Player player, String[] args) {
        ItemStack stack = ItemBuilder.of(Material.COAL).setDisplayName("COAL").build();

        NBTItem nbtItem = NBTItem.of(stack);
        nbtItem.setBoolean("isVanilla" , true)
                .setString("ID" , stack.getItemMeta().getDisplayName());
        stack = nbtItem.build();

        player.sendMessage("nbts : " + nbtItem);
        player.getInventory().addItem(stack);



    }
}
