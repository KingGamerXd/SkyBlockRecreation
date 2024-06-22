package net.hamza.skyblock.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SkyBlockEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private boolean isCancelled;

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlersList() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
