package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RankButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack VIP(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7VIP 提供最基础的权益");
        lores.add("&7以改善你在服务器的体验！");
        lores.add(" ");
        lores.add("&a✓ &7聊天前缀: &a[VIP]");
        lores.add("&a✓ &7名称标签颜色: &a绿色");
        lores.add("&a✓ &715+ 权益游戏特效");
        lores.add("&a✓ &7额外神秘箱礼包");
        lores.add("&a✓ &7/fw 命令");
        lores.add(" ");
        if (!player.hasPermission("lobby.vip")) {
            if (player.hasPermission("group.vip")) {
                lores.add("&c已购买！");
                lores.add(" ");
            } else {
                lores.add("&7花费: &a400 &f点券 ");
                lores.add(" ");
                lores.add("&a点击购买！");
            }
        } else {
            lores.add("&c已购买！");
        }

        item = new ItemBuilder(Material.IRON_INGOT).name(CC.translate("&aVIP 会员")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack VIPUpgrade(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &f样式 &a[VIP&c+&a] " + player.getName() + " ");
        lores.add(" ");
        lores.add(" &f大厅飞行 &a&l✓ ");
        lores.add(" &f特定情况不被隐藏 &a&l✓ ");
        lores.add(" &f加入/退出 信息 &a&l✓ ");
        lores.add(" &f聊天前缀 §aVIP&c+ &a&l✓ ");
        lores.add(" &f昵称颜色 §a绿色 &a&l✓ ");
        lores.add(" &f喊话&喇叭 30秒 &a&l✓ ");
        lores.add(" &f专属举报反馈通道 &a&l✓ ");
        lores.add(" ");
        if (!player.hasPermission("lobby.vip+")) {
            if (player.hasPermission("group.vip+")) {
                lores.add(" &c已拥有此会员等级 ");
                lores.add(" ");
            } else {
                lores.add(" &f售价 &b500 &f点券 ");
                lores.add(" ");
                lores.add(" &7点击获取 &aVIP&c+ ");
                lores.add(" &f&n有效期 永久 ");
                lores.add(" ");
            }
        } else {
            lores.add(" &c已拥有更高的会员等级 ");
            lores.add(" ");
        }

        item = new ItemBuilder(Material.GOLD_INGOT).name(CC.translate("&f &aVIP&c+ &f会员")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack MVP(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &f样式 &b[MVP] " + player.getName() + " ");
        lores.add(" ");
        lores.add(" &f大厅飞行,速度调节 &a&l✓ ");
        lores.add(" &f特定情况不被隐藏 &a&l✓ ");
        lores.add(" &f加入/退出 信息 &a&l✓ ");
        lores.add(" &f聊天前缀 §bMVP &a&l✓ ");
        lores.add(" &f昵称颜色 §b蓝色 &a&l✓ ");
        lores.add(" &f喊话&喇叭 20秒 &a&l✓ ");
        lores.add(" &f专属举报反馈通道 &a&l✓ ");
        lores.add(" &f起床战争强制开始游戏 &a&l✓ ");
        lores.add(" &f天坑乱斗会员 &a&l✓ ");
        lores.add(" ");
        if (!player.hasPermission("lobby.mvp")) {
            if (player.hasPermission("group.mvp")) {
                lores.add(" &c已拥有此会员等级 ");
                lores.add(" ");
            } else {
                lores.add(" &f售价 &b1000 &f点券 ");
                lores.add(" ");
                lores.add(" &7点击获取 &bMVP ");
                lores.add(" &f&n有效期 永久 ");
                lores.add(" ");
            }
        } else {
            lores.add(" &c已拥有更高的会员等级 ");
            lores.add(" ");
        }

        item = new ItemBuilder(Material.DIAMOND).name(CC.translate("&f &bMVP &f会员")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack MVPUpgrade(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &f样式 &b[MVP&c+&b] " + player.getName() + " ");
        lores.add(" ");
        lores.add(" &f大厅飞行,速度调节 &a&l✓ ");
        lores.add(" &f特定情况不被隐藏 &a&l✓ ");
        lores.add(" &f加入/退出 信息 &a&l✓ ");
        lores.add(" &f聊天前缀 §bMVP&c+ &a&l✓ ");
        lores.add(" &f昵称颜色 §b蓝色 &a&l✓ ");
        lores.add(" &f喊话&喇叭 20秒 &a&l✓ ");
        lores.add(" &f专属举报反馈通道 &a&l✓ ");
        lores.add(" &f起床战争强制开始游戏 &a&l✓ ");
        lores.add(" &f天坑乱斗会员 &a&l✓ ");
        lores.add(" &f大厅指令/ride &a&l✓ ");
        lores.add(" ");
        if (!player.hasPermission("lobby.mvp")) {
            if (player.hasPermission("group.mvp+")) {
                lores.add(" &c已拥有此会员等级 ");
                lores.add(" ");
            } else {
                lores.add(" &f售价 &b1500 &f点券 ");
                lores.add(" ");
                lores.add(" &7点击获取 &bMVP&c+ ");
                lores.add(" &f&n有效期 永久 ");
                lores.add(" ");
            }
        } else {
            lores.add(" &c已拥有更高的会员等级 ");
            lores.add(" ");
        }

        item = new ItemBuilder(Material.EMERALD).name(CC.translate("&f &bMVP&c+ &f会员")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
