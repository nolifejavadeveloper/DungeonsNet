package net.dungeons.generic.items.impl.sword;

import net.dungeons.generic.ability.SkyblockAbility;
import net.dungeons.generic.enchant.Enchantment;
import net.dungeons.generic.enchant.impl.SharpnessEnchant;
import net.dungeons.generic.gemstone.GemstoneSlot;
import net.dungeons.generic.gemstone.GemstoneSlotType;
import net.dungeons.generic.items.*;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.reforge.IReforge;
import net.dungeons.generic.stats.SkyblockStats;
import net.dungeons.generic.stats.Stat;
import net.dungeons.generic.util.Stringify;
import net.minestom.server.item.Material;
import net.minestom.server.item.component.DyedItemColor;

import java.util.List;
import java.util.UUID;

public class EmeraldBlade implements SItem {
    @Override
    public double getStat(Stat stat, SkyblockPlayer player, SkyblockItem use) {
        return this.getStats(player, use).getStat(stat);
    }

    @Override
    public SkyblockStats getStats(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return new SkyblockStats()
                    .setDamage(130);

        return use.stats.copy()
                .addStat(Stat.DAMAGE, getDamageBonus(player.getCoins()));
    }

    @Override
    public ItemRarity getItemRarity(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return ItemRarity.EPIC;
        return use.rarity;
    }

    @Override
    public String getTexture(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return "";
        return use.texture;
    }

    @Override
    public DyedItemColor leatherColor(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return null;
        return null;
    }

    @Override
    public boolean isDungeonized(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return false;
        return use.dungeonized;
    }

    @Override
    public Material getMaterial(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return Material.EMERALD;
        return use.material;
    }

    @Override
    public int getCount(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return 1;
        return use.count;
    }

    @Override
    public IReforge getReforge(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return null;
        return use.reforge;
    }

    @Override
    public String getItemID(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return "EMERALD_BLADE";
        return use.itemId;
    }

    @Override
    public List<String> getDescription(SkyblockPlayer player, SkyblockItem use) {
        return Stringify.createLore(
                "&7A powerful blade made from pure",
                "&2Emeralds&7. This blade becomes",
                "&7strongers as you carry more &6coins &7in",
                "&7your purse &8(Capped at 2B coins)",
                "&7",
                "&7Receive &4Curse Of Greed &7when striking,",
                "&cCANCELLING &7any effect modifying your &6coins",
                "&7loss on &4death&7!",
                "&7",
                "&7Current Damage Bonus: &a" + String.format("%.1f", getDamageBonus(player.getCoins()))
        );
    }

    public double getDamageBonus(long coins)
    {
        return Math.pow(Math.min(coins, 2_000_000_000), 0.25) * 2.5;
    }

    @Override
    public String getItemName(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return "Emerald Blade";
        return use.name;
    }

    @Override
    public boolean isUnique(SkyblockItem use) {
        if (use == null)
            return true;
        return use.unique;
    }

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public byte getStars(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return 0;
        return use.itemModifier.getStars();
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
        if (use == null)
            return new SkyblockItemModifier();
        return use.itemModifier;
    }

    @Override
    public SItemType getItemType(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return SItemType.SWORD;
        return use.itemType;
    }

    @Override
    public boolean enchanted(SkyblockPlayer player, SkyblockItem use) {
        if (use == null)
            return true;
        return use.enchanted;
    }
}
