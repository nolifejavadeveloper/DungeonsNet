package net.dungeons.generic;

import com.mongodb.client.MongoDatabase;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.scoreboard.SkyblockScoreboard;
import net.minestom.server.MinecraftServer;

public interface ITypeLoader {
    String getFolderName();
    ServerType getServerType();
    void onInitialize(MinecraftServer server);
    MongoDatabase getDatabase();
    SkyblockScoreboard makeScoreboard(SkyblockPlayer player);
}
