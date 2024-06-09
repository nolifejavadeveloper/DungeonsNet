package net.dungeons.generic.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferredCustomization {
    private char plusColor = 'c';
    private char bracketColor = '6';

    public PreferredCustomization(char plusColor) {
        this.plusColor = plusColor;
    }

    public PreferredCustomization(char plusColor, char bracketColor) {
        this.plusColor = plusColor;
        this.bracketColor = bracketColor;
    }

}
