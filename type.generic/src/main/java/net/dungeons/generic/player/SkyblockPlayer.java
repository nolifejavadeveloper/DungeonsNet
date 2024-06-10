package net.dungeons.generic.player;

import lombok.Getter;
import lombok.Setter;
import net.dungeons.generic.level.SkyblockLevel;
import net.dungeons.generic.rank.Rank;
import net.dungeons.generic.skills.impl.*;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
@Getter
@Setter
public class SkyblockPlayer extends Player {
    private SkyblockLevel skyblockLevel;
    private Rank rank;
    private AlchemySkill alchemySkill;
    private CarpentrySkill carpentrySkill;
    private CombatSkill combatSkill;
    private DungeonSkill dungeonSkill;
    private EnchantingSkill enchantingSkill;
    private FarmingSkill farmingSkill;
    private FishingSkill fishingSkill;
    private ForagingSkill foragingSkill;
    private MiningSkill miningSkill;
    private TamingSkill tamingSkill;

    public SkyblockPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);


    }


    public void load() {

    }

    public void save() {

    }
}
