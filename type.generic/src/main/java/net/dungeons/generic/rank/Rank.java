package net.dungeons.generic.rank;

import lombok.Getter;
import net.dungeons.generic.player.PreferredCustomization;
import net.dungeons.generic.player.SkyblockPlayer;

public enum Rank {
    DEFAULT("&7", '7', 1),
    VIP("&a[VIP] ", 'a', 2),
    VIP_PLUS("&a[VIP&6+&a] ", 'a', 3),
    MVP("&b[MVP] ", 'b', 4),
    MVP_PLUS("&b[MVP&%s+&b] ", 'b', 5),
    MVP_PLUS_PLUS("&%s[MVP&%s++&%s] ", '6', 6),
    YOUTUBE("&c[&fYOUTUBE&c] ", 'c', 7),
    MOD("&2[MOD] ", '2', 50),
    ADMIN("&c[ADMIN] ", 'c', 100),
    OWNER("&c[OWNER] ", 'c', 999);

    private final String display;
    @Getter
    private final char c;
    @Getter
    private final int permissionLevel;

    Rank(String display, char c, int permissionLevel) {
        this.display = display;
        this.c = c;
        this.permissionLevel = permissionLevel;
    }

    public String getDisplay(PreferredCustomization customization) {
        if (customization == null)
            return display;

        if (this == Rank.MVP_PLUS_PLUS) {
            return String.format(display, customization.getBracketColor(), customization.getPlusColor(), customization.getBracketColor());
        }

        return String.format(display, customization.getPlusColor());
    }

    public char getChatColor()
    {
        if (this == DEFAULT)
            return '7';
        return 'f';
    }

    public boolean hasPermission(Rank rank)
    {
        return this.permissionLevel >= rank.permissionLevel;
    }
}
