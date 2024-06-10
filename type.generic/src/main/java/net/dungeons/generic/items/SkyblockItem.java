package net.dungeons.generic.items;

import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.stats.SkyblockStats;
import net.dungeons.generic.stats.Stat;
import net.minestom.server.component.DataComponent;
import net.minestom.server.component.DataComponentMap;
import net.minestom.server.item.ItemComponent;
import net.minestom.server.item.ItemStackImpl;
import net.minestom.server.item.Material;
import net.minestom.server.item.component.DyedItemColor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class SkyblockItem extends ItemStackImpl implements SItem {
    public SkyblockStats stats;
    public boolean dungeonized;
    public ItemRarity rarity;
    public String texture;
    public Material material;
    public int count;
    //public IReforge reforge;
    public String itemId;
    public String name;
    public SItem baseItem;
    public boolean unique;
    public UUID uuid;
    public byte stars;
    //public List<Enchantment> enchantments;
    public SkyblockItemModifier itemModifier;

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
    public double getStat(Stat stat, SkyblockPlayer player, SkyblockItem ignored) {
        return this.getStats(player, this).getStat(stat);
    }

    @Override
    public SkyblockStats getStats(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getStats(player, this);
    }

    @Override
    public ItemRarity getItemRarity(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getItemRarity(player, this);
    }

    @Override
    public String getTexture(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getTexture(player, this);
    }

    @Override
    public DyedItemColor leatherColor(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.leatherColor(player, this);
    }

    @Override
    public boolean isDungeonized(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.isDungeonized(player, this);
    }

    @Override
    public Material getMaterial(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getMaterial(player, this);
    }

    @Override
    public int getCount(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getCount(player, this);
    }

    //@Override
    //public IReforge getReforge(SkyblockPlayer player, SkyblockItem use) {
        //return this.baseItem.getReforge(player, this);
    //}

    @Override
    public String getItemID(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getItemID(player, this);
    }

    @Override
    public List<String> getDescription(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getDescription(player, this);
    }

    @Override
    public String getItemName(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getItemName(player, this);
    }

    @Override
    public boolean isUnique(SkyblockItem ignored) {
        return baseItem.isUnique(this);
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public byte getStars(SkyblockPlayer player, SkyblockItem ignored) {
        return baseItem.getStars(player, this);
    }

    /*@Override
    public List<SAbility> getAbilities(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getAbilities(player, this);
    }

    @Override
    public List<Enchantment> getEnchantments(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getEnchantments(player, this);
    }*/

    @Override
    public SkyblockItemModifier getItemModifier(SkyblockPlayer player, SkyblockItem ignored) {
        return this.baseItem.getItemModifier(player, this);
    }
}
