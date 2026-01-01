package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AgreementButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack AgreementButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("&7这是一份玩家协议"));
        lores.add(CC.translate("&7在您进入服务器前,请阅读此协议"));
        lores.add(CC.translate(""));
        lores.add(CC.translate("&e点击查看"));

        item = new ItemBuilder(Material.NETHER_STAR).name(CC.translate("&b玩家协议")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack LogButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("&8截至2025/3/30"));
        lores.add(CC.translate(""));
        lores.add(CC.translate("&7- &f修改了一些东西"));
        lores.add(CC.translate("&7- &f移除了org.bukkit.Player"));
        lores.add(CC.translate("&7- &f移除了Herobrine"));
        lores.add(CC.translate(""));

        item = new ItemBuilder(Material.PAPER).name(CC.translate("&b更新日志")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack AgreeButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("&f点击即可进入服务器"));
        lores.add(CC.translate("&a同时,代表您同意协议内容"));

        item = new ItemBuilder(Material.GREEN_TERRACOTTA).name(CC.translate("&b同意协议内容")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack DisAgreeButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("&f点击将被踢出服务器"));
        lores.add(CC.translate("&c同时,代表您不同意协议内容"));

        item = new ItemBuilder(Material.RED_TERRACOTTA).name(CC.translate("&b不同意协议内容")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
