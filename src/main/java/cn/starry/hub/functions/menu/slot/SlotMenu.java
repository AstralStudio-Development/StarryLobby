package cn.starry.hub.functions.menu.slot;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.GameOwned;
import cn.starry.hub.functions.CustomSlot;
import cn.starry.hub.functions.menu.buttons.CustomSlotButtons;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.selector.SelectorMenu;
import cn.starry.hub.functions.menu.selector.button.GameItemButton;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;


public class SlotMenu implements Listener {

    private Inventory inv;
    String title = CC.translate("              &0选择菜单图标");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 45, title);

        //Game Buttons

        this.inv.setItem(40, new CustomSlotButtons().Back());
        this.inv.setItem(41, new CustomSlotButtons().Reset());

        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (!e.getView().getTitle().equals(title)) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getView().getTitle().equals(title)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        if (e.getCurrentItem().equals(new CustomSlotButtons().Back())) {
            new SelectorMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new CustomSlotButtons().Reset())) {
            player.closeInventory();
            player.sendMessage(CC.translate("&a你的自定义槽位已被重置！"));
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
            CustomSlot.resetSlot(player);
        }

    }

}

