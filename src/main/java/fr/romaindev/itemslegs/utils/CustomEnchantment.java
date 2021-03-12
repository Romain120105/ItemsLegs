package fr.romaindev.itemslegs.utils;

import org.bukkit.enchantments.Enchantment;

public class CustomEnchantment {

    private Enchantment enchantment;
    private int enchantmentID;

    public CustomEnchantment(Enchantment enchantment, int enchantmentID) {
        this.enchantment = enchantment;
        this.enchantmentID = enchantmentID;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public int getEnchantmentID() {
        return enchantmentID;
    }
}
