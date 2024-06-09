package net.dungeons.generic.items;

import net.minestom.server.component.DataComponentMap;
import net.minestom.server.item.ItemStackImpl;
import net.minestom.server.item.Material;

import java.util.UUID;

public class SkyblockItem extends ItemStackImpl implements SItem {
    private String name;
    private boolean isUnique;
    private UUID uuid;
    public SkyblockItem(Material material, int amount, DataComponentMap components) {
        super(material, amount, components);
    }
}
