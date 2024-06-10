package net.dungeons.generic.items;

import net.dungeons.generic.ability.SkyblockAbility;
import net.dungeons.generic.enchant.Enchantment;
import net.dungeons.generic.gemstone.GemstoneSlot;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.reforge.IReforge;
import net.dungeons.generic.stats.SkyblockStats;
import net.dungeons.generic.stats.Stat;
import net.minestom.server.item.Material;
import net.minestom.server.item.component.DyedItemColor;

import java.util.List;
import java.util.UUID;

public class SkyblockItemBasic implements SItem{
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
    public DyedItemColor leatherColor(SkyblockPlayer player, SkyblockItem use) {
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
    public IReforge getReforge(SkyblockPlayer player, SkyblockItem use) {
        return null;
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
    public List<SkyblockAbility> getAbilities(SkyblockPlayer player, SkyblockItem use) {
        return List.of();
    }

    @Override
    public List<Enchantment> getEnchantments(SkyblockPlayer player, SkyblockItem use) {
        return List.of();
    }

    @Override
    public List<GemstoneSlot> getGemstoneSlots(SkyblockPlayer player, SkyblockItem use) {
        return List.of();
    }

    @Override
    public SkyblockItemModifier getItemModifier(SkyblockPlayer player, SkyblockItem use) {
        return null;
    }

    @Override
    public SItemType getItemType(SkyblockPlayer player, SkyblockItem use) {
        return null;
    }
}
