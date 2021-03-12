package fr.romaindev.itemslegs.items;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * This item has 5% of luck to generate sugar items (wheat, potatoes and beetroot) when using the hoe
 */
public class ItemMagicHoe extends ItemHandler{

    private final ItemStack[] SUGAR_ITEMS = {new ItemStack(Material.WHEAT), new ItemStack(Material.POTATO_ITEM), new ItemStack(Material.BEETROOT)};

    @Override
    public void onBreakBlock(Player player, Block block, CustomItem item) {
        if(isBlockSeed(block)){
            int random = new Random().nextInt(20);
            if(random == 1){
                player.getWorld().dropItem(block.getLocation(), genRandomSugarItem());
            }

        }
    }

    @Override
    public void onInteractEvent(Player player, Action action, Block clickedBlock, BlockFace face, CustomItem item) {

    }

    private ItemStack genRandomSugarItem() {
        int randomInt = new Random().nextInt(SUGAR_ITEMS.length);
        return SUGAR_ITEMS[randomInt];
    }

    private boolean isBlockSeed(Block block){
        return block.getType() == Material.CARROT ||block.getType() == Material.CROPS || block.getType() == Material.BEETROOT_BLOCK;
    }

}
