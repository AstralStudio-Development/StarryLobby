package cn.starry.hub.functions.menu.profile;

import cn.starry.hub.functions.menu.achievements.AchievementsMenu;
import cn.starry.hub.functions.menu.profile.settings.LobbySettingsMenu;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.LuckPermsUtil;
import cn.starry.hub.Main;
import cn.starry.hub.functions.menu.buttons.AccountStatusButtons;
import cn.starry.hub.functions.menu.buttons.MainButtons;
import cn.starry.hub.functions.menu.buttons.ProfileButtons;
import cn.starry.hub.functions.menu.news.NewsMenu;
import cn.starry.hub.functions.menu.social.PartyMenu;
import cn.starry.hub.functions.menu.social.RecentPlayersMenu;
import cn.starry.hub.functions.menu.store.StoreMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class  PlayerProfileMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("个人档案");

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
            this.inv.setItem(i,new AccountStatusButtons().GlassButton(player));
        }

        this.inv.setItem(22, new ProfileButtons().PlayerButton(player));
        this.inv.setItem(29, new ProfileButtons().AppearanceButton(player));
        this.inv.setItem(30, new ProfileButtons().AchievementsButton(player));
        this.inv.setItem(31, new MainButtons().RewardsButton(player));
        this.inv.setItem(32, new ProfileButtons().QuestsButton(player));
        this.inv.setItem(33, new ProfileButtons().SettingButton(player));
        this.inv.setItem(39, new ProfileButtons().LanguagesButton(player));
        this.inv.setItem(40, new ProfileButtons().StoreButton(player));
        this.inv.setItem(41, new ProfileButtons().AccountStatusButton(player));

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
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().equals(LuckPermsUtil.getPlayerRank(player.getUniqueId()) + player.getName())) {
            new PlayerProfileMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new MainButtons().RewardsButton(player))) {
            if (Main.getInstance().rewards) {
                player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,2);
                player.sendMessage(ColorUtil.color("&c此功能处于维护状态！"));
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
        //
        if (e.getCurrentItem().equals(new ProfileButtons().SettingButton(player))) {
            new LobbySettingsMenu().openMenu(player,true);
        }
        if (e.getCurrentItem().equals(new ProfileButtons().SuffixChangeButton(player))) {
            new SuffixChangeMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new ProfileButtons().AchievementsButton(player))) {
            new AchievementsMenu().openMenu(player);
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("选择语言")) {
            new LanguageMenu().openMenu(player,false);
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("好友")) {
            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,2);
            player.sendMessage(ColorUtil.color("&c此功能处于维护状态！"));
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("组队")) {
            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,2);
            player.sendMessage(ColorUtil.color("&c此功能处于维护状态！"));
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("公会")) {
            player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_NO,1,2);
            player.sendMessage(ColorUtil.color("&c此功能处于维护状态！"));
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("近期活跃玩家")) {
            new RecentPlayersMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new ProfileButtons().AccountStatusButton(player))) {
            new AccountStatusMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new ProfileButtons().AppearanceButton(player))) {
            new CustomViewMenu().openMenu(player);
        }
    }

}

