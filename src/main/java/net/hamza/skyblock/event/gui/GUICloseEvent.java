package net.hamza.skyblock.event.gui;

import lombok.Getter;
import net.hamza.skyblock.gui.abstraction.SkyBlockGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class GUICloseEvent extends PlayerEvent
{
    private static final HandlerList handlers = new HandlerList();
    @Getter
    private final SkyBlockGUI closed;

    public GUICloseEvent(Player player, SkyBlockGUI opened)
    {
        super(player);
        this.closed = opened;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
