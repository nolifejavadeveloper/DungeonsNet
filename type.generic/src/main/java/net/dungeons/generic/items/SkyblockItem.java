package net.dungeons.generic.items;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.minestom.server.component.DataComponent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SkyblockItem implements ItemStack, SItem {

    @Override
    public @NotNull Material material() {
        return null;
    }

    @Override
    public int amount() {
        return 0;
    }

    @Override
    public @NotNull ItemStack with(@NotNull Consumer<Builder> consumer) {
        return null;
    }

    @Override
    public @NotNull ItemStack withMaterial(@NotNull Material material) {
        return null;
    }

    @Override
    public @NotNull ItemStack withAmount(int i) {
        return null;
    }

    @Override
    public @NotNull <T> ItemStack with(@NotNull DataComponent<T> dataComponent, @NotNull T t) {
        return null;
    }

    @Override
    public @NotNull ItemStack without(@NotNull DataComponent<?> dataComponent) {
        return null;
    }

    @Override
    public @NotNull ItemStack consume(int i) {
        return null;
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack itemStack) {
        return false;
    }

    @Override
    public @NotNull CompoundBinaryTag toItemNBT() {
        return (CompoundBinaryTag) NBT_TYPE.write(this);
    }

    @Override
    public boolean has(@NotNull DataComponent<?> dataComponent) {
        return false;
    }

    @Override
    public <T> @Nullable T get(@NotNull DataComponent<T> dataComponent) {

    }
}
