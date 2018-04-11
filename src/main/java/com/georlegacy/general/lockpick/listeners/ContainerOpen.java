package com.georlegacy.general.lockpick.listeners;

import com.georlegacy.general.lockpick.LockPick;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ContainerOpen implements Listener {
    private LockPick lp;
    public ContainerOpen(LockPick lp) {
        this.lp = lp;
    }

    @EventHandler
    public void onOpen(PlayerInteractEvent e) {
        if (!e.hasBlock()) return;
        if (e.isCancelled()) return;
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().equals(null)) return;
        if (e.getClickedBlock().getType().equals(Material.AIR)) return;

        if (e.getItem()==null) {
            if (lp.getContainerState().locked(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ())) {
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis chest is locked! Use a &4LockPick &cto unlock it."));
                e.setCancelled(true);
                return;
            } else return;
        }

        if (e.getItem().getItemMeta()==null) {
            if (lp.getContainerState().locked(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ())) {
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis chest is locked! Use a &4LockPick &cto unlock it."));
                e.setCancelled(true);
                return;
            } else return;
        }

        if (e.getItem().getItemMeta().getLocalizedName()==null) {
            if (lp.getContainerState().locked(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ())) {
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis chest is locked! Use a &4LockPick &cto unlock it."));
                e.setCancelled(true);
                return;
            } else return;
        }

        Block b = e.getClickedBlock();
        if (
                b.getType().equals(Material.CHEST)||
                b.getType().equals(Material.TRAP_DOOR)||
                b.getType().equals(Material.DARK_OAK_DOOR)||
                b.getType().equals(Material.ACACIA_DOOR)||
                b.getType().equals(Material.BIRCH_DOOR)||
                b.getType().equals(Material.JUNGLE_DOOR)||
                b.getType().equals(Material.SPRUCE_DOOR)||
                b.getType().equals(Material.WOOD_DOOR)||
                b.getType().equals(Material.WOODEN_DOOR)
            )
        {
            if (e.getItem().getItemMeta().getLocalizedName().equals("LOCKPICK_MASTER-KEY")) {
                if (!lp.getContainerState().locked(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ())) {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 0);
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eLockPick&7: &6The container at &eX:" + e.getClickedBlock().getLocation().getBlockX() + " Y:" + e.getClickedBlock().getLocation().getBlockY() + " Z:" + e.getClickedBlock().getLocation().getBlockZ() + " &6has been locked."));
                    lp.getContainerLock().lock(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ());
                    e.setCancelled(true);
                } else {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PAINTING_PLACE, 1, 0);
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eLockPick&7: &6The container at &eX:" + e.getClickedBlock().getLocation().getBlockX() + " Y:" + e.getClickedBlock().getLocation().getBlockY() + " Z:" + e.getClickedBlock().getLocation().getBlockZ() + " &6has been unlocked."));
                    lp.getContainerLock().unlock(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ());
                    e.setCancelled(true);
                }
            } else if (e.getItem().getItemMeta().getLocalizedName().equals("LOCKPICK_LOCK-PICK")) {
                if (lp.getContainerState().locked(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ())) {
                    double r = Math.random();
                    if (r < lp.getConfigUtil().getChance()) {
                        e.getItem().setAmount(e.getItem().getAmount()-1);
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_DIAMOND, 1 , 0);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou unlocked the container with your &2LockPick&a."));
                        lp.getContainerLock().unlock(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ());
                    } else {
                        e.setCancelled(true);
                        e.getItem().setAmount(e.getItem().getAmount()-1);
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 1 , 0);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYour &4LockPick &cbroke! Find another one and try again."));
                    }
                } else return;
            } else {
                if (lp.getContainerState().locked(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ())) {
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis chest is locked! Use a &4LockPick &cto unlock it."));
                    e.setCancelled(true);
                } else return;
            }
        } else return;
    }

}
