package net.hamza.skyblock.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface GUIClickableItem extends GUIItem{

    void run(InventoryClickEvent event);

}
