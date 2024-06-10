package net.dungeons.generic.event.impl;

import net.dungeons.generic.event.IEvent;
import net.dungeons.generic.player.SkyblockPlayer;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerTickEvent;

public class PlayerTick implements IEvent {
    @Override
    public EventListener<?> register() {
        return EventListener.of(PlayerTickEvent.class, c -> {
            SkyblockPlayer player = (SkyblockPlayer) c.getPlayer();

            player.tick();
        });
    }
}
