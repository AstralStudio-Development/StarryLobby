package cn.starry.hub.functions.menu.selector;

import cn.starry.hub.api.enums.GameOwned;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.BungeeUtil;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import cn.starry.hub.functions.menu.news.NewsMenu;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.functions.menu.buttons.MainButtons;
import cn.starry.hub.functions.menu.buttons.SelectorButtons;
import cn.starry.hub.functions.menu.store.StoreMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

@AutoRegister
public class SelectorMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("游戏菜单");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 27, title);

        //Game Buttons
        /*
        this.inv.setItem(11, new SelectorButtons().Button(player, GameOwned.BEDWARS));
        this.inv.setItem(12, new SelectorButtons().Button(player,GameOwned.SKYBLOCK));
        this.inv.setItem(13, new SelectorButtons().SMPButton(player));
        this.inv.setItem(14, new SelectorButtons().Button(player,GameOwned.RPG));
        this.inv.setItem(15, new SelectorButtons().Button(player,GameOwned.SKYWARS));

        this.inv.setItem(29, new SelectorButtons().Button(player,GameOwned.PROTOTYPE));
        this.inv.setItem(30, new SelectorButtons().Button(player,GameOwned.ARCADE));
        this.inv.setItem(31, new SelectorButtons().Button(player,GameOwned.BUILDBATTLE));
        this.inv.setItem(32, new SelectorButtons().Button(player,GameOwned.DUEL));
        this.inv.setItem(33, new SelectorButtons().Button(player,GameOwned.UHC));
        this.inv.setItem(39, new SelectorButtons().Button(player,GameOwned.MEGAWALLS));
        this.inv.setItem(40, new SelectorButtons().Button(player,GameOwned.MURDERMYSTERY));
        this.inv.setItem(41, new SelectorButtons().Button(player,GameOwned.THEPIT));

         */

        this.inv.setItem(13, new SelectorButtons().Button(player,GameOwned.THEPIT));

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
        //Main Button Listener
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("个人档案")) {
            new PlayerProfileMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new MainButtons().RewardsButton(player))) {
            if (Main.getInstance().rewards) {
                Bukkit.dispatchCommand(player, "rewards");
            } else {
                player.sendMessage(ColorUtil.color("&c该功能所需前置缺失,已禁用！"));
            }
        }
        if (e.getCurrentItem().equals(new MainButtons().ShopButton(player))) {
            new StoreMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new MainButtons().NewsButton(player))) {
            new NewsMenu().openMenu(player);
        }
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
            BungeeUtil.sendServer(player,"L_SwLobby#1");
        }
        if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.THEPIT))) {
            BungeeUtil.sendServer(player,"G_ThePit#1");
        }
        if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.RPG))) {
            BungeeUtil.sendServer(player,"G_TourP");
        }
        if (e.getCurrentItem().equals(new SelectorButtons().Button(player, GameOwned.ARCADE))) {
            BungeeUtil.sendServer(player,"L_ArcadeLobby#1");
        }
    }

}

