package net.dungeons.generic.event.impl;

import net.dungeons.generic.event.IEvent;
import net.dungeons.generic.player.SkyblockPlayer;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import org.pmw.tinylog.Logger;

public class PlayerLeave implements IEvent {

    @Override
    public EventListener<?> register() {
        return EventListener.of(PlayerDisconnectEvent.class, e -> {
            SkyblockPlayer player = (SkyblockPlayer) e.getPlayer();

            player.save();

            Logger.info("Saved data of " + player.getName());
        });
    }
}
