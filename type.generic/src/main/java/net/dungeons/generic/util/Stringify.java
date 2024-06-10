package net.dungeons.generic.util;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class Stringify {
    public static String formatString(String str)
    {
        return str.replaceAll("&", "§");
    }

    public static List<String> createLore(String... lore)
    {
        List<String> list = new ArrayList<>(lore.length);

        for (String str : lore)
        {
            list.add(formatString(str));
        }

        return list;
    }

    public static final Component blankLine = Component.text("§7");

    public static Component create(String line)
    {
        return Component.text(formatString(line));
    }
    public static List<Component> createList(String... lore) {
        List<Component> list = new ArrayList<>(lore.length);
        
        for (String str : lore) {
            list.add(Component.text(formatString(str)));
        }

        return list;
    }

    public static String toCamelCase(String s){
        String[] parts = s.split("_");
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    public static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }
}

