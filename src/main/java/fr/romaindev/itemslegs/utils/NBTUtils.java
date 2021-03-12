package fr.romaindev.itemslegs.utils;

import net.minecraft.server.v1_12_R1.Items;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTUtils {

    /**
     * Check is the itemstack has nbt tag
     * @param stack a {@link ItemStack} instance
     * @return if the itemstack has nbt tag
     */
    public static boolean hasTag(ItemStack stack){
        return getBukkitStack(stack).hasTag();
    }

    /**
     * Get itemstack nbt tag
     * @param itemStack {@link ItemStack} specified itemstack
     * @return nbt tag {@link NBTTagCompound}
     */
    public static NBTTagCompound getTag(ItemStack itemStack){
        net.minecraft.server.v1_12_R1.ItemStack stack = getBukkitStack(itemStack);
        return stack.getTag();
    }

    public static ItemStack setTag(ItemStack itemStack, NBTTagCompound compound){
        net.minecraft.server.v1_12_R1.ItemStack stack = getBukkitStack(itemStack);
        stack.setTag(compound);
        return CraftItemStack.asBukkitCopy(stack);
    }

    /**
     * Convert spigot itemstack to nms one
     * @param stack spigot itemstack
     * @return nms itemstack
     */
    public static net.minecraft.server.v1_12_R1.ItemStack getBukkitStack(ItemStack stack){
        return CraftItemStack.asNMSCopy(stack);
    }

}
