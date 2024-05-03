package cn.starry.hub.listener;

import cn.starry.hub.Main;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.functions.menu.profile.PrototypeMenu;
import cn.starry.hub.functions.menu.selector.SelectorMenu;
import cn.starry.hub.functions.menu.profile.settings.LobbySettingsMenu;
import cn.starry.hub.functions.menu.store.StoreMenu;
import cn.starry.hub.listener.handler.ItemHandler;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.skywars.proxy.inventory.skywars.MainMenu;
import com.andrei1058.bedwars.proxy.addon.BedWarsShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.yuzegod.megawallslobby.inventory.InventoryManager;

@AutoRegister
public class ItemListener implements Listener {

    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack Item = player.getEquipment().getItemInHand();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Item.equals(ItemHandler.getItem(player,0))) {
                if (Main.getPlugin(Main.class).getConfig().getBoolean("beta") == false) {
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
                    new SelectorMenu().openMenu(player);
                } else {
                    Bukkit.dispatchCommand(player,Main.getPlugin(Main.class).getConfig().getString("command.selector"));
                }
            }
            else if (Item.equals(ItemHandler.getItem(player,2))) {
                if (Main.getPlugin(Main.class).getConfig().getBoolean("beta") == false) {
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN,1,1);
                    new PlayerProfileMenu().openMenu(player);
                } else {
                    Bukkit.dispatchCommand(player,Main.getPlugin(Main.class).getConfig().getString("command.profile"));
                }
            }
            else if (Item.equals(ItemHandler.getItem(player,1))) {
                if (Main.getPlugin(Main.class).getConfig().getBoolean("beta") == false) {
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN,1,1);
                    new StoreMenu().openMenu(player);
                } else {
                    Bukkit.dispatchCommand(player,Main.getPlugin(Main.class).getConfig().getString("command.shop"));
                }
            }
            else if (Item.equals(ItemHandler.getItem(player,6))) {
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN,1,1);
                new PrototypeMenu().openMenu(player);
            }
            else if (Item.equals(ItemHandler.getItem(player,3))) {
                Bukkit.dispatchCommand(player,"gmenu main");
            }
            else if (Item.equals(ItemHandler.getItem(player,4))) {
                if (Main.getPlugin(Main.class).getConfig().getBoolean("beta") == false) {
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN,1,1);
                    new LobbySettingsMenu().openMenu(player,false);
                } else {
                    Bukkit.dispatchCommand(player,Main.getPlugin(Main.class).getConfig().getString("command.settings"));
                }
            } else if (Item.equals(ItemHandler.getItem(player,5))) {
                if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("SkyWars")) {
                    new MainMenu(player).open();
                } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("MegaWalls")) {
                    InventoryManager.SHOPMENU.open(player);
                } else if (Main.getInstance().getConfig().getString("type").equalsIgnoreCase("BedWars")) {
                    new BedWarsShopMenu().openMenu(player);
                } else {
                    Bukkit.dispatchCommand(player, "cosmetics menu");
                }
            }
        }
    }
}
