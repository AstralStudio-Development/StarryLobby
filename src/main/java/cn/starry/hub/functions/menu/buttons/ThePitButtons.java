package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ThePitButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack THEPIT1(Player player) {
        String online = PlaceholderAPI.setPlaceholders(player,"%bungee_G_ThePit#1%");
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f竞技类小游戏 ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" &7在线 &f" + online + " &7人 ");
        lores.add(" ");
        lores.add(" &7跳入坑中 ");
        lores.add(" &7升级装备 击杀敌人 ");
        lores.add(" ");

        item = new ItemBuilder(Material.INK_SACK).amount(Math.max(Integer.parseInt(online), 1)).durability(8).name(ColorUtil.color(" &f天坑乱斗 &7(一区)")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack THEPIT2(Player player) {
        String online = PlaceholderAPI.setPlaceholders(player,"%bungee_G_ThePit#2%");
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f竞技类小游戏 ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" &7在线 &f" + online + " &7人 ");
        lores.add(" ");
        lores.add(" &7跳入坑中 ");
        lores.add(" &7升级装备 击杀敌人 ");
        lores.add(" ");

        item = new ItemBuilder(Material.INK_SACK).amount(Math.max(Integer.parseInt(online), 1)).durability(9).name(ColorUtil.color(" &f天坑乱斗 &7(二区)")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack THEPIT3(Player player) {
        String online = PlaceholderAPI.setPlaceholders(player,"%bungee_G_ThePit#3%");
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f竞技类小游戏 ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" &7在线 &f" + online + " &7人 ");
        lores.add(" ");
        lores.add(" &7跳入坑中 ");
        lores.add(" &7升级装备 击杀敌人 ");
        lores.add(" ");

        item = new ItemBuilder(Material.INK_SACK).amount(Math.max(Integer.parseInt(online), 1)).durability(10).name(ColorUtil.color(" &f天坑乱斗 &7(三区)")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack THEPIT4(Player player) {
        String online = PlaceholderAPI.setPlaceholders(player,"%bungee_G_ThePit#Starry%");
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f竞技类小游戏 ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" &7在线 &f" + online + " &7人 ");
        lores.add(" ");
        lores.add(" &7跳入坑中 ");
        lores.add(" &7升级装备 击杀敌人 ");
        lores.add(" ");
        lores.add(" &c此区服无BOT,且数据不互通 ");
        lores.add(" &c内容由Fairylands自主更新 ");
        lores.add(" ");

        item = new ItemBuilder(Material.INK_SACK).amount(Math.max(Integer.parseInt(online), 1)).durability(11).name(ColorUtil.color(" &f天坑乱斗 &f(&b星辰&f)")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack NEWPIT(Player player) {
        String online = PlaceholderAPI.setPlaceholders(player,"%bungee_G_ThePit#1%");
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f竞技类小游戏 ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" &7在线 &f" + online + " &7人 ");
        lores.add(" ");
        lores.add(" &7跳入坑中 ");
        lores.add(" &7升级装备 击杀敌人 ");
        lores.add(" ");

        item = new ItemBuilder(Material.DIRT).amount(Math.max(Integer.parseInt(online), 1)).name(ColorUtil.color(" &f天坑乱斗 ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack DEBUGPIT(Player player) {
        String online = PlaceholderAPI.setPlaceholders(player,"%bungee_G_ThePit#2%");
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f竞技类小游戏 ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" &7在线 &f" + online + " &7人 ");
        lores.add(" ");
        lores.add(" &7跳入坑中 ");
        lores.add(" &7升级装备 击杀敌人 ");
        lores.add(" ");

        item = new ItemBuilder(Material.DIRT).amount(Math.max(Integer.parseInt(online), 1)).name(ColorUtil.color(" &f天坑乱斗 &c(战斗区) ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Back() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至选择菜单");

        item = new ItemBuilder(Material.ARROW).name(ColorUtil.color("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Info() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7部分补偿/活动将会陆续上线 ");
        lores.add(" &7以原数据为准进行自助补偿 ");
        lores.add(" ");

        item = new ItemBuilder(Material.BOOK).name(ColorUtil.color(" &a天坑乱斗通知")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

}
