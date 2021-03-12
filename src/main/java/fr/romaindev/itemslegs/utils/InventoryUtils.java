package fr.romaindev.itemslegs.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryUtils {

    /**
     * Remove one item of the specified type and amount.
     * Need to check if the item is present before using this
     * @param inv the player inventory
     * @param type the type of item you want to remove
     * @param amount the amount of items you want to remove
     */
    public static void removeInventoryItems(PlayerInventory inv, Material type, int amount) {
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getType() == type) {
                int newamount = is.getAmount() - amount;
                if (newamount > 0) {
                    is.setAmount(newamount);
                    break;
                } else {
                    inv.remove(is);
                    amount = -newamount;
                    if (amount == 0) break;
                }
            }
        }
    }

}
