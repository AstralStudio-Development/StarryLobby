package cn.starry.hub.functions.menu.bedwars;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.buttons.gamehub.BedWarsButtons;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class BedWarsShopMenu implements Listener {

    private Inventory inv;

    String title = CC.translate("自定义外观");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 36, title);

        this.inv.setItem(11, new BedWarsButtons().Cosmetics(player));
        this.inv.setItem(15, new BedWarsButtons().Settings(player));

        this.inv.setItem(31, new BedWarsButtons().Close());

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
        if (e.getCurrentItem().equals(new BedWarsButtons().Close())) {
            player.closeInventory();
        }
        if (e.getCurrentItem().equals(new BedWarsButtons().Settings(player))) {

        }
        if (e.getCurrentItem().equals(new BedWarsButtons().Cosmetics(player))) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 0.5F);
            player.sendMessage(CC.translate("&c将于数个版本内上线。"));
        }
        e.setCancelled(true);
    }

}

