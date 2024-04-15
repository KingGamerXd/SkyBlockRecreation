package net.hamza.skyblock.gui;

import net.hamza.skyblock.gui.abstraction.GUIClickableItem;
import net.hamza.skyblock.gui.abstraction.InventoryType;
import net.hamza.skyblock.gui.abstraction.SkyBlockGUI;
import net.hamza.skyblock.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUITest extends SkyBlockGUI {
    public GUITest() {
        super("Test GUI" , InventoryType.MEDIUM);
    }

    @Override
    public void setItems() {

        set(new GUIClickableItem() {
            @Override
            public void run(InventoryClickEvent event) {
                event.getWhoClicked().sendMessage("you just clicked on test item");
            }

            @Override
            public int getSlot() {
                return 0;
            }


            @Override
            public ItemStack getItem() {
                return ItemBuilder.of(Material.EMERALD).setDisplayName("test item").build();
            }
        });

    }
}
