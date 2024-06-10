package net.dungeons.generic.event.impl;

import net.dungeons.generic.event.IEvent;
import net.dungeons.generic.items.SkyblockItem;
import net.dungeons.generic.items.SkyblockItemFactory;
import net.dungeons.generic.player.SkyblockPlayer;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.item.ItemStack;

public class PickupEvent implements IEvent {
    @Override
    public EventListener<?> register() {
        return EventListener.builder(PickupItemEvent.class)
                .filter(c -> c.getEntity() instanceof SkyblockPlayer)
                .handler(c -> {
                    SkyblockPlayer player = (SkyblockPlayer) c.getEntity();

                    ItemStack stack = c.getItemStack();

                    c.setCancelled(true);

                    if (stack instanceof SkyblockItem)
                    {
                        player.getInventory().addItemStack(stack);
                    }
                    else
                    {
                        player.getInventory().addItemStack(SkyblockItemFactory.convertNonToSkyblock(stack, player));
                    }
                }).build();
    }
}
