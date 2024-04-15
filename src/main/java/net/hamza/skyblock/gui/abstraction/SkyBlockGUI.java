package net.hamza.skyblock.gui.abstraction;


import lombok.Getter;
import net.hamza.skyblock.SkyBlock;
import net.hamza.skyblock.event.GUICloseEvent;
import net.hamza.skyblock.event.GUIOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.*;

public abstract class SkyBlockGUI {

    public static final Map<UUID, SkyBlockGUI> GUI_MAP = new HashMap<>();

    @Getter
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

    public GUIItem get(int slot){
        for (GUIItem item : items){
            if (item.getSlot() == slot)
                return item;
        }
        return null;
    }

    public abstract void setItems();


    public void open(Player player){
        bukkitInventory = Bukkit.createInventory(null , inventoryType.getSlots());

        GUIOpenEvent openEvent = new GUIOpenEvent(player , this , bukkitInventory);
        SkyBlock.getInstance().getServer().getPluginManager().callEvent(openEvent);

        if (openEvent.isCancelled()) return;

        player.openInventory(bukkitInventory);

        setItems();
        updateItems();


        GUI_MAP.remove(player.getUniqueId());
        GUI_MAP.put(player.getUniqueId() , this);
    }

    public void updateItems(){
        items.forEach(item -> bukkitInventory.setItem(item.getSlot() , item.getItem()));
    }


    public void onOpen(GUIOpenEvent event){}
    public void onClose(GUICloseEvent event){}
    public void onTopClick(InventoryClickEvent event){}
    public void onBottomClick(InventoryClickEvent event){}

}
