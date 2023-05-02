package fun.ogre.ogredupealias.data;

import fun.ogre.ogredupealias.data.builder.BlockPosMatcher;
import org.bukkit.Material;
import org.bukkit.block.Block;

public abstract class PlacedStructures {

    public static boolean isCustomTable(Block block) {
        return BlockPosMatcher.of(block)
                .center(Material.DROPPER)
                .check(0,2,0, Material.SPRUCE_TRAPDOOR)
                .up(Material.GLASS)
                .west(Material.LEVER)
                .east(Material.LEVER)
                .north(Material.LEVER)
                .south(Material.LEVER)
                .isAcceptable();
    }
}
