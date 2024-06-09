package net.dungeons.generic.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkyblockItemBookModifier {
    public int hotPotatoBooks;
    public boolean artOfPeace;
    public boolean artOfWar;
    public boolean bookOfStats;


    public SkyblockItemBookModifier copy() {
        return new SkyblockItemBookModifier(hotPotatoBooks, artOfPeace, artOfWar, bookOfStats);
    }
}
