package net.dungeons.generic.event.impl;

import net.dungeons.generic.event.IEvent;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerPacketEvent;
import org.pmw.tinylog.Logger;

public class PacketListener implements IEvent {

    @Override
    public EventListener<?> register() {
        return EventListener.of(PlayerPacketEvent.class, c -> {
            System.out.println("Packet received: " + c.getPacket());
        });
    }
}
