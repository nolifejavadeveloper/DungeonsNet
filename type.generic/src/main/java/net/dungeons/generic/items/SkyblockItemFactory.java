package net.dungeons.generic.items;

import com.google.gson.JsonObject;
import net.dungeons.generic.gemstone.GemstoneSlot;
import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.reforge.IReforge;
import net.dungeons.generic.stats.Stat;
import net.dungeons.generic.util.Stringify;
import net.dungeons.generic.world.SkyblockLocation;
import net.kyori.adventure.text.Component;
import net.minestom.server.component.DataComponent;
import net.minestom.server.component.DataComponentMap;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.item.ItemComponent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.component.HeadProfile;
import net.minestom.server.item.component.Unbreakable;
import net.minestom.server.utils.Unit;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SkyblockItemFactory {
    public static SkyblockItem createInstance(SItem sItem, SkyblockPlayer player) {
        SkyblockItem item = new SkyblockItem(sItem.getMaterial(player, null), sItem.getCount(player, null), DataComponentMap.EMPTY);

        item.stats = sItem.getStats(player, null);
        item.dungeonized = sItem.isDungeonized(player, null);
        item.rarity = sItem.getItemRarity(player, null);
        item.texture = sItem.getTexture(player, null);
        item.material = sItem.getMaterial(player, null);
        item.count = sItem.getCount(player, null);
        item.reforge = sItem.getReforge(player, null);
        item.itemId = sItem.getItemID(player, null);
        item.name = sItem.getItemName(player, null);
        item.baseItem = sItem;
        item.unique = sItem.isUnique(null);
        item.uuid = sItem.getUUID();
        item.enchantments = sItem.getEnchantments(player, null);
        item.gemstoneSlots = sItem.getGemstoneSlots(player, null);
        item.itemModifier = sItem.getItemModifier(player, null);
        item.itemType = sItem.getItemType(player, null);
        item.enchanted = sItem.enchanted(player, null);
        item.color = sItem.leatherColor(player, null);

        return item;
    }

    public static DataComponentMap generateComponentMap(SkyblockItem item, SkyblockPlayer player) {
        DataComponentMap.Builder map = DataComponentMap.builder();

        ItemGenerationContext context = new ItemGenerationContext(player, item);

        map.set(ItemComponent.CUSTOM_NAME, createName(context));
        map.set(ItemComponent.LORE, createLore(context));
        map.set(ItemComponent.UNBREAKABLE, Unbreakable.DEFAULT);
        map.set(ItemComponent.HIDE_ADDITIONAL_TOOLTIP, Unit.INSTANCE);

        if (item.getMaterial(player, item) == Material.PLAYER_HEAD && !item.getTexture(player, item).equals(""))
        {
            JsonObject json = new JsonObject();
            json.addProperty("isPublic", true);
            json.addProperty("signatureRequired", false);

            JsonObject object = new JsonObject();
            object.addProperty("url", "http://textures.minecraft.net/texture/" + item.getTexture(player, item));
            JsonObject obj = new JsonObject();
            obj.addProperty("model", "slim");
            object.add("metadata", obj);

            JsonObject t = new JsonObject();
            t.add("SKIN", object);

            json.add("textures", t);

            String texturedEncoded = Base64.getEncoder().encodeToString(json.toString().getBytes());

            map.set(ItemComponent.PROFILE, new HeadProfile(new PlayerSkin(texturedEncoded, null)));
        }

        if (item.leatherColor(player, item) != null)
        {
            map.set(ItemComponent.DYED_COLOR, item.leatherColor(player, item));
        }

        return map.build();
    }





    public static Component addRarityLine(ItemGenerationContext context) {
        String line = "";

        SkyblockItem item = context.instance;
        SkyblockPlayer player = context.player;

        ItemRarity rarity = item.getItemRarity(player, item);
        SItemType type = item.getItemType(player, item);

        line += "&" + rarity.color;

        SkyblockItemModifier modifiers = item.getItemModifier(player, item);

        if (modifiers.isRecombobulated())
            line += "&kD&r &" + rarity.color;

        line += "&l" + rarity.name;

        if (item.isDungeonized(player, item))
            line += " DUNGEONIZED";
        line += " " + type.name();

        if (modifiers.isRecombobulated())
            line += " &r&" + rarity.color + "&kD";

        return Stringify.create(line);
    }

    public static Component addGemstoneLine(ItemGenerationContext context)
    {
        SkyblockItem instance = context.instance;
        SkyblockPlayer player = context.player;

        List<GemstoneSlot> slots = instance.getGemstoneSlots(player, null);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < slots.size(); i++)
        {
            GemstoneSlot slot = slots.get(i);

            if (slot.gemstone == null)
            {
                builder.append("&7[" + slot.type.icon + "] ");
                continue;
            }

            builder.append("&" + slot.gemstone.getQuality().color + "[&" + slot.gemstone.getType().color + slot.type.icon + "&" + slot.gemstone.getQuality().color + "] ");
        }

        return Component.text(Stringify.formatString(builder.toString()));
    }

    public static List<Component> createLore(ItemGenerationContext context)
    {
        SkyblockItem instance = context.instance;
        SkyblockPlayer player = context.player;
        List<Component> components = new ArrayList<>();

        if (instance.isDungeonized(player, null))
        {
            components.add(Stringify.create("&6Gear Score: &d" + GearScore.getGearScore(instance, player)));
        }

        components.addAll(createStatLore(context));
        if (!instance.getGemstoneSlots(player, instance).equals(List.of()))
            components.add(addGemstoneLine(context));
        components.add(Stringify.blankLine);

        if (!instance.getDescription(player, instance).equals(List.of()))
        {
            components.addAll(createDescriptionLore(context));
            components.add(Stringify.blankLine);
        }

        components.add(addRarityLine(context));

        return components;
    }

    public static List<Component> createDescriptionLore(ItemGenerationContext context)
    {
        SkyblockItem item = context.instance;
        SkyblockPlayer player = context.player;

        List<String> description = item.getDescription(player, item);
        List<Component> comps = new ArrayList<>(description.size());

        for (int i = 0; i < description.size(); i++)
        {
            comps.add(Component.text(description.get(i)));
        }

        return comps;
    }

    public static List<Component> createStatLore(ItemGenerationContext context)
    {
        SkyblockItem instance = context.instance;
        SkyblockPlayer player = context.player;
        List<Component> components = new ArrayList<>();

        boolean inDungeon = player.getLocation() == SkyblockLocation.DUNGEON;

        for (Stat stat : Stat.values())
        {
            if (instance.getStat(stat, player, instance) != 0)
                components.add(createStatLine(stat, instance, player, inDungeon));
        }

        return components;
    }

    public static Component createStatLine(Stat stat, SkyblockItem instance, SkyblockPlayer player, boolean inDungeon)
    {
        StringBuilder builder = new StringBuilder();

        double value = instance.getStat(stat, player, null);
        double boost = 315;

        builder.append("&7" + stat.name + ": " + (stat.isRed ? "&c" : "&a") + (value > 0 ? "+" : "-") + String.format("%.1f", value) + (stat.percent ? "%" : ""));

        if (instance.getItemModifier(player, instance).getHotPotatoBooks() > 0) {
            switch (instance.itemType)
            {
                case SWORD:
                    if (stat == Stat.DAMAGE || stat == Stat.STRENGTH)
                        builder.append(" &e(+" + (instance.getItemModifier(player, instance).getHotPotatoBooks() * 2) + ")");
                    break;
                case HELMET:
                case CHESTPLATE:
                case LEGGINGS:
                case BOOTS:
                    if (stat == Stat.HEALTH || stat == Stat.DEFENSE)
                        builder.append(" &e(+" + (instance.getItemModifier(player, instance).getHotPotatoBooks() * (stat == Stat.HEALTH ? 4 : 2)) + ")");
                    break;
            }
        }

        if (instance.getItemModifier(player, instance).isArtOfWar())
        {
            builder.append(" &6[+5]");
        }

        if (!inDungeon && instance.isDungeonized(player, instance))
        {
            builder.append(" &7(" + (value > 0 ? "+" : "-") + String.format("%.2f", value*(boost/100)) + (stat.percent ? "%" : "") + ")");
        }

        if (instance.getItemModifier(player, instance).isArtOfPeace() && stat == Stat.HEALTH)
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

    public static Component createName(ItemGenerationContext context) {
        SkyblockItem item = context.instance;

        String name = Stringify.formatString("&" + item.getItemRarity(context.player, item).color);

        if (item.getReforge(context.player, context.instance) != null)
        {
            name += item.getReforge(context.player, context.instance).getReforgeName() + " ";
        }

        name += item.getItemName(context.player, context.instance);

        //stars
        if (context.instance.getItemModifier(context.player, item).stars >= 1 && context.instance.isDungeonized(context.player, null))
        {
            name += " " + StarService.getStarString(context.instance.getItemModifier(context.player, item).stars);
        }

        return Component.text(name);
    }

    public static SkyblockItem convertNonToSkyblock(ItemStack c, SkyblockPlayer player)
    {
        return SkyblockItemFactory.createInstance(SkyblockItemRegistry.get(c.material().name()), player);
    }
}
