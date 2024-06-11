package net.dungeons.generic.messages;

import net.dungeons.generic.util.Stringify;
import net.kyori.adventure.text.Component;


public class Messages {

    public static String NO_PERMISSION = "&cYou do not have permission to perform this.";
    public static String PLAYER_NOT_FOUND = "&cPlayer not found!";

    public static Component get(String s) {
        return Stringify.create(s);
    }

}
