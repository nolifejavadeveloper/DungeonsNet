package net.dungeons.generic.items;

import net.dungeons.generic.gemstone.GemstoneSlot;
import net.dungeons.generic.util.Stringify;
import net.kyori.adventure.text.Component;
import net.minestom.server.component.DataComponentMap;
import net.minestom.server.item.ItemComponent;

public class SkyblockItemFactory {





    public DataComponentMap generateComponentMap(SkyblockItem item) {
        DataComponentMap.Builder map = DataComponentMap.builder();

        ItemComponent.LORE
    }





    public static Component addRarityLine(ItemGenerationContext context) {
        return Component.text(Stringify.formatString("&dTEST"));
    }

    public static Component addGemstoneLine(ItemGenerationContext context) {
        SItemInstance instance = context.instance;
        DungeonsPlayer player = context.player;

        List<GemstoneSlot> slots = instance.get(player, null);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < slots.size(); i++)
        {
            GemstoneSlot slot = slots.get(i);

            if (slot.getGemstone() == null)
            {
                builder.append("&7[" + slot.getSlotType().icon + "] ");
                continue;
            }

            builder.append("&" + slot.getGemstone().getQuality().color + "[&" + slot.getGemstone().getType().color + slot.getSlotType().icon + "&" + slot.getGemstone().getQuality().color + "] ");
        }

        return Component.text(Stringify.formatString(builder.toString()));
    }

    public static List<Component> createLore(ItemGenerationContext context)
    {
        SItemInstance instance = context.instance;
        DungeonsPlayer player = context.player;
        List<Component> components = new ArrayList<>();

        if (instance.isDungeonized(player, null))
        {
            components.add(Stringify.create("&6Gear Score: &d" + GearScore.getGearScore(instance, player)));
        }

        components.addAll(createStatLore(context));

        components.add(addGemstoneLine(context));
        components.add(Component.newline());
        components.add(addRarityLine(context));

        return components;
    }

    public static List<Component> createStatLore(ItemGenerationContext context)
    {
        SItemInstance instance = context.instance;
        DungeonsPlayer player = context.player;
        List<Component> components = new ArrayList<>();

        boolean inDungeon = player.getLocation() == SLocation.DUNGEON;

        for (Stat stat : Stat.values())
        {
            if (instance.getStat(stat, player, instance) != 0)
                components.add(createStatLine(stat, instance, player, inDungeon));
        }

        return components;
    }

    public static Component createStatLine(Stat stat, SItemInstance instance, DungeonsPlayer player, boolean inDungeon)
    {
        StringBuilder builder = new StringBuilder();

        double value = instance.getStat(stat, player, null);
        double boost = 315;

        builder.append("&7" + stat.name + ": " + (stat.isRed ? "&c" : "&a") + (value > 0 ? "+" : "-") + String.format("%.2f", value) + (stat.percent ? "%" : ""));

        if (instance.itemModifier.hotPotatoBooks > 0) {
            switch (instance.itemType)
            {
                case SWORD:
                    if (stat == Stat.DAMAGE || stat == Stat.STRENGTH)
                        builder.append(" &e(+" + (instance.itemModifier.hotPotatoBooks * 2) + ")");
                    break;
                case HELMET:
                case CHEST_PLATE:
                case LEGGINGS:
                case BOOTS:
                    if (stat == Stat.HEALTH || stat == Stat.DEFENSE)
                        builder.append(" &e(+" + (instance.itemModifier.hotPotatoBooks * (stat == Stat.HEALTH ? 4 : 2)) + ")");
                    break;
            }
        }

        if (instance.itemModifier.artOfWar)
        {
            builder.append(" &6[+5]");
        }

        if (!inDungeon)
        {
            builder.append(" &7(" + (value > 0 ? "+" : "-") + String.format("%.2f", value*(boost/100)) + (stat.percent ? "%" : "") + ")");
        }

        if (instance.itemModifier.artOfPeace && stat == Stat.HEALTH)
        {
            builder.append(" &4[+40]");
        }

        IReforge reforge = instance.getReforge(player, null);

        if (reforge != null)
        {
            double statValue = reforge.getStats(player, instance).getStat(stat);

            if (statValue != 0)
                builder.append(" &9(" + (statValue > 0 ? "+" : "-") + String.format("%.2f", statValue) + ")");
        }

        List<GemstoneSlot> slots = instance.getGemstoneSlots(player, null);

        double gemstoneValue = 0;

        for (int i = 0; i < slots.size(); i++)
        {
            GemstoneSlot slot = slots.get(i);

            if (slot == null || slot.getGemstone() == null || slot.getGemstone().getType().isCorrectStat(stat))
                continue;

            gemstoneValue += slot.getGemstone().getBoost();
        }

        if (gemstoneValue != 0)
        {
            builder.append(" &d(+" + gemstoneValue + ")");
        }

        return Component.text(Stringify.formatString(builder.toString()));
    }

    public static String createName(ItemGenerationContext context) {
        SkyblockItem item = context.instance;

        String name = Stringify.formatString("&" + item.getItemRarity(context.player, item).color);

        if (item.reforge != null)
        {
            name += item.reforge.getReforgeName() + " ";
        }

        name += item.getItemName(context.player, context.instance);

        //stars
        if (context.instance.stars >= 1 && context.instance.isDungeonized(context.player, null))
        {
            name += " " + StarService.getStarString(item.stars);
        }

        return name;
    }
}
