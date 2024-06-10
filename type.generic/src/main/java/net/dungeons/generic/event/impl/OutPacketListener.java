package net.dungeons.generic.event.impl;

import net.dungeons.generic.event.IEvent;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerPacketOutEvent;
import org.pmw.tinylog.Logger;

public class OutPacketListener implements IEvent {
    @Override
    public EventListener<?> register() {
        return EventListener.of(PlayerPacketOutEvent.class, c -> {
            System.out.println("Sent packet " + c.getPacket());
        });
    }
}
