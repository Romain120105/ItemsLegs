package fr.romaindev.itemslegs;

import fr.romaindev.itemslegs.items.CustomItem;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class CustomItemsEvent implements Listener {

    /**
     * Used to detect when a custom item is used to break a block
     * @param event
     */
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        System.out.println("onBreak()");
        Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();

        if(ItemFactory.isCustomItem(heldItem)){
            CustomItem item = ItemFactory.getCustomItemFromStack(heldItem);
            item.getHandler().onBreakBlock(player, block, item);
        }
    }

    /**
     * Used to detect when a custom item is used to interact with a block
     * @param event
     */
    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        BlockFace face = event.getBlockFace();
        Action action = event.getAction();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if(event.getHand() != null && event.getHand().equals(EquipmentSlot.HAND)) {
            if (ItemFactory.isCustomItem(heldItem)) {
                CustomItem item = ItemFactory.getCustomItemFromStack(heldItem);
                item.getHandler().onInteractEvent(player, action, clickedBlock, face, item);
            }
        }
    }

}
