package fr.romaindev.itemslegs.items;

import fr.romaindev.itemslegs.utils.NBTUtils;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class CustomItem {

    private String itemName;
    private Material baseItem;
    private ItemHandler handler;
    private Map<Enchantment, Integer> baseEnchantments;

    private int customID;

    public CustomItem(String itemName, Material baseItem, ItemHandler handler, Map<Enchantment, Integer> baseEnchantments, int customID) {
        this.itemName = itemName;
        this.baseItem = baseItem;
        this.handler = handler;
        this.baseEnchantments = baseEnchantments;
        this.customID = customID;
    }

    /**
     * Convert this a custom item into a bukkit itemstack
     * @param count the number of item you want
     * @return an {@link ItemStack} instance
     */
    public ItemStack buildItemStack(int count){
        ItemStack stack = new ItemStack(baseItem, count);
        stack = applyCustomItemTag(stack, this.customID);
        stack.addEnchantments(this.baseEnchantments);

        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(this.itemName);
        stack.setItemMeta(meta);

        return stack;
    }

    /**
     * This methods is used to turn any item into a custom item tag
     * @return new item stack with customItemID tag set to id
     */
    private ItemStack applyCustomItemTag(ItemStack stack, int id){
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInt("customItemID", id);

        stack = NBTUtils.setTag(stack, nbt);
        return stack;
    }

    public String getItemName() {
        return itemName;
    }

    public Material getBaseItem() {
        return baseItem;
    }

    public ItemHandler getHandler() {
        return handler;
    }

    public Map<Enchantment, Integer> getBaseEnchantments() {
        return baseEnchantments;
    }

    public int getCustomID() {
        return customID;
    }
}
