package net.dungeons.generic.items;

import lombok.Getter;
import net.dungeons.generic.GenericSkyblockLoader;
import net.dungeons.generic.util.Stringify;
import net.minestom.server.item.Material;
import org.pmw.tinylog.Logger;

import java.util.HashMap;

public class SkyblockItemRegistry {
    @Getter
    private static final HashMap<String, SItem> skyblockItems = new HashMap<>();

    public static void init()
    {
        Logger.info("Loading items!");

        for (Material material : Material.values())
        {
            String id = material.name();
            String name = Stringify.toCamelCase(id);

            skyblockItems.put(id, new SkyblockItemBasic(name, material, id));
        }

        GenericSkyblockLoader.loopThroughPackage("net.dungeons.generic.items.impl", SItem.class)
                .forEach(c -> {
                    skyblockItems.put(c.getItemID(null, null), c);
                });
    }

    public static SItem get(String key)
    {
        if (skyblockItems.containsKey(key))
            return skyblockItems.get(key);
        return null;
    }
}
