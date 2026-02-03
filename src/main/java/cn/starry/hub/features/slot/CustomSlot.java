package cn.starry.hub.features.slot;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.menus.slot.SlotMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class CustomSlot implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        StarryLobby main = StarryLobby.getInstance();
        Player player = (Player) e.getWhoClicked();
        UUID uuid = player.getUniqueId();
        //ItemStack item = new CustomSlotItemButton().getButtonItem(player);
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (!e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getClick().isRightClick()) {
            if (e.getSlot() == 9) {
                CacheData.CUSTOMSLOT.put(player, "SLOT1");
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                new SlotMenu().openMenu(player);
            }
            if (e.getSlot() == 10) {
                CacheData.CUSTOMSLOT.put(player, "SLOT2");
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                new SlotMenu().openMenu(player);
            }
            if (e.getSlot() == 11) {
                CacheData.CUSTOMSLOT.put(player, "SLOT3");
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                new SlotMenu().openMenu(player);
            }
            if (e.getSlot() == 12) {
                CacheData.CUSTOMSLOT.put(player, "SLOT4");
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                new SlotMenu().openMenu(player);
            }
            if (e.getSlot() == 13) {
                CacheData.CUSTOMSLOT.put(player, "SLOT5");
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                new SlotMenu().openMenu(player);
            }
            if (e.getSlot() == 14) {
                CacheData.CUSTOMSLOT.put(player, "SLOT6");
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                new SlotMenu().openMenu(player);
            }
            if (e.getSlot() == 15) {
                CacheData.CUSTOMSLOT.put(player, "SLOT7");
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                new SlotMenu().openMenu(player);
            }
            if (e.getSlot() == 16) {
                CacheData.CUSTOMSLOT.put(player, "SLOT8");
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                new SlotMenu().openMenu(player);
            }
            if (e.getSlot() == 17) {
                CacheData.CUSTOMSLOT.put(player, "SLOT9");
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                new SlotMenu().openMenu(player);
            }
        }
        if (e.getClick().isLeftClick()) {
            // Buttons
            //
        }

    }

    /*
    public static void updateSlot(Player player) {
        Core main = Core.getInstance();
        UUID uuid = player.getUniqueId();
        if (!Objects.equals(StarryLobby.getInstance().getConfig().getString("type"), "Login")) {
            player.getInventory().setItem(26, new GadgetsSlotButtons().Info(player));
            ItemStack item = new CustomSlotButtons().Button(player);
            Inventory inventory = player.getInventory();
            inventory.setItem(9, main.getMongoDB().getSlotData(uuid, "SLOT1").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getMongoDB().getSlotData(uuid, "SLOT1"))));
            inventory.setItem(10, main.getMongoDB().getSlotData(uuid, "SLOT2").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getMongoDB().getSlotData(uuid, "SLOT2"))));
            inventory.setItem(11, main.getMongoDB().getSlotData(uuid, "SLOT3").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getMongoDB().getSlotData(uuid, "SLOT3"))));
            inventory.setItem(12, main.getMongoDB().getSlotData(uuid, "SLOT4").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getMongoDB().getSlotData(uuid, "SLOT4"))));
            inventory.setItem(13, main.getMongoDB().getSlotData(uuid, "SLOT5").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getMongoDB().getSlotData(uuid, "SLOT5"))));
            inventory.setItem(14, main.getMongoDB().getSlotData(uuid, "SLOT6").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getMongoDB().getSlotData(uuid, "SLOT6"))));
            inventory.setItem(15, main.getMongoDB().getSlotData(uuid, "SLOT7").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getMongoDB().getSlotData(uuid, "SLOT7"))));
            inventory.setItem(16, main.getMongoDB().getSlotData(uuid, "SLOT8").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getMongoDB().getSlotData(uuid, "SLOT8"))));
            inventory.setItem(17, main.getMongoDB().getSlotData(uuid, "SLOT9").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getMongoDB().getSlotData(uuid, "SLOT9"))));
        }
    }

     */

    public static void resetSlot(Player player) {
        Core main = Core.getInstance();
        UUID uuid = player.getUniqueId();
        main.getMongoDB().updateSlotData(uuid, "SLOT1", "NONE");
        main.getMongoDB().updateSlotData(uuid, "SLOT2", "NONE");
        main.getMongoDB().updateSlotData(uuid, "SLOT3", "NONE");
        main.getMongoDB().updateSlotData(uuid, "SLOT4", "NONE");
        main.getMongoDB().updateSlotData(uuid, "SLOT5", "NONE");
        main.getMongoDB().updateSlotData(uuid, "SLOT6", "NONE");
        main.getMongoDB().updateSlotData(uuid, "SLOT7", "NONE");
        main.getMongoDB().updateSlotData(uuid, "SLOT8", "NONE");
        main.getMongoDB().updateSlotData(uuid, "SLOT9", "NONE");
        //updateSlot(player);
    }

}

