package cn.starry.hub.functions.menu.buttons.legacy;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class StoreButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack getPoint() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7服内点券比例");
        lores.add(" &f1元 &7=&f 10点券");
        lores.add(" ");
        lores.add(" &7已支持充值方式");
        lores.add(" &7  - &f微信");
        lores.add(" &7  - &f支付宝");
        lores.add(" ");

        item = new ItemBuilder(Material.BOOK).name(CC.translate(" &f充值点券")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getRank() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7会员将拥有更好的游戏体验");
        lores.add(" &7你也可以在这里升级会员");
        lores.add(" ");
        lores.add(" &f包含");
        lores.add(" &7VIP.VIP+");
        lores.add(" &7MVP.MVP+等");
        lores.add(" ");

        item = new ItemBuilder(Material.EMERALD).name(CC.translate(" &f会员")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getMysteryBox() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7打开此处");
        lores.add(" &7购买神秘箱");
        lores.add(" ");

        item = new ItemBuilder(Material.ENDER_CHEST).name(CC.translate(" &f神秘箱")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getHorn() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7喇叭可以向全服");
        lores.add(" &7发送您想发的消息");
        lores.add(" ");
        lores.add(" &f指令");
        lores.add(" &7/lb (内容)");
        lores.add(" ");

        item = new ItemBuilder(Material.HOPPER).name(CC.translate(" &f喇叭")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getSuffix() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7用点卷购买您的后缀");
        lores.add(" &7让你在游戏中更加出色");
        lores.add(" ");
        lores.add(" &c此内容已下架");
        lores.add(" ");

        item = new ItemBuilder(Material.NAME_TAG).name(CC.translate(" &f后缀")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getBooster() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7用硬币增倍器,在特定游戏");
        lores.add(" &7给予&b所有人&7额外的硬币加成");
        lores.add(" ");
        lores.add(" &c此分类暂未开放");
        lores.add(" ");

        item = new ItemBuilder(Material.POTION).name(CC.translate(" &f硬币增倍器")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Back() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至货摊");

        item = new ItemBuilder(Material.ARROW).name(CC.translate("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
