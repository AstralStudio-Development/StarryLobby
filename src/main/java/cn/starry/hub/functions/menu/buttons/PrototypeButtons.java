package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.api.enums.GameOwned;
import cn.starry.core.api.enums.GameType;
import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PrototypeButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack Info() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7人气星尘 &bN&7/A");
        lores.add(" ");
        lores.add(" &7我们将在此菜单展示 ");
        lores.add(" &7处于测试阶段的小游戏内容 ");
        lores.add(" ");
        lores.add(" &b人气星尘&7可以用于展示 ");
        lores.add(" &7你对这些实验室游戏的热爱程度。 ");
        lores.add(" ");

        item = new ItemBuilder(Material.GLOWSTONE_DUST).name(CC.translate(" &b人气星尘")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack PrototypeButton(GameOwned gameOwned, boolean isReleased) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &b人气星尘&7可以用于展示 ");
        lores.add(" &7你对这些实验室游戏的热爱程度。 ");
        lores.add(" ");
        if (isReleased) {
            lores.add(" &a这个游戏已确定发布正式版， ");
            lores.add(" &a无需再投票！ ");
            lores.add(" ");
        } else {
            /*
            lores.add(" &e点击为&e&l" + gameOwned.getDisplayName().replace(" ","").replace("&b","&e") + "&e投票！");

             */
            lores.add(" &c投票系统暂未开放！");
            lores.add(" ");
        }

        item = new ItemBuilder(gameOwned.getItemStack()).amount(1).name(CC.translate(" &a为" + gameOwned.getDisplayName().replace(" ","").replace("&b","&a") + "投票")).lore(lores).build();
        if (isReleased) {
            item = new ItemBuilder(item).shiny().build();
        }
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

}
