package net.dungeons.generic.enchant.impl;

import net.dungeons.generic.enchant.EnchantModifierType;
import net.dungeons.generic.enchant.Enchantment;
import net.dungeons.generic.enchant.EnchantmentType;
import net.dungeons.generic.enchant.IDamageEnchant;
import net.dungeons.generic.items.SkyblockItem;
import net.dungeons.generic.player.SkyblockPlayer;

public class SharpnessEnchant extends Enchantment implements IDamageEnchant {
    public SharpnessEnchant(int level)
    {
        super(level);
    }

    @Override
    public boolean isMultiplicative()
    {
        return false;
    }

    @Override
    public double getModifier(SkyblockPlayer player, SkyblockItem item) {
        return 15 * this.getLevel();
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @Override
    public EnchantmentType getEnchantmentType() {
        return EnchantmentType.NORMAL;
    }

    @Override
    public EnchantModifierType getModifierType() {
        return EnchantModifierType.DAMAGE;
    }
}
