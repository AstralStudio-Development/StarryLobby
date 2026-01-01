package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomSlotButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack Button(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        //LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        lores.add("&7此槽位可用于快速加入你最喜欢的游戏或模式");
        lores.add(" ");
        lores.add("&7你随时可以右键修改此槽位");

        item = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("&e自定义槽位").lore(lores).build();
                /*
            case ENGLISH:
                item = new ItemBuilder(Material.LEGACY_STAINED_GLASS_PANE).durability(7).name("&eCustom Slot").lore(lores).build();
                break;
            case JAPANESE:
                item = new ItemBuilder(Material.LEGACY_STAINED_GLASS_PANE).durability(7).name("&eカスタムスロット").lore(lores).build();
                break;

                 */
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

    public ItemStack Back() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至选择菜单");

        item = new ItemBuilder(Material.ARROW).name(CC.translate("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
