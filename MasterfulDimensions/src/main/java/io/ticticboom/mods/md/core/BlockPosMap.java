package io.ticticboom.mods.md.core;

import net.minecraft.core.BlockPos;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

public class BlockPosMap<VAL> implements Map<BlockPos, VAL> {
    private final int ySize;
    private final int xSize;
    private final int zSize;
    private final Function<BlockPos, VAL> valueFactory;
    public final VAL[][][] data;

    protected BlockPosMap(int xSize, int ySize, int zSize, Function<BlockPos, VAL> valueFactory) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;
        this.valueFactory = valueFactory;
        data = (VAL[][][]) new Object[ySize][xSize][zSize];
    }

    protected void init() {
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                for (int z = 0; z < zSize; z++) {
                    data[y][x][z] = valueFactory.apply(new BlockPos(x, y, z));
                }
            }
        }
    }

    @Override
    public int size() {
        return ySize * xSize * zSize;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof BlockPos bp)) {
            return false;
        }
        if ((bp.getX() > xSize || bp.getX() < 0)
                && (bp.getY() > ySize || bp.getY() < 0)
                && (bp.getZ() > zSize || bp.getZ() < 0)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                for (int z = 0; z < zSize; z++) {
                    if (ObjectUtils.equals(data[y][x][z], value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public VAL get(Object key) {
        if (!(key instanceof BlockPos bp)) {
            return null;
        }
        return data[bp.getY()][bp.getX()][bp.getZ()];
    }

    @Nullable
    @Override
    public VAL put(BlockPos key, VAL value) {
        var bp = key;
        if ((bp.getX() > xSize || bp.getX() < 0)
                && (bp.getY() > ySize || bp.getY() < 0)
                && (bp.getZ() > zSize || bp.getZ() < 0)) {
            return null;
        }
        var old = data[bp.getY()][bp.getX()][bp.getZ()];
        data[bp.getY()][bp.getX()][bp.getZ()] = value;
        return old;
    }

    @Override
    public VAL remove(Object key) {
        if (!(key instanceof BlockPos bp)) {
            return null;
        }
        var old = data[bp.getY()][bp.getX()][bp.getZ()];
        data[bp.getY()][bp.getX()][bp.getZ()] = null;
        return old;
    }

    @Override
    public void putAll(@NotNull Map<? extends BlockPos, ? extends VAL> m) {
        for (var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                for (int z = 0; z < zSize; z++) {
                    data[y][x][z] = null;
                }
            }
        }
    }

    @NotNull
    @Override
    public Set<BlockPos> keySet() {
        var keys = new HashSet<BlockPos>();
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                for (int z = 0; z < zSize; z++) {
                    keys.add(new BlockPos(x, y, z));
                }
            }
        }
        return keys;
    }

    @NotNull
    @Override
    public Collection<VAL> values() {
        var values = new ArrayList<VAL>();
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                for (int z = 0; z < zSize; z++) {
                    values.add(data[y][x][z]);
                }
            }
        }
        return values;
    }

    @NotNull
    @Override
    public Set<Map.Entry<BlockPos, VAL>> entrySet() {
        var entries = new HashSet<Map.Entry<BlockPos, VAL>>();
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                for (int z = 0; z < zSize; z++) {
                    entries.add(new Entry<VAL>(new BlockPos(x, y, z), data[y][x][z]));
                }
            }
        }
        return entries;
    }

    public static final class Entry<VAL> implements Map.Entry<BlockPos, VAL> {

        public BlockPos key;
        public VAL value;

        public Entry(BlockPos key, VAL value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public BlockPos getKey() {
            return key;
        }

        @Override
        public VAL getValue() {
            return value;
        }

        @Override
        public VAL setValue(VAL value) {
            return this.value = value;
        }
    }
}
