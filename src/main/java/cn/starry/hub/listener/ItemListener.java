package cn.starry.hub.listener;

import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.functions.menu.selector.SelectorMenu;
import cn.starry.hub.functions.menu.settings.SettingsMenu;
import cn.starry.hub.functions.menu.store.legacy.StoreMenu;
import cn.starry.hub.listener.handler.ItemHandler;
import dev.jnic.annotations.Include;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@Include
public class ItemListener implements Listener {

    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack Item = player.getEquipment().getItemInHand();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            //player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
            if (Item.equals(ItemHandler.getItem(player,0))) {
                new SelectorMenu().openMenu(player);
            } else if (Item.getType().equals(Material.PLAYER_HEAD) && Item.getItemMeta().getDisplayName().equalsIgnoreCase(ItemHandler.getItem(player,2).getItemMeta().getDisplayName())) {
                new PlayerProfileMenu().openMenu(player);
            } else if (Item.equals(ItemHandler.getItem(player,1))) {
                new StoreMenu().openMenu(player);
            } else if (Item.equals(ItemHandler.getItem(player,6))) {
                //new PrototypeMenu().openMenu(player);
            } else if (Item.getType().equals(Material.CHEST) && Item.getItemMeta().getDisplayName().equalsIgnoreCase(ItemHandler.getItem(player,3).getItemMeta().getDisplayName())) {
                Bukkit.dispatchCommand(player,"gmenu main");
            } else if (Item.equals(ItemHandler.getItem(player,4))) {
                new SettingsMenu(null,0).openMenu(player);
            } else if (Item.equals(ItemHandler.getItem(player,5))) {
                Bukkit.dispatchCommand(player, "shop");
            }
        }
    }
}
