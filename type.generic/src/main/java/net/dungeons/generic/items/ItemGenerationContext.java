package net.dungeons.generic.items;

import lombok.AllArgsConstructor;
import net.dungeons.generic.player.SkyblockPlayer;

@AllArgsConstructor
public class ItemGenerationContext {
    public SkyblockPlayer player;
    public SkyblockItem instance;
}
