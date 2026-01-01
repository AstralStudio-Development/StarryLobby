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
        lores.add("&7拥有&bMVP&c+&7的玩家可以切换“+”的颜色");
        lores.add("");
        lores.add("&e点击切换！");

        item = new ItemBuilder(Material.LEGACY_INK_SACK).name(CC.translate("&aMVP+等级颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Shine() {
        List<String> lores = new ArrayList<>();
        lores.add("&7你的角色将会基于你的会员颜色染上轮廓。");
        lores.add("");
        lores.add("&c请注意:&7发光效果只会对使用Minecraft");
        lores.add("&71.9+版本的玩家可见！");
        lores.add("");
        lores.add("&c此功能将在数个版本内上线");

        item = new ItemBuilder(Material.PRISMARINE_CRYSTALS).name(CC.translate("&a发光")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Back() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至个人档案");

        item = new ItemBuilder(Material.ARROW).name(CC.translate("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
