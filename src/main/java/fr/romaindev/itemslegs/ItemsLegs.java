package fr.romaindev.itemslegs;

import fr.romaindev.itemslegs.commands.GiveItemCommand;
import fr.romaindev.itemslegs.items.ItemFarmerHoe;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class ItemsLegs extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("giveitem").setExecutor(new GiveItemCommand());
        this.getServer().getPluginManager().registerEvents(new CustomItemsEvent(), this);
        //Register every of our items
        CustomsItems.registerItems();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        //Unregister every of our items
        ItemFactory.unregisterItems();
        super.onDisable();
    }
}
