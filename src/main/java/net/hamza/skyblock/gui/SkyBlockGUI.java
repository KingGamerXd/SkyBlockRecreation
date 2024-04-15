package net.hamza.skyblock.gui;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public abstract class SkyBlockGUI {

    private String title;
    private InventoryType inventoryType;

    private List<GUIItem> items;

    private Inventory bukkitInventory;

    public SkyBlockGUI(String title , InventoryType type){
        this.title = title;
        this.inventoryType = type;
        this.items = new ArrayList<>();
    }

    public SkyBlockGUI(String title){
        this(title , InventoryType.SMALL);
    }


    public void set(GUIItem guiItem){
        items.removeIf(item -> item.getSlot() == guiItem.getSlot());
        items.add(guiItem);
    }


    public void open(Player player){
        bukkitInventory = Bukkit.createInventory(null , inventoryType.getSlots());

        items.forEach(item -> bukkitInventory.setItem(item.getSlot() , item.getItem()));

        player.openInventory(bukkitInventory);

    }

}
