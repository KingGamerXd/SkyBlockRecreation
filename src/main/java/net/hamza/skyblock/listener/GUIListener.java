package net.hamza.skyblock.listener;

import net.hamza.skyblock.event.GUICloseEvent;
import net.hamza.skyblock.event.GUIOpenEvent;
import net.hamza.skyblock.gui.abstraction.GUIClickableItem;
import net.hamza.skyblock.gui.abstraction.GUIItem;
import net.hamza.skyblock.gui.abstraction.SkyBlockGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GUIListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        SkyBlockGUI skyBlockGUI = SkyBlockGUI.GUI_MAP.get(player.getUniqueId());
        if (skyBlockGUI == null) return;

        if (event.getClickedInventory() == event.getView().getTopInventory()){
            GUIItem item = skyBlockGUI.get(event.getSlot());
            if (item == null) return;
            if (!item.canPickup()) event.setCancelled(true);
            if (item instanceof GUIClickableItem){
                ((GUIClickableItem) item).run(event);
            }
        }
    }

    @EventHandler
    public void onOpen(GUIOpenEvent event){
        SkyBlockGUI gui = event.getOpened();
        if (gui == null) return;
        gui.onOpen(event);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (!(event.getPlayer() instanceof Player)) return;
        Player player = (Player) event.getPlayer();

        if (SkyBlockGUI.GUI_MAP.get(player.getUniqueId()) == null) return;
        SkyBlockGUI gui = SkyBlockGUI.GUI_MAP.get(player.getUniqueId());

        GUICloseEvent closeEvent = new GUICloseEvent(player , gui);

        gui.onClose(closeEvent);
        SkyBlockGUI.GUI_MAP.remove(player.getUniqueId());
    }

}
