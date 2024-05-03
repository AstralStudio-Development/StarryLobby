package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
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
        lores.add(ColorUtil.color("&7这是一份玩家协议"));
        lores.add(ColorUtil.color("&7在您进入服务器前,请阅读此协议"));
        lores.add(ColorUtil.color(""));
        lores.add(ColorUtil.color("&e点击查看"));

        item = new ItemBuilder(Material.NETHER_STAR).name(ColorUtil.color("&b玩家协议")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack AgreeButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(ColorUtil.color("&f点击即可进入服务器"));
        lores.add(ColorUtil.color("&a同时,代表您同意协议内容"));

        item = new ItemBuilder(Material.STAINED_CLAY).durability(5).name(ColorUtil.color("&b同意协议内容")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack DisAgreeButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(ColorUtil.color("&f点击将被踢出服务器"));
        lores.add(ColorUtil.color("&c同时,代表您不同意协议内容"));

        item = new ItemBuilder(Material.STAINED_CLAY).durability(14).name(ColorUtil.color("&b不同意协议内容")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
