package cn.starry.hub.functions;

import cn.starry.hub.Main;
import cn.starry.hub.api.data.CacheData;
import cn.starry.hub.api.enums.GameOwned;
import cn.starry.hub.functions.menu.buttons.CustomSlotButtons;
import cn.starry.hub.functions.menu.buttons.SelectorButtons;
import cn.starry.hub.functions.menu.selector.ThePitMenu;
import cn.starry.hub.functions.menu.slot.SlotMenu;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.BungeeUtil;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

@AutoRegister
public class CustomSlot implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Main main = Main.getInstance();
        Player player = (Player) e.getWhoClicked();
        UUID uuid = player.getUniqueId();
        ItemStack item = new CustomSlotButtons().Button(player);
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
            if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.BEDWARS))) {
                BungeeUtil.sendServer(player,"L_BedWarsLobby#1");
            }
            if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.MURDERMYSTERY))) {
                BungeeUtil.sendServer(player,"L_MurderMysteryLobby#1");
            }
            if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.DUEL))) {
                BungeeUtil.sendServer(player,"G_Duels");
            }
            if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.SKYWARS))) {
                BungeeUtil.sendServer(player,"G_SkyWars");
            }
            if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.THEPIT))) {
                new ThePitMenu().openMenu(player,true);
            }
            if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.RPG))) {
                BungeeUtil.sendServer(player,"G_TourP");
            }
            if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.ARCADE))) {
                BungeeUtil.sendServer(player,"L_ArcadeLobby#1");
            }
            //
        }

    }

    public static void updateSlot(Player player) {
        Main main = Main.getInstance();
        UUID uuid = player.getUniqueId();
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
            ItemStack item = new CustomSlotButtons().Button(player);
            Inventory inventory = player.getInventory();
            inventory.setItem(9, main.getData().getSlotData(uuid, "SLOT1").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getData().getSlotData(uuid, "SLOT1"))));
            inventory.setItem(10, main.getData().getSlotData(uuid, "SLOT2").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getData().getSlotData(uuid, "SLOT2"))));
            inventory.setItem(11, main.getData().getSlotData(uuid, "SLOT3").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getData().getSlotData(uuid, "SLOT3"))));
            inventory.setItem(12, main.getData().getSlotData(uuid, "SLOT4").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getData().getSlotData(uuid, "SLOT4"))));
            inventory.setItem(13, main.getData().getSlotData(uuid, "SLOT5").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getData().getSlotData(uuid, "SLOT5"))));
            inventory.setItem(14, main.getData().getSlotData(uuid, "SLOT6").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getData().getSlotData(uuid, "SLOT6"))));
            inventory.setItem(15, main.getData().getSlotData(uuid, "SLOT7").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getData().getSlotData(uuid, "SLOT7"))));
            inventory.setItem(16, main.getData().getSlotData(uuid, "SLOT8").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getData().getSlotData(uuid, "SLOT8"))));
            inventory.setItem(17, main.getData().getSlotData(uuid, "SLOT9").equalsIgnoreCase("NONE") ? item : new SelectorButtons().Button(player, GameOwned.valueOf(main.getData().getSlotData(uuid, "SLOT9"))));
        }
    }

    public static void resetSlot(Player player) {
        UUID uuid = player.getUniqueId();
        Main.getInstance().getData().updateSlotData(uuid, "SLOT1", "NONE");
        Main.getInstance().getData().updateSlotData(uuid, "SLOT2", "NONE");
        Main.getInstance().getData().updateSlotData(uuid, "SLOT3", "NONE");
        Main.getInstance().getData().updateSlotData(uuid, "SLOT4", "NONE");
        Main.getInstance().getData().updateSlotData(uuid, "SLOT5", "NONE");
        Main.getInstance().getData().updateSlotData(uuid, "SLOT6", "NONE");
        Main.getInstance().getData().updateSlotData(uuid, "SLOT7", "NONE");
        Main.getInstance().getData().updateSlotData(uuid, "SLOT8", "NONE");
        Main.getInstance().getData().updateSlotData(uuid, "SLOT9", "NONE");
        updateSlot(player);
    }

}
