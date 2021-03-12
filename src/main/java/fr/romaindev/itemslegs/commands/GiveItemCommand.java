package fr.romaindev.itemslegs.commands;

import fr.romaindev.itemslegs.ItemFactory;
import fr.romaindev.itemslegs.items.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length >= 1){
                String customItemName = args[0];
                CustomItem item = ItemFactory.getItemByName(customItemName);

                if(item != null){
                    player.getInventory().addItem(item.buildItemStack(1));
                    return true;
                }else{
                    player.sendMessage(ChatColor.RED+"There is no item with such name!");
                    return false;
                }
            }else{
                player.sendMessage(ChatColor.RED+"Not enought arguments!");
                return false;
            }
        }
        sender.sendMessage(ChatColor.RED+"Only a player can execute this command!");
        return false;
    }
}
