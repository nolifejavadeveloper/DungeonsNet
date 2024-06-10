package net.dungeons.generic.skills.impl;


import net.dungeons.generic.player.SkyblockPlayer;
import net.dungeons.generic.skills.Skill;

public class EnchantingSkill extends Skill {
    public EnchantingSkill(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public String getSkillName() {
        return "Mining";
    }

    @Override
    public String getKey() {
        return "enchanting";
    }

    @Override
    public int getMaxLevel() {
        return 60;
    }

    @Override
    public int getLevelFromExperience(double experience) {
        //TODO: do this
        return 0;
    }
}
