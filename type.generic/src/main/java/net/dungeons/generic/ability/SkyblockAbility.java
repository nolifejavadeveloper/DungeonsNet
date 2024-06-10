package net.dungeons.generic.ability;

import net.dungeons.generic.items.SkyblockItem;
import net.dungeons.generic.player.SkyblockPlayer;

public interface SkyblockAbility {
    public ActionType getActionType();
    public ActionModifier getActionModifier();
    public void execute(SkyblockPlayer player, SkyblockItem item);
}
