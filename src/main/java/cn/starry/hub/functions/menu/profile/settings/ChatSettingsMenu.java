package cn.starry.hub.functions.menu.profile.settings;

import cn.starry.hub.Main;
import cn.starry.hub.functions.menu.buttons.SettingButtons;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class ChatSettingsMenu implements Listener {

    private Inventory inv;

    private boolean isBack;

    String title = ColorUtil.color("聊天设置");

    public void openMenu(Player player,boolean isMenu) {
        this.init(player,isMenu);
        player.openInventory(this.inv);
    }

    public void init(Player player,boolean isMenu) {
        isBack = isMenu;
        this.inv = Bukkit.createInventory(null, 54, title);
        UUID uuid = player.getUniqueId();

        this.inv.setItem(0, new SettingButtons().LobbySettingsButton(player,false));
        this.inv.setItem(1, new SettingButtons().ChatSettingsButton(player,true));

        for (int i = 9; i < 18; i++) {
            this.inv.setItem(i,new SettingButtons().GlassButton(player,7));
        }

        this.inv.setItem(10, new SettingButtons().GlassButton(player,13));

        //Settings Item
        this.inv.setItem(19, new SettingButtons().RECEIVE_ITEM(player));
        this.inv.setItem(22, new SettingButtons().FILTERATE_PUBLIC_ITEM(player));
        this.inv.setItem(38, new SettingButtons().JOIN_MESSAGE_ITEM(player));
        //Receive Button
        if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_receive").equals("ENABLE")) {
            this.inv.setItem(28, new SettingButtons().RECEIVE_ENABLE());
        } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_receive").equals("DISABLE")) {
            this.inv.setItem(28, new SettingButtons().RECEIVE_DISABLE());
        } else {
            player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:RECEIVE_TYPE"));
            player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
        }
        //FILTERATE Public Button
        if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_filterate_public").equals("ENABLE")) {
            this.inv.setItem(31, new SettingButtons().FILTERATE_PUBLIC_ENABLE());
        } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_filterate_public").equals("DISABLE")) {
            this.inv.setItem(31, new SettingButtons().FILTERATE_PUBLIC_DISABLE());
        } else {
            player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:FILTERATE_PUBLIC_TYPE"));
            player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
        }
        //Join Message Button
        if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_joinmessage").equals("ENABLE")) {
            this.inv.setItem(47, new SettingButtons().JOIN_MESSAGE_ENABLE());
        } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_joinmessage").equals("DISABLE")) {
            this.inv.setItem(47, new SettingButtons().JOIN_MESSAGE_DISABLE());
        } else {
            player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:JOINMESSAGE_TYPE"));
            player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
        }

        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        boolean isMenu = false;
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
        if (!e.getInventory().getName().equals(title)) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getInventory().getName().equals(title)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        if (e.getInventory().contains(new SettingButtons().Back())) {
            isMenu = true;
        }
        if (e.getCurrentItem().equals(new SettingButtons().RECEIVE_DISABLE()) || e.getCurrentItem().equals(new SettingButtons().RECEIVE_ENABLE())) {
            if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_receive").equals("ENABLE")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_chat_receive","DISABLE");
                new ChatSettingsMenu().openMenu(player,isMenu);
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_receive").equals("DISABLE")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_chat_receive","ENABLE");
                new ChatSettingsMenu().openMenu(player,isMenu);
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:RECEIVE_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
        }
        if (e.getCurrentItem().equals(new SettingButtons().JOIN_MESSAGE_ENABLE()) || e.getCurrentItem().equals(new SettingButtons().JOIN_MESSAGE_DISABLE())) {
            if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_joinmessage").equals("ENABLE")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_chat_joinmessage","DISABLE");
                new ChatSettingsMenu().openMenu(player,isMenu);
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_joinmessage").equals("DISABLE")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_chat_joinmessage","ENABLE");
                new ChatSettingsMenu().openMenu(player,isMenu);
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:JOINMESSAGE_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
        }
        if (e.getCurrentItem().equals(new SettingButtons().FILTERATE_PUBLIC_DISABLE()) || e.getCurrentItem().equals(new SettingButtons().FILTERATE_PUBLIC_ENABLE())) {
            if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_filterate_public").equals("ENABLE")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_chat_filterate_public","DISABLE");
                new ChatSettingsMenu().openMenu(player,isMenu);
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_chat_filterate_public").equals("DISABLE")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_chat_filterate_public","ENABLE");
                new ChatSettingsMenu().openMenu(player,isMenu);
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:FILTERATE_PUBLIC_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
        }
        if (e.getCurrentItem().equals(new SettingButtons().LobbySettingsButton(player,false))) {
            new LobbySettingsMenu().openMenu(player,false);
        }
    }

}

