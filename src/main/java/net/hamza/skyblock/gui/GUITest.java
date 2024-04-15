package net.hamza.skyblock.gui;

import net.hamza.skyblock.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GUITest extends SkyBlockGUI{
    public GUITest() {
        super("Test GUI" , InventoryType.MEDIUM);

        set(new GUIItem() {
            @Override
            public int getSlot() {
                return 0;
            }

            @Override
            public ItemStack getItem() {
                return ItemBuilder.of(Material.EMERALD).setDisplayName("Test").build();
            }
        });

    }

}
