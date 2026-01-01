package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GadgetsSlotButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack Info(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        lores.add("&7收藏物品可方便你的使用。请在收藏品菜单右键点击物品以加入收藏夹！");
        lores.add("&7");
        lores.add("&c此功能将在数个版本内加入");

        item = new ItemBuilder(Material.BOOK).name("&a喜爱的收藏品").lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Reset() {
        List<String> lores = new ArrayList<>();
        lores.add("&7重置所有自定义槽位");

        item = new ItemBuilder(Material.RED_STAINED_GLASS).name(CC.translate("&c重置自定义槽位")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
