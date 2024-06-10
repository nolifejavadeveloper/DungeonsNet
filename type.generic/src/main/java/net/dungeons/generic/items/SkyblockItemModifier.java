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
    public byte stars;
    public boolean recombobulated;
    public int silex;
    public byte scrolls;


    public SkyblockItemModifier copy() {
        return new SkyblockItemModifier(hotPotatoBooks, artOfPeace, artOfWar, bookOfStats, stars, recombobulated, silex, scrolls);
    }
}
