package net.dungeons.generic.event.impl;

import net.dungeons.generic.event.IEvent;
import net.dungeons.generic.items.SkyblockItem;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerSwapItemEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.listener.PlayerHeldListener;

public class SwapSlot implements IEvent {

    @Override
    public EventListener<?> register() {
        return EventListener.of(PlayerSwapItemEvent.class, c -> {
            c.getPlayer().sendMessage("No offhand bad sir");
            c.setCancelled(true);
        });
    }
}
