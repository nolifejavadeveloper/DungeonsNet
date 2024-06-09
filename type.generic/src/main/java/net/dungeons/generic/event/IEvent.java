package net.dungeons.generic.event;

import net.minestom.server.event.EventListener;

public interface IEvent {
    EventListener<?> register();
}
