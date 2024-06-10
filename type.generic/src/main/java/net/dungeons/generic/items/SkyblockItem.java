package net.dungeons.generic.items;

import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.stats.SkyblockStats;
import net.dungeons.generic.stats.Stat;
import net.kyori.adventure.text.Component;
import net.minestom.server.component.DataComponent;
import net.minestom.server.component.DataComponentMap;
import net.minestom.server.item.ItemComponent;
import net.minestom.server.item.ItemStackImpl;
import net.minestom.server.item.Material;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class SkyblockItem extends ItemStackImpl implements SItem {
    private String name;
    private boolean isUnique;
    private UUID uuid;

    public SkyblockItem(Material material, int amount, DataComponentMap components) {
        super(material, amount, components);
    }

    public <T> SkyblockItem put(@NotNull DataComponent<T> component, T value) {
        this.components.set(component, value);

        return this;
    }

    @Override
    public DataComponentMap components()
    {
        return this.components;
    }

    @Override
    public double getStat(Stat stat, SkyblockPlayer player, SkyblockItem use) {
        return 0;
    }

    @Override
    public SkyblockStats getStats(SkyblockPlayer player, SkyblockItem use) {
        return null;
    }

    @Override
    public ItemRarity getItemRarity(SkyblockPlayer player, SkyblockItem use) {
        return null;
    }

    @Override
    public String getTexture(SkyblockPlayer player, SkyblockItem use) {
        return "";
    }

    @Override
    public Color leatherColor(SkyblockPlayer player, SkyblockItem use) {
        return null;
    }

    @Override
    public boolean isDungeonized(SkyblockPlayer player, SkyblockItem use) {
        return false;
    }

    @Override
    public Material getMaterial(SkyblockPlayer player, SkyblockItem use) {
        return null;
    }

    @Override
    public int getCount(SkyblockPlayer player, SkyblockItem use) {
        return 0;
    }

    @Override
    public String getItemID(SkyblockPlayer player, SkyblockItem use) {
        return "";
    }

    @Override
    public List<String> getDescription(SkyblockPlayer player, SkyblockItem use) {
        return List.of();
    }

    @Override
    public String getItemName(SkyblockPlayer player, SkyblockItem use) {
        return "";
    }

    @Override
    public boolean isUnique(SkyblockItem use) {
        return false;
    }

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public byte getStars(SkyblockPlayer player, SkyblockItem use) {
        return 0;
    }

    @Override
    public SkyblockItemModifier getItemModifier(SkyblockPlayer player, SkyblockItem use) {
        return null;
    }
}
