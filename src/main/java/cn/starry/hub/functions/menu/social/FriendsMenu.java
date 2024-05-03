package cn.starry.hub.functions.menu.social;

import cn.starry.hub.functions.menu.buttons.MainButtons;
import cn.starry.hub.functions.menu.buttons.SocialButtons;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.BungeeUtil;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.anvilgui.AnvilGUI;
import cn.starry.hub.Main;
import cn.starry.hub.functions.menu.news.NewsMenu;
import cn.starry.hub.functions.menu.store.StoreMenu;
import cn.starry.social.bukkit.API;
import cn.starry.social.bukkit.utils.FriendManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@AutoRegister
public class FriendsMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("                  &0好友");

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

        this.inv.setItem(12, new SocialButtons().friendAddItem());
        this.inv.setItem(13, new SocialButtons().friendRemoveItem());

        if (API.getAPI().getFriendUtils(player.getUniqueId()).getFriends().size() == 0) {
            this.inv.setItem(32, new SocialButtons().noFriends());
        } else {
            List<String> friendUUIDs = API.getAPI().getFriendUtils(player.getUniqueId()).getFriends();

            for (int i = 0; i < friendUUIDs.size(); ++i) {
                UUID uuid = UUID.fromString(friendUUIDs.get(i));
                FriendManager.FriendUtils friendUtils = API.getAPI().getFriendUtils(uuid);
                String friendName = friendUtils.getName();
                inv.setItem(getItemsSlots()[i], new SocialButtons().friendSkullItem(friendName));
            }
        }

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
        //
        if (e.getCurrentItem().equals(new SocialButtons().friendAddItem())) {
            new AnvilGUI.Builder()
                    .title("输入用户名")
                    .text("输入用户名")
                    .itemLeft(new ItemStack(Material.PAPER)).onComplete(completion -> {
                        BungeeUtil.sendFriendCmd(player, "add " + completion.getText());
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
            }).plugin(Main.getInstance()).open(player);
        }
        if (e.getCurrentItem().equals(new SocialButtons().friendRemoveItem())) {
            new AnvilGUI.Builder()
                    .title("输入用户名")
                    .text("输入用户名")
                    .itemLeft(new ItemStack(Material.PAPER)).onComplete(completion -> {
                        BungeeUtil.sendFriendCmd(player, "remove " + completion.getText());
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }).plugin(Main.getInstance()).open(player);
        }
    }

    public Integer[] getItemsSlots() {
        return new Integer[]{21, 22, 23, 24, 25, 30, 31, 32, 33, 34};
    }

}

