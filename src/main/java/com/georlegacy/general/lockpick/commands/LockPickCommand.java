package com.georlegacy.general.lockpick.commands;

import com.georlegacy.general.lockpick.LockPick;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LockPickCommand implements CommandExecutor {
    private LockPick lp;
    public LockPickCommand(LockPick lp) {
        this.lp=lp;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length==0) {

        } else if (args[0].equalsIgnoreCase("give")) {
            if (args.length<3) {

            } else {
                if (Bukkit.getPlayer(args[1])==null) {

                } else if (!args[2].equalsIgnoreCase("MasterKey")&&!args[2].equalsIgnoreCase("LockPick")) {

                } else {
                    Player p = Bukkit.getPlayer(args[1]);
                    if (p.getInventory().firstEmpty()==-1) {

                    }
                    if (args[2].equalsIgnoreCase("MasterKey")) {
                        ItemStack key = new ItemStack(Material.TRIPWIRE_HOOK);
                        ItemMeta keymeta = key.getItemMeta();
                        keymeta.setLocalizedName("LOCKPICK_MASTER-KEY");
                        keymeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eMaster Key"));

                        List<String> lore = new ArrayList<String>();
                        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Left-Click to lock"));
                        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Right-Click to unlock"));
                        keymeta.setLore(lore);
                        key.setItemMeta(keymeta);
                        p.getInventory().addItem(key);

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eLockPick&7: &eYou have been given a &6Master Key&e."));
                    }
                    if (args[2].equalsIgnoreCase("LockPick")) {
                        ItemStack pick = new ItemStack(Material.STICK);
                        ItemMeta pickmeta = pick.getItemMeta();
                        pickmeta.setLocalizedName("LOCKPICK_LOCK-PICK");
                        pickmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eLock Pick"));

                        List<String> lore = new ArrayList<String>();
                        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Right-Click a door, trapdoor, or chest to try and pick the lock"));
                        lore.add(ChatColor.translateAlternateColorCodes('&', "&7This item doesn't always work"));
                        pickmeta.setLore(lore);
                        pick.setItemMeta(pickmeta);
                        p.getInventory().addItem(pick);

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eLockPick&7: &eYou have been given a &6Lock Pick&e."));
                    }
                }
            }
        } else if (args[0].equalsIgnoreCase("version")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&lLockPick &7&lon &8&lV1.0 &7&lby &8&l615283 &7&lwww.615283.net"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lockpick give <player> <LockPick|MasterKey>"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lockpick version"));
        }
        else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIncorrect arguments, see &4/lockpick version"));
        }
        return true;
    }
}
