package cn.starry.hub.functions.menu.profile;

import cn.starry.hub.functions.menu.buttons.CustomViewButtons;
import cn.starry.core.utils.chat.CC;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class CustomViewMenu implements Listener {

    private Inventory inv;

    String title = CC.translate("自定义外观");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 54, title);

        this.inv.setItem(20, new CustomViewButtons().RankColor());
        this.inv.setItem(24, new CustomViewButtons().Shine());

        this.inv.setItem(40, new CustomViewButtons().Back());

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
        //
        if (e.getCurrentItem().equals(new CustomViewButtons().Back())) {
            new PlayerProfileMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new CustomViewButtons().RankColor())) {
            new RankColorMenu().openMenu(player,false);
        }
        e.setCancelled(true);
    }

}

