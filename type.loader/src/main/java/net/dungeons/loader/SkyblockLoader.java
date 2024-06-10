package net.dungeons.loader;

import lombok.SneakyThrows;
import net.dungeons.generic.GenericSkyblockLoader;
import net.dungeons.generic.ITypeLoader;
import net.minestom.server.MinecraftServer;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import java.util.Set;

public class SkyblockLoader {
    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();
        Reflections reflections = new Reflections("net.dungeons.server");
        Set<Class<? extends ITypeLoader>> subTypes = reflections.getSubTypesOf(ITypeLoader.class);

        if (subTypes.isEmpty()) {
            Logger.error("No typeloader found!!!");
            System.exit(0);
            return;
        }
        ITypeLoader typeLoader = null;
        try {
            typeLoader = subTypes.stream().filter(clazz -> {
                try {
                    ITypeLoader type = clazz.getDeclaredConstructor().newInstance();
                    Logger.info("Found typeloader of type " + type.getServerType().name());
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }).findFirst().orElse(null).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            Logger.error("Failed to find typeloader!");
            System.exit(0);
        }

        new GenericSkyblockLoader(typeLoader).init(server);
        typeLoader.onInitialize(server);

        server.start("0.0.0.0", 25565);
    }
}