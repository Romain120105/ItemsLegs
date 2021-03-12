package fr.romaindev.itemslegs.items;

import fr.romaindev.itemslegs.utils.InventoryUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This hoe breaks and plant seeds, beetroots and carrots in 3x3
 */
public class ItemFarmerHoe extends ItemHandler{

    @Override
    public void onBreakBlock(Player player, Block block, CustomItem item) {
        if(isBlockSeed(block)){
            for(Block seed :getBlocksInThree(block)){
                if(isBlockSeed(seed)){
                    seed.breakNaturally();
                }
            }
        }
    }

    @Override
    public void onInteractEvent(Player player, Action action, Block clickedBlock, BlockFace face, CustomItem item) {
        if(action == Action.RIGHT_CLICK_BLOCK){
            if(clickedBlock.getType() == Material.DIRT || clickedBlock.getType() == Material.GRASS){
                for(Block dirt : getBlocksInThree(clickedBlock)){
                    if(dirt.getType() == Material.DIRT || dirt.getType() == Material.GRASS){
                        dirt.setType(Material.SOIL);
                    }
                }
            }else if(clickedBlock.getType() == Material.SOIL){
                int freeSpace =0;
                for(Block soil : getBlocksInThree(clickedBlock)){
                    if(soil.getType() == Material.SOIL){
                        Block blockLocation = player.getWorld().getBlockAt(soil.getLocation().add(0, 1, 0));
                        if(blockLocation.getType() == Material.AIR){
                            if(hasSeed(player)){
                                InventoryUtils.removeInventoryItems(player.getInventory(), Material.SEEDS, 1);
                                blockLocation.setType(Material.CROPS);
                            }else if(hasCarrots(player)){
                                InventoryUtils.removeInventoryItems(player.getInventory(), Material.CARROT_ITEM, 1);
                                blockLocation.setType(Material.CARROT);
                            }else if(hasBeetroot(player)){
                                InventoryUtils.removeInventoryItems(player.getInventory(), Material.BEETROOT_SEEDS, 1);
                                blockLocation.setType(Material.BEETROOT_BLOCK);
                            }
                            freeSpace += 1;
                        }
                    }
                }
            }
        }
    }

    public List<Block> getBlocksInThree(Block origin){
        List<Block> blocksInRange = new ArrayList<Block>();

        blocksInRange.add(origin.getRelative(BlockFace.SOUTH));
        blocksInRange.add(origin.getRelative(BlockFace.SOUTH_WEST));
        blocksInRange.add(origin.getRelative(BlockFace.WEST));
        blocksInRange.add(origin.getRelative(BlockFace.NORTH_WEST));
        blocksInRange.add(origin.getRelative(BlockFace.NORTH));
        blocksInRange.add(origin.getRelative(BlockFace.NORTH_EAST));
        blocksInRange.add(origin.getRelative(BlockFace.EAST));
        blocksInRange.add(origin.getRelative(BlockFace.SOUTH_EAST));
        blocksInRange.add(origin);

        return blocksInRange;
    }

    private boolean hasSeed(Player player){
        Inventory inventory = player.getInventory();
        return inventory.contains(Material.SEEDS);
    }

    private boolean hasCarrots(Player player){
        Inventory inventory = player.getInventory();
        return inventory.contains(Material.CARROT_ITEM);
    }

    private boolean hasBeetroot(Player player){
        Inventory inventory = player.getInventory();
        return inventory.contains(Material.BEETROOT_SEEDS);
    }

    private boolean isBlockSeed(Block block){
        return block.getType() == Material.CARROT ||block.getType() == Material.CROPS || block.getType() == Material.BEETROOT_BLOCK;
    }
}
