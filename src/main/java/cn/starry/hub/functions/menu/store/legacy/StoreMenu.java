package cn.starry.hub.functions.menu.store.legacy;

import cn.starry.hub.functions.menu.buttons.MainButtons;
import cn.starry.hub.functions.menu.buttons.legacy.StoreButtons;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.functions.menu.store.legacy.sub.CommodityMenu;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


@Deprecated
public class StoreMenu implements Listener {

    private Inventory inv;

    String title = CC.translate("                  &0货摊");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 54, title);

        //Main Buttons
        this.inv.setItem(10, new MainButtons().PlayerProfileButton(player));
        this.inv.setItem(19, new MainButtons().RewardsButton(player));
        this.inv.setItem(28, new MainButtons().ShopButton(player));
        this.inv.setItem(37, new MainButtons().NewsButton(player));
        //Store
        this.inv.setItem(14, new StoreButtons().getPoint());
        this.inv.setItem(21, new StoreButtons().getRank());
        this.inv.setItem(22, new StoreButtons().getMysteryBox());
        this.inv.setItem(23, new StoreButtons().getHorn());
        this.inv.setItem(24, new StoreButtons().getSuffix());
        this.inv.setItem(25, new StoreButtons().getBooster());

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
        if (e.getCurrentItem().getType() == Material.LEGACY_SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("个人档案")) {
            new PlayerProfileMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new MainButtons().RewardsButton(player))) {
            if (StarryLobby.getInstance().rewards) {
                Bukkit.dispatchCommand(player, "rewards");
            } else {
                player.sendMessage(CC.translate("&c该功能所需前置缺失,已禁用！"));
            }
        }
        if (e.getCurrentItem().equals(new MainButtons().ShopButton(player))) {
            new StoreMenu().openMenu(player);
        }
        //
        if (e.getCurrentItem().equals(new StoreButtons().getPoint())) {
            new CommodityMenu().openMenu(player,0);
        }
        if (e.getCurrentItem().equals(new StoreButtons().getRank())) {
            new CommodityMenu().openMenu(player,1);
        }
        if (e.getCurrentItem().equals(new StoreButtons().getMysteryBox())) {
            new CommodityMenu().openMenu(player,2);
        }
        if (e.getCurrentItem().equals(new StoreButtons().getHorn())) {
            new CommodityMenu().openMenu(player,3);
        }
        /*
        if (e.getCurrentItem().equals(new StoreButtons().getSuffix())) {
            new CommodityMenu().openMenu(player,4);
        }

         */
    }

}

