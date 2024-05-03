package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.api.enums.GameType;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
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
        lores.add(" &7我们将在此菜单展示 ");
        lores.add(" &7处于测试阶段的小游戏内容 ");
        lores.add(" ");
        lores.add(" &7与主菜单相同,你可以通过右键传送 ");
        lores.add(" ");

        item = new ItemBuilder(Material.BOOK).name(ColorUtil.color(" &a什么是铸造厂")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack MegaWallsButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f" + GameType.SPORTS.getDisplayName());
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" ");
        lores.add(" &7一款史诗级的百人游戏 ");
        lores.add(" &7带有趣味和独特的职业 ");
        lores.add(" &7击败敌人的凋零 ");
        lores.add(" &7以防止他们重生 ");
        lores.add(" ");

        item = new ItemBuilder(Material.SOUL_SAND).amount(1).name(ColorUtil.color(" &f超级战墙 "+ GameType.SPORTS.getFormattedDisplayName())).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

}
