package net.dungeons.generic.player;

import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SkyblockPlayer extends Player {


    public SkyblockPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);
    }
}
