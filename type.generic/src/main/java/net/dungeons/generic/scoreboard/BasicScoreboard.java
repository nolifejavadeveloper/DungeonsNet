package net.dungeons.generic.scoreboard;

import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.util.Stringify;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class BasicScoreboard extends SkyblockScoreboard {
    public BasicScoreboard(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return getScoreboardDisplayName();
    }

    public Component getScoreboardDisplayName() {
        String baseText = "SKYBLOCK";
        String[] colors = {"§f§l", "§6§l", "§e§l"};
        String endColor = "§a§l";
        //String endText = isGuest ? " GUEST" : "";
        String endText = "";

        int counter = (int) (this.getPlayer().getAliveTicks() % 100) / 2;

        if (counter > 0 && counter <= 8) {
            return Component.text(colors[0] + baseText.substring(0, counter - 1) +
                    colors[1] + baseText.charAt(counter - 1) +
                    colors[2] + baseText.substring(counter) +
                    endColor + endText);
        } else if ((counter >= 9 && counter <= 19) ||
                (counter >= 25 && counter <= 29)) {
            return Component.text(colors[0] + baseText + endColor + endText);
        } else {
            return Component.text(colors[2] + baseText + endColor + endText);
        }
    }

    @Override
    public void update() {
        addLine(15,"&7 ");
        addLine(14, "&7X: " + this.getPlayer().getPosition().x());
        addLine(13, "&fHi mom!");
    }

    @Override
    public @NotNull Set<@NotNull Player> getViewers() {
        return Set.of();
    }
}
