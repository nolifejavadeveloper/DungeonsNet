package net.dungeons.generic;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Getter;
import net.dungeons.generic.event.EventManager;
import net.dungeons.generic.pet.SkyblockPet;
import net.dungeons.generic.player.SkyblockPlayer;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.anvil.AnvilLoader;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public record GenericSkyblockLoader(ITypeLoader load) {

    @Getter
    private static MinecraftServer server;
    @Getter
    private static ITypeLoader loader;

    public void init(MinecraftServer server)
    {
        GenericSkyblockLoader.server = server;

        if (load == null)
        {
            throw new RuntimeException("ITypeLoader cannot be null!!!");
        }

        GenericSkyblockLoader.loader = load;

        new Thread(this::initMongoConnection).start();

        InstanceManager manager = MinecraftServer.getInstanceManager();

        if (!loader.getFolderName().isEmpty())
        {
            InstanceContainer container = manager.createInstanceContainer();
            container.setChunkLoader(new AnvilLoader("./config/" + loader.getFolderName()));

            Logger.info("Created instance container!");

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

        EventManager.init(Constants.eventHandler);
        SkyblockPet.init();
        MojangAuth.init();
    }

    public static <T> Stream<T> loopThroughPackage(String packageName, Class<T> clazz) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(clazz);

        return subTypes.stream()
                .map(subClass -> {
                    try {
                        return clazz.cast(subClass.getDeclaredConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException e) {
                        return null;
                    }
                })
                .filter(java.util.Objects::nonNull);
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
        } catch (MongoException e) {
            Logger.error("Failed to connect to MongoDB! " + e.getMessage());
        }

        Constants.selectedDatabase = loader.getDatabase();
        Constants.playerCollection = Constants.selectedDatabase.getCollection("players");
    }
}
