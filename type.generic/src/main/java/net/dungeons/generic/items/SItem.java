package net.dungeons.generic.items;

import net.dungeons.generic.ability.SkyblockAbility;
import net.dungeons.generic.enchant.Enchantment;
import net.dungeons.generic.gemstone.GemstoneSlot;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.reforge.IReforge;
import net.dungeons.generic.stats.SkyblockStats;
import net.dungeons.generic.stats.Stat;
import net.minestom.server.color.Color;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.ItemStackImpl;
import net.minestom.server.item.Material;
import net.minestom.server.item.component.DyedItemColor;

import java.util.List;
import java.util.UUID;

public interface SItem {
    double getStat(Stat stat, SkyblockPlayer player, SkyblockItem use);
    SkyblockStats getStats(SkyblockPlayer player, SkyblockItem use);
    //**data**
    ItemRarity getItemRarity(SkyblockPlayer player, SkyblockItem use);
    String getTexture(SkyblockPlayer player, SkyblockItem use);
    DyedItemColor leatherColor(SkyblockPlayer player, SkyblockItem use);
    boolean isDungeonized(SkyblockPlayer player, SkyblockItem use);
    Material getMaterial(SkyblockPlayer player, SkyblockItem use);
    int getCount(SkyblockPlayer player, SkyblockItem use);
    IReforge getReforge(SkyblockPlayer player, SkyblockItem use);
    String getItemID(SkyblockPlayer player, SkyblockItem use);
    List<String> getDescription(SkyblockPlayer player, SkyblockItem use);
    String getItemName(SkyblockPlayer player, SkyblockItem use);
    boolean isUnique(SkyblockItem use);
    UUID getUUID();
    byte getStars(SkyblockPlayer player, SkyblockItem use);
    List<SkyblockAbility> getAbilities(SkyblockPlayer player, SkyblockItem use);
    List<Enchantment> getEnchantments(SkyblockPlayer player, SkyblockItem use);
    List<GemstoneSlot> getGemstoneSlots(SkyblockPlayer player, SkyblockItem use);
    SkyblockItemModifier getItemModifier(SkyblockPlayer player, SkyblockItem use);
    SItemType getItemType(SkyblockPlayer player, SkyblockItem use);
    boolean enchanted(SkyblockPlayer player, SkyblockItem use);

    static SkyblockItem cast(ItemStack item)
    {
        if (item instanceof ItemStackImpl impl && impl instanceof SkyblockItem)
            return (SkyblockItem) impl;
        return null;
    }
}
