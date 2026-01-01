package cn.starry.hub.functions.menu.login;


import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.buttons.AgreementButtons;
import cn.starry.hub.functions.menu.buttons.DevelopersButtons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;


public class StaffMenu implements Listener {

    private Inventory inv;

    String title = CC.translate("           &0在职工作人员列表");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 45, this.title);
        UUID uuid = player.getUniqueId();
        this.inv.setItem(10, new DevelopersButtons().TypeButton("&c运营组", "&f负责服务器主要策划,开发,运营的技术人员。","RED"));

        this.inv.setItem(11, new DevelopersButtons().HeadButton(player, "&cStalyer", true, "首席睡觉官", "看我干什么，问下一个。", "5b4ff4465cc6bcc6ed2a20c39014e9e4d5af9fb639fac1c05d50f47d14325f16"));
        this.inv.setItem(12, new DevelopersButtons().HeadButton(player, "&cStarry_Killer", true, "陪上一个睡的", "看我干什么，问上一个。", "ec4e780fdefd63d140d0597307a5236d331ffc02ed16615d15f56930a5ffcc9c"));
        this.inv.setItem(13, new DevelopersButtons().HeadButton(player, "&cQlickly_", true, "睡觉", "工作日白天不在 有事找我多发几次", "58432ab55415c7842434f6534008e65b8293ac3b0720b95b6af5e2a0a6633b9e"));
        this.inv.setItem(14, new DevelopersButtons().HeadButton(player, "&cCloudForeal", true, "开发、策划", "我真的怀疑有些人闲的程度啊", "bbf137bddedf4b2737b150cde4cc19283fb8be72af29fa5c86e33166cb2f421e"));
        this.inv.setItem(15, new DevelopersButtons().HeadButton(player, "&cpi_ka", true, "开发、策划", "", "5c6fe12e5cce23843167c9ced85c3b5ae17b8cde569d31c8f9bce3121d392d86"));
        this.inv.setItem(16, new DevelopersButtons().HeadButton(player, "&czhuiqiuat", true, "开发", "", "c89c5f915240c551539945e8fd8b3d33f6a0995c5fab0aec5d07a158253be723"));


        this.inv.setItem(19, new DevelopersButtons().TypeButton("&2客服组", "&f负责服务器内管理玩家群体。","GREEN"));

        this.inv.setItem(20, new DevelopersButtons().HeadButton(player, "&2SakiMukiYou", true, "客服", "", "482c998edb03d6f19539a61e9d8e428404ccaf67ebd55589f2fae6169278c0e0"));

        this.inv.setItem(40, new DevelopersButtons().Close());
        this.inv.setItem(44, new DevelopersButtons().More());
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
        if (e.getCurrentItem().equals(new DevelopersButtons().More())) {
            new ThanksMenu().openMenu(player);
        }
        if (e.getCurrentItem().equals(new DevelopersButtons().Close())) {
            player.closeInventory();
        }
        e.setCancelled(true);
    }

}

