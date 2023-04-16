package io.github.itzispyder.ogredupealias.data.builder;

import io.github.itzispyder.ogredupealias.data.Direction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockPosMatcher {

    private final Location origin;
    private boolean acceptable;

    private BlockPosMatcher(Block block) {
        this.origin = block.getLocation();
        this.acceptable = true;
    }

    public BlockPosMatcher up(Material type) {
        return check(Direction.UP, type);
    }

    public BlockPosMatcher down(Material type) {
        return check(Direction.DOWN, type);
    }

    public BlockPosMatcher north(Material type) {
        return check(Direction.NORTH, type);
    }

    public BlockPosMatcher south(Material type) {
        return check(Direction.SOUTH, type);
    }

    public BlockPosMatcher east(Material type) {
        return check(Direction.EAST, type);
    }

    public BlockPosMatcher west(Material type) {
        return check(Direction.WEST, type);
    }

    public BlockPosMatcher center(Material type) {
        return check(Direction.CENTER, type);
    }

    public BlockPosMatcher check(Direction dir, Material type) {
        if (acceptable) {
            switch (dir) {
                case UP -> acceptable = origin.clone().add(0,1,0).getBlock().getType().equals(type);
                case DOWN -> acceptable = origin.clone().add(0,-1,0).getBlock().getType().equals(type);
                case NORTH -> acceptable = origin.clone().add(0,0,-1).getBlock().getType().equals(type);
                case SOUTH -> acceptable = origin.clone().add(0,0,1).getBlock().getType().equals(type);
                case EAST -> acceptable = origin.clone().add(1,0,0).getBlock().getType().equals(type);
                case WEST -> acceptable = origin.clone().add(-1,0,0).getBlock().getType().equals(type);
                case CENTER -> acceptable = origin.clone().add(0,0,0).getBlock().getType().equals(type);
            }
        }
        return this;
    }

    public BlockPosMatcher check(int relativeX, int relativeY, int relativeZ, Material type) {
        if (acceptable) {
            acceptable = origin.clone().add(relativeX,relativeY,relativeZ).getBlock().getType().equals(type);
        }
        return this;
    }

    public boolean isAcceptable() {
        return acceptable;
    }

    public static BlockPosMatcher of(Block block) {
        return new BlockPosMatcher(block);
    }
}
