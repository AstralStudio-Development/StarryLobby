package cn.starry.hub.functions.menu.profile.settings;

import cn.starry.hub.Main;
import cn.starry.hub.functions.achievement.AchievementManager;
import cn.starry.hub.functions.menu.buttons.SettingButtons;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class LobbySettingsMenu implements Listener {

    private Inventory inv;

    private boolean isBack;

    String title = ColorUtil.color("大厅设置");

    public void openMenu(Player player,boolean isMenu) {
        this.init(player,isMenu);
        player.openInventory(this.inv);
    }

    public void init(Player player,boolean isMenu) {
        isBack = isMenu;
        this.inv = Bukkit.createInventory(null, 54, title);
        UUID uuid = player.getUniqueId();

        this.inv.setItem(0, new SettingButtons().LobbySettingsButton(player,true));
        this.inv.setItem(1, new SettingButtons().ChatSettingsButton(player,false));
        this.inv.setItem(9, new SettingButtons().GlassButton(player,13));

        for (int i = 9; i < 18; i++) {
            this.inv.setItem(i,new SettingButtons().GlassButton(player,7));
        }

        this.inv.setItem(9, new SettingButtons().GlassButton(player,13));

        //Settings Item
        this.inv.setItem(30, new SettingButtons().SHOW_ITEM(player));
        this.inv.setItem(32, new SettingButtons().TIME_ITEM(player));
        //Show Button
        if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_show").equals("ENABLE")) {
            this.inv.setItem(39, new SettingButtons().SHOW_ENABLE());
        } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_show").equals("DISABLE")) {
            AchievementManager.unlockAchievement(player,AchievementManager.getAchievement("OnlyOne"));
            this.inv.setItem(39, new SettingButtons().SHOW_DISABLE());
        } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_show").equals("RANK")) {
            this.inv.setItem(39, new SettingButtons().SHOW_RANK());
        } else {
            player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:SHOW_TYPE"));
            player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
        }
        //Time Button
            if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_time").equals("DAY")) {
                    this.inv.setItem(41, new SettingButtons().TIME_DAY());
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_time").equals("SUNSET")) {
                    this.inv.setItem(41, new SettingButtons().TIME_SUNSET());
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_time").equals("NIGHT")) {
                    this.inv.setItem(41, new SettingButtons().TIME_NIGHT());
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:TIME_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }

            this.inv.setItem(49, new SettingButtons().Back());

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
        if (e.getCurrentItem().equals(new SettingButtons().SHOW_DISABLE()) || e.getCurrentItem().equals(new SettingButtons().SHOW_ENABLE()) || e.getCurrentItem().equals(new SettingButtons().SHOW_RANK())) {
            if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_show").equals("ENABLE")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_lobby_show","DISABLE");
                for (Player p : getServer().getOnlinePlayers()) {
                    player.hidePlayer(p);
                }
                new LobbySettingsMenu().openMenu(player,isMenu);
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_show").equals("DISABLE")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_lobby_show","RANK");
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (p.hasPermission("lobby.rankshow")) {
                        if (!Main.getInstance().getData().getPlayerData(p.getUniqueId(),"vanish").equalsIgnoreCase("true")) {
                            player.showPlayer(p);
                        }
                    } else {
                        player.hidePlayer(p);
                    }
                }
                new LobbySettingsMenu().openMenu(player,isMenu);
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_show").equals("RANK")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_lobby_show","ENABLE");
                for (Player p : getServer().getOnlinePlayers()) {
                    if (!Main.getInstance().getData().getPlayerData(p.getUniqueId(),"vanish").equalsIgnoreCase("true")) {
                        player.showPlayer(p);
                    }
                }
                new LobbySettingsMenu().openMenu(player,isMenu);
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:SHOW_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
        }
        if (e.getCurrentItem().equals(new SettingButtons().TIME_DAY()) || e.getCurrentItem().equals(new SettingButtons().TIME_SUNSET()) || e.getCurrentItem().equals(new SettingButtons().TIME_NIGHT())) {
            if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_time").equals("DAY")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_lobby_time","SUNSET");
                player.setPlayerTime(12650L,false);
                new LobbySettingsMenu().openMenu(player,isMenu);
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_time").equals("SUNSET")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_lobby_time","NIGHT");
                player.setPlayerTime(18000L,false);
                new LobbySettingsMenu().openMenu(player,isMenu);
            } else if (Main.getPlugin(Main.class).getData().getPlayerData(uuid,"settings_lobby_time").equals("NIGHT")) {
                Main.getPlugin(Main.class).getData().updatePlayerData(uuid,"settings_lobby_time","DAY");
                player.setPlayerTime(1200L,false);
                new LobbySettingsMenu().openMenu(player,isMenu);
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:TIME_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
        }
        if (e.getCurrentItem().equals(new SettingButtons().Back())) {
            new PlayerProfileMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new SettingButtons().ChatSettingsButton(player,false))) {
            new ChatSettingsMenu().openMenu(player,false);
        }
    }

}

