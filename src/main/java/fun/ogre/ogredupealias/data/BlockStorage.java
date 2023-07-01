package fun.ogre.ogredupealias.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;

public class BlockStorage {
    private Material type;
    private BlockData data;
    private Location loc;
    private BlockState blockState;

    public BlockStorage(Block b) {
        type = b.getType();
        data = b.getBlockData();
        loc = b.getLocation();
        blockState = b.getState();
    }

    public Block toBlock() {
        return getLoc().getBlock();
    }
    public void restore() {
        Block b = toBlock();
        b.setType(this.getType());
        b.setBlockData(this.getData());
    }

    public Material getType() {
        return type;
    }

    public void setType(Material type) {
        this.type = type;
    }

    public BlockData getData() {
        return data;
    }

    public void setData(BlockData data) {
        this.data = data;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(BlockState blockState) {
        this.blockState = blockState;
    }
}
