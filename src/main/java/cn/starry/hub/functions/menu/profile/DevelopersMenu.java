package cn.starry.hub.functions.menu.profile;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.menu.buttons.DevelopersButtons;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

@AutoRegister
public class DevelopersMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("            &0社区开发者列表");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 27, title);
        UUID uuid = player.getUniqueId();

        this.inv.setItem(10, new DevelopersButtons().HeadButton(player,"duduskz",true));
        this.inv.setItem(11, new DevelopersButtons().HeadButton(player,"awa_0",true));
        this.inv.setItem(12, new DevelopersButtons().HeadButton(player,"Bluesky_ES",true));
        this.inv.setItem(13, new DevelopersButtons().HeadButton(player,"",false));
        this.inv.setItem(14, new DevelopersButtons().HeadButton(player,"",false));
        this.inv.setItem(15, new DevelopersButtons().HeadButton(player,"",false));
        this.inv.setItem(16, new DevelopersButtons().HeadButton(player,"",false));

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
    }

}

