package cn.starry.hub.functions.menu.social;

import cn.starry.hub.functions.menu.buttons.MainButtons;
import cn.starry.hub.functions.menu.buttons.ProfileButtons;
import cn.starry.hub.functions.menu.buttons.SocialButtons;
import cn.starry.hub.functions.menu.profile.LanguageMenu;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.anvilgui.AnvilGUI;
import cn.starry.hub.Main;
import cn.yistars.party.bukkit.BungeeChannelManager;
import cn.yistars.party.bukkit.Party;
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

public class PartyMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("组队");

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
            this.inv.setItem(i,new MainButtons().GlassButton(player,11));
        }

        if (Party.PlayerParty.get(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"name")) == null) {
            this.inv.setItem(31, new SocialButtons().noParty());
        } else {
            this.inv.setItem(18, new SocialButtons().inviteParty());
            this.inv.setItem(19, new SocialButtons().removeParty());
            this.inv.setItem(20, new SocialButtons().warpParty());
            this.inv.setItem(21, new SocialButtons().disbandParty());
            /*for (int i = 0; i < Party.PlayerParty.size(); ++i) {
                Integer PartyID = Party.PlayerParty.get(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"name"));
                ArrayList<String> members = Party.PartyMember.get(PartyID);
                this.inv.setItem(i - 18 + 36, new SocialButtons().partySkullItem(members.get(i)));
            }

             */
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
        if (e.getCurrentItem().equals(new SocialButtons().noParty())) {
            new AnvilGUI.Builder()
                    .title("输入用户名")
                    .text("输入用户名")
                    .itemLeft(new ItemStack(Material.PAPER)).onComplete(completion -> {
                        String Command = "party " + completion.getText();
                        BungeeChannelManager.sendPluginMessage(player, "PlayerCommand", Command);
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }).plugin(Main.getInstance()).open(player);
        }
        if (e.getCurrentItem().equals(new SocialButtons().inviteParty())) {
            new AnvilGUI.Builder()
                    .title("输入用户名")
                    .text("输入用户名")
                    .itemLeft(new ItemStack(Material.PAPER)).onComplete(completion -> {
                        String Command = "party invite " + completion.getText();
                        BungeeChannelManager.sendPluginMessage(player, "PlayerCommand", Command);
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }).plugin(Main.getInstance()).open(player);
        }
        if (e.getCurrentItem().equals(new SocialButtons().removeParty())) {
            new AnvilGUI.Builder()
                    .title("输入用户名")
                    .text("输入用户名")
                    .itemLeft(new ItemStack(Material.PAPER)).onComplete(completion -> {
                        String Command = "party kick " + completion.getText();
                        BungeeChannelManager.sendPluginMessage(player, "PlayerCommand", Command);
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }).plugin(Main.getInstance()).open(player);
        }
        if (e.getCurrentItem().equals(new SocialButtons().warpParty())) {
            String Command = "party warp";
            BungeeChannelManager.sendPluginMessage(player, "PlayerCommand", Command);
            player.closeInventory();
        }
        if (e.getCurrentItem().equals(new SocialButtons().disbandParty())) {
            String Command = "party disband";
            BungeeChannelManager.sendPluginMessage(player, "PlayerCommand", Command);
            player.closeInventory();
        }
    }

}

