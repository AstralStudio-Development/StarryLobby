package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.api.enums.ActivityType;
import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ActivityButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack LinkItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7点击打开书本页面");
        lores.add(" &7以了解本次活动内容");
        lores.add(" ");
        lores.add(" &e点击打开！");
        lores.add(" ");

        item = new ItemBuilder(Material.LEGACY_BOOK_AND_QUILL).name(CC.translate(" &c活动链接")).lore(lores).shiny().build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack ActivityStoreItem(Player player, ActivityType type) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7你可以通过完成各种游戏任务");
        lores.add(" &7或参与大厅活动来获得");
        lores.add(" &7相对应的活动货币！");
        lores.add(" ");
        lores.add(" &7当期活动: " + type.getDisplayName());
        if (type.isActivityEnable()) {
            lores.add(" &a活动将在" + type.getLeftTime() + "天后结束!");
            lores.add(" ");
            lores.add(" &e点击浏览商店！");
            lores.add(" ");
        } else {
            lores.add(" ");
            lores.add(" &c活动暂未开始");
            lores.add(" ");
        }

        item = new ItemBuilder(Material.EMERALD).name(CC.translate(" &a活动商店")).lore(lores).shiny().build();
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
