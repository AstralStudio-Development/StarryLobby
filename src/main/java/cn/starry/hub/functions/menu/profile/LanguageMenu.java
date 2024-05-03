package cn.starry.hub.functions.menu.profile;

import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import cn.starry.hub.functions.menu.buttons.LanguageButtons;
import cn.starry.hub.listener.handler.LobbyHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

@AutoRegister
public class LanguageMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("选择你的语言");

    public void openMenu(Player player,boolean isByCommand) {
        this.init(player,isByCommand);
        player.openInventory(this.inv);
    }

    public void init(Player player,boolean isByCommand) {
        this.inv = Bukkit.createInventory(null, 54, title);

        this.inv.setItem(10, new LanguageButtons().ChineseButton(player));
        //this.inv.setItem(11, new LanguageButtons().EnglishButton(player));
        //this.inv.setItem(12, new LanguageButtons().JapaneseButton(player));

        if (isByCommand) {
            this.inv.setItem(49, new LanguageButtons().Close());
        } else {
            this.inv.setItem(49, new LanguageButtons().BackToProfile());
        }

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
        if (!e.getInventory().getName().equals(title)) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getInventory().getName().equals(title)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("简体中文")) {
            player.sendMessage(ColorUtil.color("&a你将你的语言设置为 &6简体中文 &a！"));
            Main.getInstance().getData().updatePlayerData(uuid,"language", LanguageType.CHINESE.toString());
            player.playSound(player.getLocation(),Sound.BLOCK_NOTE_PLING,1,1);
            new LobbyHandler().loadItem(player);
            player.closeInventory();
        }
        /*
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("English")) {
            player.sendMessage(ColorUtil.color("&aYou set your language to &6English&a!"));
            Main.getInstance().getData().updatePlayerData(uuid,"language",LanguageType.ENGLISH.toString());
            player.playSound(player.getLocation(),Sound.BLOCK_NOTE_PLING,1,1);
            new LobbyHandler().loadItem(player);
            TabHandler.sendTabList(player);
            player.closeInventory();
        }
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().getItemMeta().getDisplayName().contains("日本語")) {
            player.sendMessage(ColorUtil.color("&a言語を &6日本語 &aに設定しました！"));
            Main.getInstance().getData().updatePlayerData(uuid,"language",LanguageType.JAPANESE.toString());
            player.playSound(player.getLocation(),Sound.BLOCK_NOTE_PLING,1,1);
            new LobbyHandler().loadItem(player);
            TabHandler.sendTabList(player);
            player.closeInventory();
        }

         */
        if (e.getCurrentItem().equals(new LanguageButtons().Close())) {
            player.closeInventory();
        }
        if (e.getCurrentItem().equals(new LanguageButtons().BackToProfile())) {
            new PlayerProfileMenu().openMenu(player);
        }
    }

}

