package net.dungeons.generic;

import net.minestom.server.MinecraftServer;

public interface ITypeLoader {
    String getFolderName();
    ServerType getServerType();

    void onInitialize(MinecraftServer server);
}
