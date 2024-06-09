package net.dungeons.generic;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Getter;
import net.dungeons.generic.player.SkyblockPlayer;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.anvil.AnvilLoader;
import org.pmw.tinylog.Logger;

import java.util.Objects;

public record GenericSkyblockLoader(ITypeLoader load) {

    @Getter
    private static MinecraftServer server;
    @Getter
    private static ITypeLoader loader;

    public void init(MinecraftServer server)
    {
        initMongoConnection();

        GenericSkyblockLoader.server = server;
        GenericSkyblockLoader.loader = load;

        InstanceManager manager = MinecraftServer.getInstanceManager();

        String path = "./config/" + loader.getFolderName();

        if (!loader.getFolderName().isEmpty())
        {
            InstanceContainer container = manager.createInstanceContainer();
            container.setChunkLoader(new AnvilLoader(path));

            Constants.instanceContainer = container;
        }

        Constants.eventHandler = MinecraftServer.getGlobalEventHandler();

        MinecraftServer.getConnectionManager().setPlayerProvider((uuid, name, playerConnection) -> {
            SkyblockPlayer player = new SkyblockPlayer(uuid, name, playerConnection);

            /*
            * future: get version, origin server, authentication.
            * */

            return player;
        });
    }

    private void initMongoConnection() {
        String connString = "mongodb+srv://server:Pr6xfWQu42blUb0Z@dungeonsnet.joqyrao.mongodb.net/?retryWrites=true&w=majority&appName=DungeonsNet";
        Logger.info("Connecting to MongoDB...");

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connString))
                .serverApi(serverApi)
                .build();

        try {
            Constants.mongoClient = MongoClients.create(settings);
            Logger.info("Successfully connected to MongoDB!");
        }catch (MongoException e) {
            Logger.error("Failed to connect to MongoDB! " + e.getMessage());
        }

    }
}
