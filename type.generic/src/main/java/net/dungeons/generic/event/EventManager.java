package net.dungeons.generic.event;

import net.dungeons.generic.Constants;
import net.dungeons.generic.GenericSkyblockLoader;
import net.dungeons.generic.player.SkyblockPlayer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

public class EventManager {
    public static void init(GlobalEventHandler handler)
    {
        GenericSkyblockLoader.loopThroughPackage("net.dungeons.generic.event.impl", IEvent.class)
                .forEach(c -> {
                    Logger.info("Registered listener: " + c.getClass().getSimpleName());
                    handler.addListener(c.register());
                });
    }
}
