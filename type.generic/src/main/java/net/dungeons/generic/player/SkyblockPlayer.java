package net.dungeons.generic.player;

import lombok.Getter;
import lombok.Setter;
import net.dungeons.generic.level.SkyblockLevel;
import net.dungeons.generic.rank.Rank;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
@Getter
@Setter
public class SkyblockPlayer extends Player {
    private SkyblockLevel skyblockLevel;
    private Rank rank;

    public SkyblockPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);
    }


    public void load() {

    }
}
