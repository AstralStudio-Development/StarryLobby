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

public class AccountStatusButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack CurrentBanLevelButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&6未启用");
        lores.add("");
        lores.add("&7在你获得了数次处罚后");
        lores.add("&7下一次的触发可能比正常来的更久");

        item = new ItemBuilder(Material.ANVIL).name(ColorUtil.color("&7当前账号封禁等级")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack CompetitiveBanStatusButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7竞技游戏禁令用于保持竞技类游戏的公平性");
        lores.add("&7并禁止重复作弊的用户参与游戏");
        lores.add(" ");
        lores.add("&7状态: &6未启用 (Temporarily)");

        item = new ItemBuilder(Material.DIAMOND_SWORD).name(ColorUtil.color("&6竞技游戏禁令状态")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack GlassButton(Player player) {
        item = new ItemBuilder(Material.STAINED_GLASS_PANE).name(ColorUtil.color(" ")).durability(1).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack BackToProfile() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至个人档案");
        item = new ItemBuilder(Material.ARROW).name(ColorUtil.color("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
