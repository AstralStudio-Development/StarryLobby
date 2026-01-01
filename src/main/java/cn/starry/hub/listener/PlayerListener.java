package cn.starry.hub.listener;

import cn.starry.core.api.data.CacheData;
import cn.starry.core.utils.chat.CC;
import dev.jnic.annotations.Include;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Date: 2023/06/14
 */
@Include
public class PlayerListener implements Listener {

    private List<Material> blockTypes = new ArrayList<>();

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (CacheData.EDIT.get(player) == false) {
            player.sendMessage(CC.translate("&c你不被允许破坏方块"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (CacheData.EDIT.get(player) == false) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.sendMessage(CC.translate("&c你不被允许丢弃物品"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickUpItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        blockTypes.add(Material.LEGACY_WORKBENCH);
        blockTypes.add(Material.FURNACE);
        blockTypes.add(Material.LEGACY_ENCHANTMENT_TABLE);
        blockTypes.add(Material.ANVIL);
        blockTypes.add(Material.CHEST);
        blockTypes.add(Material.ENDER_CHEST);
        blockTypes.add(Material.LEGACY_SOIL);
        blockTypes.add(Material.ARMOR_STAND);
        //YumeGames
        blockTypes.add(Material.LEGACY_BED);
        blockTypes.add(Material.LEGACY_BED_BLOCK);
        blockTypes.add(Material.BREWING_STAND);
        blockTypes.add(Material.HOPPER);
        blockTypes.add(Material.DAYLIGHT_DETECTOR);

        if (clickedBlock != null && blockTypes.contains(clickedBlock.getType()) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.PHYSICAL)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

}
