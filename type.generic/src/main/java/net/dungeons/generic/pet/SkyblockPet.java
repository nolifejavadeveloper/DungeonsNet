package net.dungeons.generic.pet;

import net.dungeons.generic.GenericSkyblockLoader;
import net.dungeons.generic.items.ItemRarity;
import net.dungeons.generic.items.SkyblockItem;
import net.dungeons.generic.stats.SkyblockStats;
import net.dungeons.generic.util.IStorable;
import org.bson.Document;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import java.util.HashMap;

public abstract class SkyblockPet {
    public abstract SkyblockStats getStats();
    public abstract int getMaxLevel();
    public abstract double getNextLevelTotalExp();
    public abstract double getMaxLevelTotalExp();
    public abstract int getLevel();
    public abstract SkyblockItem getAsItem();
    public abstract double getExperienceToLevel(int level);

    //
    private String name;
    private double exp;
    private ItemRarity rarity;

    public SkyblockPet(String name, double exp, ItemRarity rarity) {
        this.name = name;
        this.exp = exp;
        this.rarity = rarity;
    }

    public Document petToDocument()
    {
        Document doc = new Document();

        doc.put("xp", this.exp);
        doc.put("rarity", this.rarity);
        doc.put("petClass", this.getClass().getSimpleName());

        return doc;
    }

    public static SkyblockPet petFromDocument(Document document)
    {
        try
        {
            double xp = document.get("xp", 0);
            ItemRarity rarity = ItemRarity.valueOf(document.get("rarity", "COMMON"));

            String key = (String) document.get("petClass");
            Class<?> clasz = CLASS_MAP.get(key);

            return (SkyblockPet) clasz.getEnclosingConstructor().newInstance(xp, rarity);
        }
        catch (Exception e)
        {
            Logger.info("Error loading pet!!!");
        }

        return null;
    }

    private static final HashMap<String, Class<? extends SkyblockPet>> CLASS_MAP = new HashMap<>();

    public static void init()
    {
        new Reflections("net.dungeons.generic.pets.impl")
                .getSubTypesOf(SkyblockPet.class)
                .forEach(c -> {
                    CLASS_MAP.put(c.getSimpleName(), c);
                });
    }
}
