package fr.romaindev.itemslegs;

import com.google.common.base.Preconditions;
import fr.romaindev.itemslegs.items.CustomItem;
import fr.romaindev.itemslegs.items.ItemHandler;
import fr.romaindev.itemslegs.utils.CustomEnchantment;
import fr.romaindev.itemslegs.utils.NBTUtils;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemFactory {

    /**
     * Local map used to contains every custom items registered
     */
    private static final Map<Integer, CustomItem> customItemMap = new HashMap<>();

    /**
     * Last used ID
     */
    private static int lastID = -1;

    /**
     * Register custom items in the plugin
     * @param customItem the {@link CustomItem} to register
     */
    public static void registerCustomItem(CustomItem customItem){
        Preconditions.checkNotNull(customItem);

        int itemID = customItem.getCustomID();

        // Checks if an item with this name or id is already registered
        if(customItemMap.containsKey(itemID) || getItemByName(customItem.getItemName()) != null){
            System.err.println("Can't add the item " + customItem.getItemName() + "#" + customItem.getCustomID() + " because it is already registered.");
            return;
        }

        customItemMap.put(itemID, customItem);
    }

    /**
     * Gets a custom item by a specified name
     * @param name the item name
     * @return a {@link CustomItem} if presents or null if not
     */
    public static CustomItem getItemByName(String name){
        for(Map.Entry<Integer, CustomItem> entry : customItemMap.entrySet()){
            System.out.println(entry.getValue().getItemName());
            if(entry.getValue().getItemName().equalsIgnoreCase(name)){
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Gets a custom item by a specified id
     * @param id the item id
     * @return a {@link CustomItem} if presents or null if not
     */
    public static CustomItem getItemById(int id){
        if(customItemMap.containsKey(id)){
            return customItemMap.get(id);
        }
        return null;
    }

    /**
     * This method generate a free item id which can be used to register new custom item
     * @return a new free id
     */
    public static int findCustomItemId(){
        lastID+=1;
        return lastID;
    }

    /**
     * Checks if a itemstack is a custom item
     * @param itemStack specified {@link ItemStack} instance
     * @return either true if it's a custom item or false if not
     */
    public static boolean isCustomItem(ItemStack itemStack) {
        return getCustomItemFromStack(itemStack) != null;
    }

    /**
     * Converts bukkit itemstack into custom item instance
     * @param itemStack {@link ItemStack} instance
     * @return {@link CustomItem} instance
     */
    public static CustomItem getCustomItemFromStack(ItemStack itemStack){
        if(NBTUtils.hasTag(itemStack)){

            NBTTagCompound compound = NBTUtils.getTag(itemStack);
            if(compound.hasKey("customItemID")){
                int customItemID = compound.getInt("customItemID");
                CustomItem customItem = getItemById(customItemID);
                if(customItem == null){
                    return null;
                }else{
                    return customItem;
                }
            }
        }
        return null;
    }

    /**
     * This methods unregister every custom items by clearing {@code customItemMap}
     */
    public static void unregisterItems() {
        customItemMap.clear();
    }



    /**
     * Use this builder to create {@link CustomItem} instance
     */
    static class Builder {
        private String itemName;
        private Material baseItem;
        private ItemHandler handler;
        private Map<Enchantment, Integer> baseEnchantments;

        /**
         * Define the custom item name used in the tools.json for name and descriptions
         * @param name the name for this custom item
         * @return a reference to this {@code Builder} used to create {@link CustomItem} instance
         */
        public Builder name(String name) {
            this.itemName = name;
            return this;
        }

        /**
         * Define the base material for this custom item
         * @param baseItem the base {@link Material}
         * @return a reference to this {@code Builder} used to create {@link CustomItem} instance
         */
        public Builder baseItem(Material baseItem) {
            this.baseItem = baseItem;
            return this;
        }

        /**
         * Define the base enchantments presents by default when this item is gived
         * @param baseEnchantments a list of {@link Enchantment}
         * @return a reference to this {@code Builder} used to create {@link CustomItem} instance
         */
        public Builder baseEnchantment(List<CustomEnchantment> baseEnchantments) {
            Map<Enchantment, Integer> enchantments = new HashMap<>();

            for(CustomEnchantment enchantment : baseEnchantments){
                enchantments.put(enchantment.getEnchantment(), enchantment.getEnchantmentID());
            }
            this.baseEnchantments = enchantments;
            System.out.println("base enchantments: " + this.baseEnchantments);
            return this;
        }

        /**
         * Define this item new handler used to handle every item's events
         * @param handler an instance of a {@link ItemHandler}
         * @return a reference to this {@code Builder} used to create {@link CustomItem} instance
         */
        public Builder handler(ItemHandler handler) {
            this.handler = handler;
            return this;
        }

        public CustomItem build() {
            return new CustomItem(itemName, baseItem, handler, baseEnchantments, ItemFactory.findCustomItemId());
        }
    }

}
