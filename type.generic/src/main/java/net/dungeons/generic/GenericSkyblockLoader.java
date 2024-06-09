package net.dungeons.generic;

import lombok.Getter;
import net.dungeons.generic.player.SkyblockPlayer;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.anvil.AnvilLoader;

import java.util.Objects;

public record GenericSkyblockLoader(ITypeLoader load) {

    @Getter
    private static MinecraftServer server;
    @Getter
    private static ITypeLoader loader;

    public void init(MinecraftServer server)
    {
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

        MinecraftServer.getConnectionManager().setPlayerProvider((uuid, name, playerConnection) -> {
            SkyblockPlayer player = new SkyblockPlayer(uuid, name, playerConnection);

            /*
            * future: get version, origin server, authentication.
            * */
        });
    }
}
