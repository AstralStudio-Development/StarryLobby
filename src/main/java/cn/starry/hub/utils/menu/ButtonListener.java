package cn.starry.hub.utils.menu;

import cn.starry.hub.StarryLobby;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.PlayerInventory;

/**
 * @Modifier: Stalyer
 */
public class ButtonListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onButtonPress(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }
        Menu openMenu = Menu.currentlyOpenedMenus.get(player.getName());

        if (openMenu != null) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK,1,1);
            if (event.getClickedInventory() instanceof PlayerInventory) {
                event.setCancelled(true);
            }

            openMenu.onClickEvent(event);
            if (event.getSlot() != event.getRawSlot()) {
                if ((event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
                    event.setCancelled(true);
                }
                return;
            }

            if (openMenu.getButtons().containsKey(event.getSlot())) {
                Button button = openMenu.getButtons().get(event.getSlot());
                boolean cancel = button.shouldCancel(player, event.getSlot(), event.getClick());

                if (!cancel && (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
                    event.setCancelled(true);
                    if (event.getCurrentItem() != null) {
                        player.getInventory().addItem(event.getCurrentItem());
                    }
                } else {
                    event.setCancelled(cancel);
                }

                button.clicked(player, event.getSlot(), event.getClick(), event.getHotbarButton(), event.getCurrentItem());

                if (Menu.currentlyOpenedMenus.containsKey(player.getName())) {
                    Menu newMenu = Menu.currentlyOpenedMenus.get(player.getName());

                    if (newMenu == openMenu) {
                        boolean buttonUpdate = button.shouldUpdate(player, event.getSlot(), event.getClick());
                        if (buttonUpdate) {
                            openMenu.setClosedByMenu(true);
                            newMenu.openMenu(player);
                        }
                    }
                } else if (button.shouldUpdate(player, event.getSlot(), event.getClick())) {
                    openMenu.setClosedByMenu(true);
                    openMenu.openMenu(player);
                }

                if (event.isCancelled()) {
                    Bukkit.getScheduler().runTaskLater(StarryLobby.getInstance(), player::updateInventory, 1L);
                }
            } else {
                if (event.getCurrentItem() != null) {
                    event.setCancelled(true);
                }
                if ((event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player)) {
            return;
        }
        Menu openMenu = Menu.currentlyOpenedMenus.get(player.getName());

        if (openMenu != null) {
            if (!openMenu.isClosedByMenu()) {
                openMenu.onClose(player);
                Menu.currentlyOpenedMenus.remove(player.getName());
            }
        }
    }
}
