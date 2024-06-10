package net.dungeons.generic;

import com.mongodb.client.MongoDatabase;
import net.minestom.server.MinecraftServer;

public interface ITypeLoader {
    String getFolderName();
    ServerType getServerType();
    void onInitialize(MinecraftServer server);
    MongoDatabase getDatabase();
}
