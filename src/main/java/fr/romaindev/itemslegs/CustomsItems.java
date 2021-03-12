package fr.romaindev.itemslegs;

import fr.romaindev.itemslegs.items.CustomItem;
import fr.romaindev.itemslegs.items.ItemFarmerHoe;
import fr.romaindev.itemslegs.items.ItemMagicHoe;
import fr.romaindev.itemslegs.utils.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.Arrays;

public class CustomsItems {

    public static final CustomItem FARMER_HOE = new ItemFactory.Builder().name("farmer_hoe").handler(new ItemFarmerHoe()).baseItem(Material.DIAMOND_HOE).baseEnchantment(Arrays.asList(new CustomEnchantment(Enchantment.MENDING, 1))).build();
    public static final CustomItem MAGIC_HOE = new ItemFactory.Builder().name("magic_hoe").handler(new ItemMagicHoe()).baseItem(Material.DIAMOND_HOE).baseEnchantment(Arrays.asList(new CustomEnchantment(Enchantment.MENDING, 1))).build();

    /**
     * This method is used to register every customs items
     */
    public static void registerItems(){
        ItemFactory.registerCustomItem(FARMER_HOE);
        ItemFactory.registerCustomItem(MAGIC_HOE);
    }


}
