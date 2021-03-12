package fr.romaindev.itemslegs.items;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public abstract class ItemHandler {

    public abstract void onBreakBlock(Player player, Block block, CustomItem item);

    public abstract void onInteractEvent(Player player, Action action, Block clickedBlock, BlockFace face, CustomItem item);
}
