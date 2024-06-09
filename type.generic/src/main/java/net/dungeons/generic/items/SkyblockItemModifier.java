package net.dungeons.generic.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkyblockItemModifier {
    public int hotPotatoBooks;
    public boolean artOfPeace;
    public boolean artOfWar;
    public boolean bookOfStats;
    public int stars;
    public boolean recombobulated;


    public SkyblockItemModifier copy() {
        return new SkyblockItemModifier(hotPotatoBooks, artOfPeace, artOfWar, bookOfStats, stars, recombobulated);
    }
}
