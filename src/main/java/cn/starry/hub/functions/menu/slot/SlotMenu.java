package cn.starry.hub.functions.menu.slot;

import cn.starry.hub.api.data.CacheData;
import cn.starry.hub.api.enums.GameOwned;
import cn.starry.hub.functions.menu.buttons.CustomSlotButtons;
import cn.starry.hub.functions.menu.buttons.SelectorButtons;
import cn.starry.hub.functions.menu.selector.SelectorMenu;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import cn.starry.hub.functions.CustomSlot;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

@AutoRegister
public class SlotMenu implements Listener {

    private Inventory inv;
    String title = ColorUtil.color("              &0选择菜单图标");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 54, title);

        //Game Buttons
        this.inv.setItem(10, new SelectorButtons().Button(player, GameOwned.BEDWARS));
        this.inv.setItem(12, new SelectorButtons().Button(player, GameOwned.RPG));
        this.inv.setItem(14, new SelectorButtons().Button(player, GameOwned.SKYWARS));
        this.inv.setItem(15, new SelectorButtons().Button(player, GameOwned.THEPIT));
        this.inv.setItem(20, new SelectorButtons().Button(player, GameOwned.MURDERMYSTERY));
        this.inv.setItem(21, new SelectorButtons().Button(player, GameOwned.DUEL));
        this.inv.setItem(24, new SelectorButtons().Button(player, GameOwned.ARCADE));

        this.inv.setItem(49, new CustomSlotButtons().Back());
        this.inv.setItem(50, new CustomSlotButtons().Reset());

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
        if (!e.getInventory().getName().equals(title)) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getInventory().getName().equals(title)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        // Buttons
        if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.BEDWARS))) {
            Main.getInstance().getData().updateSlotData(uuid, CacheData.CUSTOMSLOT.get(player), "BEDWARS");
            CustomSlot.updateSlot(player);
            new SelectorMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.MURDERMYSTERY))) {
            Main.getInstance().getData().updateSlotData(uuid, CacheData.CUSTOMSLOT.get(player), "MURDERMYSTERY");
            CustomSlot.updateSlot(player);
            new SelectorMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.DUEL))) {
            Main.getInstance().getData().updateSlotData(uuid, CacheData.CUSTOMSLOT.get(player), "DUEL");
            CustomSlot.updateSlot(player);
            new SelectorMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.SKYWARS))) {
            Main.getInstance().getData().updateSlotData(uuid, CacheData.CUSTOMSLOT.get(player), "SKYWARS");
            CustomSlot.updateSlot(player);
            new SelectorMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.THEPIT))) {
            Main.getInstance().getData().updateSlotData(uuid, CacheData.CUSTOMSLOT.get(player), "THEPIT");
            CustomSlot.updateSlot(player);
            new SelectorMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.RPG))) {
            Main.getInstance().getData().updateSlotData(uuid, CacheData.CUSTOMSLOT.get(player), "RPG");
            CustomSlot.updateSlot(player);
            new SelectorMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.ARCADE))) {
            Main.getInstance().getData().updateSlotData(uuid, CacheData.CUSTOMSLOT.get(player), "ARCADE");
            CustomSlot.updateSlot(player);
            new SelectorMenu().openMenu(player);
        }
        //
        if (e.getCurrentItem().equals(new CustomSlotButtons().Back())) {
            new SelectorMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new CustomSlotButtons().Reset())) {
            player.closeInventory();
            player.sendMessage(ColorUtil.color("&a你的自定义槽位已被重置！"));
            player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
            CustomSlot.resetSlot(player);
        }
    }

}

