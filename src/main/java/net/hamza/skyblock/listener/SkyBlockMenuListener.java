package net.hamza.skyblock.listener;

import net.hamza.skyblock.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SkyBlockMenuListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = player.getInventory();


        inventory.setItem(8 , new ItemBuilder(Material.NETHER_STAR)
                .setDisplayName("§aSkyBlock Menu " + "§7(Right Click)")
                .setLore("&7View all of your SkyBlock",
                         "&7progress, including your Skills,",
                         "&7Collection , Recipes and more!",
                         " ",
                         "&eClick to open!").build());

    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inventory = event.getClickedInventory();
        if (inventory == null) return;

        if (!(inventory.getType() == InventoryType.PLAYER)) return;
        if (event.getSlot() != 8) return;

        event.setCancelled(true);

        // todo : open inventory

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        ItemStack item = event.getItemDrop().getItemStack();

        if (item.getType() != Material.NETHER_STAR) return;

        if (item.getItemMeta() == null) return;

        if (!item.getItemMeta().getDisplayName().equals("§aSkyBlock Menu " + "§7(Right Click)")) return;

        event.setCancelled(true);
    }

}
