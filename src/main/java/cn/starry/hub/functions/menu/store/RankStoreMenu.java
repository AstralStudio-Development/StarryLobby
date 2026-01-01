package cn.starry.hub.functions.menu.store;

import cn.starry.hub.functions.menu.buttons.StoreButtons;

import cn.starry.core.utils.chat.CC;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class RankStoreMenu implements Listener {

    private Inventory inv;

    String title = CC.translate("会员");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 54, title);

        this.inv.setItem(0,new StoreButtons().getRank());
        this.inv.setItem(1,new StoreButtons().getNetworkBooster());
        this.inv.setItem(2,new StoreButtons().getDownloadableContent());

        for (int i = 9; i < 18; i++) {
            this.inv.setItem(i,new StoreButtons().GlassButton(false));
        }

        this.inv.setItem(9,new StoreButtons().GlassButton(true));

        this.inv.setItem(31,new StoreButtons().getUnavailable());

        this.inv.setItem(48,new StoreButtons().Close());
        this.inv.setItem(49,new StoreButtons().getPoint());

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
        if (!e.getView().getTitle().equals(title)) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getView().getTitle().equals(title)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        //Main Button Listener
        //
        if (e.getCurrentItem().equals(new StoreButtons().getPoint())) {
            player.closeInventory();
            player.sendMessage(CC.translate("&c暂不支持充值服务器"));
        }
        if (e.getCurrentItem().equals(new StoreButtons().Close())) {
            player.closeInventory();
        }
        if (e.getCurrentItem().equals(new StoreButtons().getRank())) {
            new RankStoreMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new StoreButtons().getNetworkBooster())) {
            new BoosterStoreMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new StoreButtons().getDownloadableContent())) {
            new DLCStoreMenu().openMenu(player);
        }
    }

}

