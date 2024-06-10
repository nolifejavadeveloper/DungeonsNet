package net.dungeons.generic.enchant;

public abstract class Enchantment {
    private int level;

    public abstract int getMaxLevel();
    public abstract EnchantmentType getEnchantmentType();
    public abstract EnchantModifierType getModifierType();

    public Enchantment(int level)
    {
        this.level = Math.max(level, getMaxLevel());
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
}
