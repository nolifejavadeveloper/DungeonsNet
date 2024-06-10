package net.dungeons.generic.player;

import lombok.Getter;
import lombok.Setter;
import net.dungeons.generic.util.IStorable;
import org.bson.Document;

@Getter
@Setter
public class PreferredCustomization implements IStorable {
    private char plusColor = 'c';
    private char bracketColor = '6';

    public PreferredCustomization(char plusColor) {
        this.plusColor = plusColor;
    }

    public PreferredCustomization(char plusColor, char bracketColor) {
        this.plusColor = plusColor;
        this.bracketColor = bracketColor;
    }

    @Override
    public void save(Document doc) {
        doc.put("plusColor", plusColor);
        doc.put("bracketColor", bracketColor);
    }

    @Override
    public void load(Document doc) {
        plusColor = ((String) doc.get("plusColor")).charAt(0);
        bracketColor = ((String) doc.get("bracketColor")).charAt(0);
    }
}
