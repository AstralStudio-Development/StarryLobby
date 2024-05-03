package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class NewsButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack Button(Player player,String name) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7点击访问此公告 ");
        lores.add(" ");

        item = new ItemBuilder(Material.BOOK_AND_QUILL).name(ColorUtil.color(" &f" + name)).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        return item;
    }

}
