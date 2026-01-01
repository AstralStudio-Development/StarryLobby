package cn.starry.hub.functions.menu.buttons.gamehub;

import cn.starry.core.api.enums.ActivityType;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BedWarsButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack Cosmetics(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" &7查看你已拥有的起床战争特效");
        lores.add(" &7或是通过代币来购买它们。");

        item = new ItemBuilder(Material.ARMOR_STAND).name(CC.translate(" &a起床战争特效")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Settings(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" &7调整起床战争的游戏设置。");

        item = new ItemBuilder(Material.COMPARATOR).name(CC.translate(" &a起床战争设置")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Close() {
        item = new ItemBuilder(Material.BARRIER).name(CC.translate("&c关闭")).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
