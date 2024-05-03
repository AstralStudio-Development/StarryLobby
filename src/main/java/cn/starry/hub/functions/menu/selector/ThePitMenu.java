package cn.starry.hub.functions.menu.selector;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.BungeeUtil;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.menu.buttons.ThePitButtons;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

@AutoRegister
public class ThePitMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("             &0天坑乱斗-选区");

    public void openMenu(Player player,boolean isMenu) {
        this.init(player,isMenu);
        player.openInventory(this.inv);
    }

    public void init(Player player,boolean isMenu) {
        this.inv = Bukkit.createInventory(null, isMenu ? 36 : 27, title);

        //Settings Item
        //this.inv.setItem(11, new ThePitButtons().THEPIT1(player));
        //this.inv.setItem(13, new ThePitButtons().THEPIT2(player));
        //this.inv.setItem(15, new ThePitButtons().THEPIT3(player));

        this.inv.setItem(12, new ThePitButtons().NEWPIT(player));
        this.inv.setItem(14, new ThePitButtons().DEBUGPIT(player));

        if (isMenu) {
            this.inv.setItem(31, new ThePitButtons().Back());
            this.inv.setItem(32, new ThePitButtons().Info());
        }

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
        //Old ThePit
        if (e.getCurrentItem().equals(new ThePitButtons().THEPIT1(player))) {
            BungeeUtil.sendServer(player,"G_ThePit#1");
        }
        if (e.getCurrentItem().equals(new ThePitButtons().THEPIT2(player))) {
            BungeeUtil.sendServer(player,"G_ThePit#2");
        }
        if (e.getCurrentItem().equals(new ThePitButtons().THEPIT3(player))) {
            BungeeUtil.sendServer(player,"G_ThePit#3");
        }
        //New ThePit
        if (e.getCurrentItem().equals(new ThePitButtons().NEWPIT(player))) {
            BungeeUtil.sendServer(player,"G_ThePit#1");
        }
        if (e.getCurrentItem().equals(new ThePitButtons().DEBUGPIT(player))) {
            BungeeUtil.sendServer(player,"G_ThePit#2");
        }
        //
        if (e.getCurrentItem().equals(new ThePitButtons().Back())) {
            new SelectorMenu().openMenu(player);
        }
    }

}

