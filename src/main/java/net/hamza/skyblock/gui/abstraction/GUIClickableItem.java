package net.hamza.skyblock.gui.abstraction;

import net.hamza.skyblock.gui.abstraction.GUIItem;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface GUIClickableItem extends GUIItem {

    void run(InventoryClickEvent event);

}
