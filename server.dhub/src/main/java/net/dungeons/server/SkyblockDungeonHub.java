package net.dungeons.server;

import net.dungeons.generic.ITypeLoader;
import net.dungeons.generic.ServerType;
import net.minestom.server.MinecraftServer;
import org.pmw.tinylog.Logger;

public class SkyblockDungeonHub implements ITypeLoader {

    public SkyblockDungeonHub()
    {

    }


    @Override
    public String getFolderName() {
        return "";
    }

    @Override
    public ServerType getServerType() {
        return ServerType.DungeonHub;
    }

    @Override
    public void onInitialize(MinecraftServer server) {
        Logger.info("Dungeonhub started!");
    }
}
