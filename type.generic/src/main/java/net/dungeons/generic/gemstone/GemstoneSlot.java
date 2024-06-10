package net.dungeons.generic.gemstone;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

public class GemstoneSlot {
    public GemstoneSlotType type;
    public Gemstone gemstone;
    public GemstoneSlot(GemstoneSlotType type, @Nullable Gemstone applied) {
        this.type = type;
        this.gemstone = applied;
    }

    public boolean isApplicable(Gemstone gemstone) {
        return this.type.isApplicable(gemstone);
    }

}