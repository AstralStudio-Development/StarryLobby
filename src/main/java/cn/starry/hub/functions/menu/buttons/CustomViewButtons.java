package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
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
        lores.add("   &aStar+等级颜色   ");
        lores.add(" ");
        lores.add("   &7拥有 &bStar&c+   ");
        lores.add("   &7的玩家可以切换“+”的颜色   ");
        lores.add("");
        lores.add("   &a+ &f点击切换   ");
        lores.add("");

        item = new ItemBuilder(Material.LEGACY_INK_SACK).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Shine() {
        List<String> lores = new ArrayList<>();
        lores.add("   &a人物发光   ");
        lores.add(" ");
        lores.add("   &7你的角色将会基于   ");
        lores.add("   &7你的会员颜色染上轮廓   ");
        lores.add("");
        lores.add("   &c此功能将在数个版本内上线   ");
        lores.add("");

        item = new ItemBuilder(Material.PRISMARINE_CRYSTALS).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Back() {
        List<String> lores = new ArrayList<>();
        lores.add("   &a返回   ");
        lores.add("");
        lores.add("   &7返回至个人档案   ");
        lores.add("");

        item = new ItemBuilder(Material.ARROW).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
