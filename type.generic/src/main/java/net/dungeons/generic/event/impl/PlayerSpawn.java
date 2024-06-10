package net.dungeons.generic.event.impl;

import net.dungeons.generic.GenericSkyblockLoader;
import net.dungeons.generic.event.IEvent;
import net.dungeons.generic.player.SkyblockPlayer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerSpawnEvent;
import org.pmw.tinylog.Logger;


public class PlayerSpawn implements IEvent {
    @Override
    public EventListener<?> register() {
        return EventListener.builder(PlayerSpawnEvent.class)
                .handler(event -> {
                    SkyblockPlayer player = (SkyblockPlayer) event.getPlayer();

                    player.load();

                    Logger.info("Loaded data of player " + player.getName());

                    player.setPermissionLevel(4);
                    player.setGameMode(GameMode.CREATIVE);

                    player.showScoreboard(GenericSkyblockLoader.loader.makeScoreboard(player));

                    player.getInstance().loadChunk(player.getRespawnPoint()).join();
                }).build();
    }
}
