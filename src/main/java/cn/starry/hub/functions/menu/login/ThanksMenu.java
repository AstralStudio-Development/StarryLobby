package cn.starry.hub.functions.menu.login;


import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.buttons.DevelopersButtons;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;


public class ThanksMenu implements Listener {

    private Inventory inv;

    String title = CC.translate("           &0特别感谢列表");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 45, this.title);

        this.inv.setItem(10, new DevelopersButtons().SpecialThanksButton("&bBlue_King2China", "2020", "1c3e4c161ca5a412c95998ef4a1a0f75823f7b7e34930cd025af24cebfcab365"));
        this.inv.setItem(11, new DevelopersButtons().SpecialThanksButton("&bShuiLongtou", "2021", "bd6a3079bc697d6da9a4dae00cd99af46bc4481217dad70e5f59475ef0d9f288"));
        this.inv.setItem(12, new DevelopersButtons().SpecialThanksButton("&bMoJiya_", "2021-2024", "90fd2ccfb617f5c7c09310a4fa1e3c17719c7d5943e74394c004e21d0ea21d3"));
        this.inv.setItem(13, new DevelopersButtons().SpecialThanksButton("&bLeruitou", "2021-2024", "de6260ab7af6d7958427ae5a178ce5f129c5cbfec509b056fbbe4267e604bbe2"));
        this.inv.setItem(14, new DevelopersButtons().SpecialThanksButton("&bBedrock_ShaDog", "2023-至今", "abbe222efc6fafc192af856416c91d3316a4fd589d50e51de7a5702484829a7f"));
        this.inv.setItem(15, new DevelopersButtons().SpecialThanksButton("&bTheGoodBoys", "2023-至今", "80db68ee5c317625537feb67c9d395edd5d3ae2e3f4372bcfcb47e19fcc7a99a"));
        this.inv.setItem(16, new DevelopersButtons().SpecialThanksButton("&bShizoukia", "2024-至今", "8e6d097d57e0d059cf3b01d859b72b6ff15bd17d1e09a41e9b5ca39ab424b7fc"));

        this.inv.setItem(19, new DevelopersButtons().SpecialThanksButton("&bCan_Leng_", "2022-2024", "64b22fdccf923bb566fa35934b2d2a9cdd1e347da603912e272024a4fe4cb783"));
        this.inv.setItem(20, new DevelopersButtons().SpecialThanksButton("&bLove_Taffy", "2021-至今", "8e37d0c63ae415f189dcdbc9d179f541f9af5bce33a91d1d46d3665252092613"));
        this.inv.setItem(21, new DevelopersButtons().SpecialThanksButton("&bKaBuSaMa", "2025-至今", "57ac0469b906b9039b69a56fa07a76a6a1920e098cab4ee7a863a818a21f2e3a"));
        this.inv.setItem(22, new DevelopersButtons().SpecialThanksButton("&bdyslmj_boy", "2022-至今", "1256629e143e341ed26701b785ec4d041ba36d5ceae3b5dd0a0b3df9271f45a1"));

        this.inv.setItem(40, new DevelopersButtons().BackToProfile());
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
        if (!e.getView().getTitle().equals(title)) {
            return;
        }
        if (e.getClickedInventory().equals(player.getInventory())) {
            return;
        }
        if (e.getView().getTitle().equals(title)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        if (e.getCurrentItem().equals(new DevelopersButtons().BackToProfile())) {
            new StaffMenu().openMenu(player);
        }
        e.setCancelled(true);
    }

}

