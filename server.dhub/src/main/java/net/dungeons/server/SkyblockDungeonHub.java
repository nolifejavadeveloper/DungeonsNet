package net.dungeons.server;

import com.mongodb.client.MongoDatabase;
import net.dungeons.generic.Constants;
import net.dungeons.generic.ITypeLoader;
import net.dungeons.generic.ServerType;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.scoreboard.BasicScoreboard;
import net.dungeons.generic.scoreboard.SkyblockScoreboard;
import net.minestom.server.MinecraftServer;
import org.pmw.tinylog.Logger;

public class SkyblockDungeonHub implements ITypeLoader {

    public SkyblockDungeonHub()
    {

    }


    @Override
    public String getFolderName() {
        return "dhub";
    }

    @Override
    public ServerType getServerType() {
        return ServerType.DungeonHub;
    }

    @Override
    public void onInitialize(MinecraftServer server) {
        Logger.info("Dungeonhub started!");
    }

    @Override
    public MongoDatabase getDatabase() {
        return Constants.mongoClient.getDatabase("skyblock-dev");
    }

    @Override
    public SkyblockScoreboard makeScoreboard(SkyblockPlayer player) {
        return new BasicScoreboard(player);
    }
}
