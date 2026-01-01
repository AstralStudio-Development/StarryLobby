package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StoreButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack getPoint() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7璀璨星尘比例 ");
        lores.add(" &f1元 &7=&f 10 璀璨星尘 ");
        lores.add(" ");

        item = new ItemBuilder(Material.GUNPOWDER).name(CC.translate(" &b璀璨星尘 ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getRank() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7购买会员可拥有更好的游戏体验");
        lores.add(" &7但不会提供任何游戏内优势");
        lores.add(" &7每个等级都包含先前等级的所有特权");
        lores.add(" ");
        lores.add(" &e您当前等级的价值将会自动从未来任何的等级 ");
        lores.add(" &e升级费用中扣除。 ");
        lores.add(" ");
        /*
        lores.add(" &f包含");
        lores.add(" &7VIP.VIP+");
        lores.add(" &7MVP.MVP+等");
        lores.add(" ");

         */

        item = new ItemBuilder(Material.EMERALD).name(CC.translate(" &a会员")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getNetworkBooster() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7使用硬币增倍器，可在特定的小游戏中");
        lores.add(" &7给予&b所有人&7额外的硬币加成");
        lores.add(" &7增幅最高可达3%。");
        lores.add(" ");
        lores.add(" &c如果此时已经存在一个启用的增倍器 ");
        lores.add(" &c那么您须等待当前的增倍器结束 ");
        lores.add(" ");

        item = new ItemBuilder(Material.POTION).name(CC.translate(" &a硬币增倍器")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getDownloadableContent() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7购买拓展内容");
        lores.add(" &7将会解锁部分在原定游戏中锁定的");
        lores.add(" &7包括但不限于");
        lores.add(" &7职业，地图区域，任务，技能，武器等。");
        lores.add(" ");
        lores.add(" &c针对角色扮演游戏的拓展内容 ");
        lores.add(" &c将于此世界剧情结束后免费开放 ");
        lores.add(" ");

        item = new ItemBuilder(Material.PRISMARINE_CRYSTALS).name(CC.translate(" &a拓展内容 ")).lore(lores).build();
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

    public ItemStack GlassButton(boolean a) {
        List<String> lores = new ArrayList<>();
        lores.add("&8⇩ &7物品");
        if (a) {
            item = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).name(CC.translate("&8⇧ &7类别")).lore(lores).build();
        } else {
            item = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(CC.translate("&8⇧ &7类别")).lore(lores).build();
        }
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getUnavailable() {
        List<String> lores = new ArrayList<>();
        lores.add(" &7此页面暂无可购买的内容 ");

        item = new ItemBuilder(Material.BEDROCK).name(CC.translate(" &c该内容暂不可用 ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
