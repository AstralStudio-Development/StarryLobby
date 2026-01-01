package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CommodityButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack PointsItem(int amount,Material material) {
        int real = amount/10;
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &f可选以下方式付款");
        lores.add(" ");
        lores.add(" &7微信支付");
        lores.add(" &7支付宝支付");
        lores.add(" ");
        lores.add(" &7支付金额 &f" + real + "CNY");
        lores.add(" ");

        item = new ItemBuilder(material).name(CC.translate(" &f" + amount + "点券 &7(" + real + "CNY)")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack MysteryBoxItem(int amount, int level, int price) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        if (level == 1) {
            lores.add(" &7必出两个&b[5✰]&7神秘箱 ");
        } else if (level == 2) {
            lores.add(" &7必出三个&b[4✰]&7与&b[5✰]&7神秘箱 ");
        } else if (level == 3) {
            lores.add(" &7必出五个&b[4✰]&7与&b[5✰]&7神秘箱 ");
        }
        lores.add(" &7品质 &f随机");
        lores.add(" ");
        lores.add(" &7总价格 &f" + price + " &7点券");
        lores.add(" ");

        item = new ItemBuilder(Material.ENDER_CHEST).amount(amount).name(CC.translate(" &f神秘箱 &7(" + amount + ")")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack HornItem(int amount,int price) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7个数 &f" + amount);
        lores.add(" ");
        lores.add(" &7总价格 &f" + price + " &7点券");
        lores.add(" ");

        item = new ItemBuilder(Material.HOPPER).amount(amount).name(CC.translate(" &f喇叭 &7(" + amount + ")")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
