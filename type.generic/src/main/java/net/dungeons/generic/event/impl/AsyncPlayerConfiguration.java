package net.dungeons.generic.event.impl;

import net.dungeons.generic.Constants;
import net.dungeons.generic.event.IEvent;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;

public class AsyncPlayerConfiguration implements IEvent {
    @Override
    public EventListener<?> register() {
        return EventListener.of(AsyncPlayerConfigurationEvent.class, c -> {
            c.setSpawningInstance(Constants.instanceContainer);
            Constants.instanceContainer.loadChunk(0, 0).join();

            c.getPlayer().setRespawnPoint(new Pos(-30.5, 121, 0.5, 90, 0));
        });
    }
}
