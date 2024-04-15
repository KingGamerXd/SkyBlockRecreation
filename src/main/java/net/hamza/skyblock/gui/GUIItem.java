package net.hamza.skyblock.gui;

import org.bukkit.inventory.ItemStack;

public interface GUIItem {

    int getSlot();

    ItemStack getItem();

    default boolean canPickup() {
        return false;
    }

}
