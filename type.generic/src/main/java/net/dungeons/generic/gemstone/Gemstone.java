package net.dungeons.generic.gemstone;

public class Gemstone {
    private GemstoneQuality quality;
    private GemstoneType type;
    public Gemstone(GemstoneQuality quality, GemstoneType type) {
        this.quality = quality;
        this.type = type;
    }

    public GemstoneQuality getQuality() {
        return this.quality;
    }

    public void setQuality(GemstoneQuality quality) {
        this.quality = quality;
    }

    public GemstoneType getType() {
        return this.type;
    }

    public void setType(GemstoneType type) {
        this.type = type;
    }

    public double getBoost() {
        return type.getStatBonusByQuality(quality);
    }
}
