package cn.starry.hub.functions.menu.profile;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.BungeeUtil;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.menu.buttons.PrototypeButtons;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

@AutoRegister
public class PrototypeMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("                 &0铸造厂");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 36, title);

        //Game Buttons
        this.inv.setItem(13, new PrototypeButtons().MegaWallsButton(player));

        this.inv.setItem(31, new PrototypeButtons().Info());

        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
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
        if (e.getCurrentItem().equals(new PrototypeButtons().MegaWallsButton(player))) {
            BungeeUtil.sendServer(player,"L_MwLobby#1");
        }
    }

}

