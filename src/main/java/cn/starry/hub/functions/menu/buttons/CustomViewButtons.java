package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomViewButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack RankColor() {
        List<String> lores = new ArrayList<>();
        lores.add("&7拥有&bMVP&c+&7的玩家可以切换“+”的颜色");
        lores.add("");
        lores.add("&e点击切换！");

        item = new ItemBuilder(Material.INK_SACK).name(ColorUtil.color("&aMVP+等级颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Shine() {
        List<String> lores = new ArrayList<>();
        lores.add("&7你的角色将会基于你的Rank颜色染上轮廓。");
        lores.add("");
        lores.add("&c请注意:&7发光效果只会对使用Minecraft");
        lores.add("&71.9+版本的玩家可见！");
        lores.add("");
        lores.add("&c在任一Battle Pass中解锁！");

        item = new ItemBuilder(Material.PRISMARINE_CRYSTALS).name(ColorUtil.color("&a发光")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Back() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至个人档案");

        item = new ItemBuilder(Material.ARROW).name(ColorUtil.color("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
