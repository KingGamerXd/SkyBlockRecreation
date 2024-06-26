package net.hamza.skyblock.event.gui;


import lombok.Getter;
import lombok.Setter;
import net.hamza.skyblock.gui.abstraction.SkyBlockGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Inventory;

public class GUIOpenEvent extends PlayerEvent implements Cancellable
{
    private static final HandlerList handlers = new HandlerList();
    @Getter
    private final SkyBlockGUI opened;
    @Getter
    @Setter
    private String title;
    @Getter
    private final Inventory inventory;
    private boolean cancelled;

    public GUIOpenEvent(Player player, SkyBlockGUI opened, Inventory inventory)
    {
        super(player);
        this.opened = opened;
        this.title = opened.getTitle();
        this.inventory = inventory;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    @Override
    public boolean isCancelled()
    {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel)
    {
        this.cancelled = cancel;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
