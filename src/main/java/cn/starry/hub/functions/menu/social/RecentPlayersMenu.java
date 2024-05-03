package cn.starry.hub.functions.menu.social;

import cn.starry.hub.functions.menu.buttons.MainButtons;
import cn.starry.hub.functions.menu.buttons.ProfileButtons;
import cn.starry.hub.functions.menu.buttons.SocialButtons;
import cn.starry.hub.functions.menu.profile.LanguageMenu;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class RecentPlayersMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("近期活跃玩家");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 54, title);

        //Main Buttons
        this.inv.setItem(2, new MainButtons().PlayerProfileButton(player));
        this.inv.setItem(3, new ProfileButtons().FriendButton());
        this.inv.setItem(4, new ProfileButtons().PartyButton(player));
        this.inv.setItem(5, new ProfileButtons().GuildButton(player));
        this.inv.setItem(6, new ProfileButtons().RecentPlayersButton(player));

        for (int i = 9; i < 18; i++) {
            this.inv.setItem(i,new MainButtons().GlassButton(player,7));
        }

        this.inv.setItem(18, new SocialButtons().clearRecentPlayers());
        this.inv.setItem(31, new SocialButtons().noRecentPlayers());

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
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains(player.getName())) {
            new PlayerProfileMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new MainButtons().RewardsButton(player))) {
            if (Main.getInstance().rewards) {
                Bukkit.dispatchCommand(player, "rewards");
            } else {
                player.sendMessage(ColorUtil.color("&c该功能所需前置缺失,已禁用！"));
            }
        }
        //
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("选择语言")) {
            new LanguageMenu().openMenu(player,false);
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("好友")) {
            player.sendMessage(ColorUtil.color("&c此功能处于维护状态！"));
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("组队")) {
            new PartyMenu().openMenu(player);
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("公会")) {
            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,2);
            player.sendMessage(ColorUtil.color("&c公会菜单即将上线..."));
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("近期活跃玩家")) {
            new RecentPlayersMenu().openMenu(player);
        }
        //
        if (e.getCurrentItem().equals(new SocialButtons().clearRecentPlayers())) {
            player.closeInventory();
        }
    }

}

