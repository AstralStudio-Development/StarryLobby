package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.utils.LuckPermsUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SuffixButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack None(Player player) {
        List<String> lores = new ArrayList<>();
        String name = "无";
        String display = " ";
        String display2 = "";
        lores.add(" &8后缀");
        lores.add(" ");
        lores.add(" &7选择此后缀");
        lores.add(" &7将隐藏你的后缀");
        lores.add(" ");
        lores.add(" &f浏览");
        lores.add(PlaceholderAPI.setPlaceholders(player, " %luckperms_prefix%%player_name% " + display));
        lores.add(" ");
        String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
        if (suffix.equals(display) || suffix.equals(display2)) {
            lores.add(" &a已选择");
        } else {
            lores.add(" &e点击选择");
        }
        lores.add(" ");

        item = new ItemBuilder(Material.BARRIER).name(ColorUtil.color(" &a" + name)).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Flowers(Player player,boolean isShop) {
        List<String> lores = new ArrayList<>();
        String name = "花";
        String display = "&e&l❀";
        lores.add(" &8后缀");
        lores.add(" ");
        lores.add(" &7选择" + name);
        lores.add(" &7花圃的象征");
        lores.add(" ");
        lores.add(" &f浏览");
        lores.add(PlaceholderAPI.setPlaceholders(player, " %luckperms_prefix%%player_name% " + display));
        lores.add(" ");
        if (!isShop) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (player.hasPermission("prefix.follow") || player.hasPermission("suffix.flower")) {
                if (suffix.equals(" " + display)) {
                    lores.add(" &a已选择");
                } else {
                    lores.add(" &e点击选择");
                }
            } else {
                lores.add(" &c前往货摊购买");
            }
        } else {
            if (player.hasPermission("prefix.follow") || player.hasPermission("suffix.flower")) {
                lores.add(" &e前往后缀切换");
            } else {
                lores.add(" &f售价 &b200 &f点券 ");
                lores.add(" ");
                lores.add(" &e点击购买");
            }
        }
        lores.add(" ");

        item = new ItemBuilder(Material.YELLOW_FLOWER).name(ColorUtil.color(" &a" + name)).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack LittleFlowers(Player player,boolean isShop) {
        List<String> lores = new ArrayList<>();
        String name = "小鲜花";
        String display = "&8[&c小鲜花&8]";
        lores.add(" &8后缀");
        lores.add(" ");
        lores.add(" &7选择" + name);
        lores.add(" &7园丁辛勤劳作的结果");
        lores.add(" ");
        lores.add(" &f浏览");
        lores.add(PlaceholderAPI.setPlaceholders(player, " %luckperms_prefix%%player_name% " + display));
        lores.add(" ");
        if (!isShop) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (player.hasPermission("prefix.xxh") || player.hasPermission("suffix.xxh")) {
                if (suffix.equals(" " + display)) {
                    lores.add(" &a已选择");
                } else {
                    lores.add(" &e点击选择");
                }
            } else {
                lores.add(" &c前往货摊购买");
            }
        } else {
            if (player.hasPermission("prefix.xxh") || player.hasPermission("suffix.xxh")) {
                lores.add(" &e前往后缀切换");
            } else {
                lores.add(" &f售价 &b200 &f点券 ");
                lores.add(" ");
                lores.add(" &e点击购买");
            }
        }
        lores.add(" ");

        item = new ItemBuilder(Material.DOUBLE_PLANT).durability(4).name(ColorUtil.color(" &a" + name)).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Star(Player player,boolean isShop) {
        List<String> lores = new ArrayList<>();
        String name = "星星";
        String display = "&b&l✰";
        lores.add(" &8后缀");
        lores.add(" ");
        lores.add(" &7选择" + name);
        lores.add(" &7晚安,这片大地");
        lores.add(" ");
        lores.add(" &f浏览");
        lores.add(PlaceholderAPI.setPlaceholders(player, " %luckperms_prefix%%player_name% " + display));
        lores.add(" ");
        if (!isShop) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (player.hasPermission("prefix.star") || player.hasPermission("suffix.star")) {
                if (suffix.equals(" " + display)) {
                    lores.add(" &a已选择");
                } else {
                    lores.add(" &e点击选择");
                }
            } else {
                   lores.add(" &c前往货摊购买");
            }
        } else {
            if (player.hasPermission("prefix.star") || player.hasPermission("suffix.star")) {
                lores.add(" &e前往后缀切换");
            } else {
                lores.add(" &f售价 &b200 &f点券 ");
                lores.add(" ");
                lores.add(" &e点击购买");
            }
        }
        lores.add(" ");

        item = new ItemBuilder(Material.NETHER_STAR).name(ColorUtil.color(" &a" + name)).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Fairylands(Player player,boolean isShop) {
        List<String> lores = new ArrayList<>();
        String name = "仙境";
        String display = "&8[&f仙境&8]";
        lores.add(" &8后缀");
        lores.add(" ");
        lores.add(" &7选择" + name);
        lores.add(" &7终究是有魔法的存在");
        lores.add(" ");
        lores.add(" &f浏览");
        lores.add(PlaceholderAPI.setPlaceholders(player, " %luckperms_prefix%%player_name% " + display));
        lores.add(" ");
        if (!isShop) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (player.hasPermission("prefix.fairylands") || player.hasPermission("suffix.fairylands")) {
                if (suffix.equals(" " + display)) {
                    lores.add(" &a已选择");
                } else {
                    lores.add(" &e点击选择");
                }
            } else {
                lores.add(" &c前往货摊购买");
            }
        } else {
            if (player.hasPermission("prefix.fairylands") || player.hasPermission("suffix.fairylands")) {
                lores.add(" &e前往后缀切换");
            } else {
                lores.add(" &f售价 &b200 &f点券 ");
                lores.add(" ");
                lores.add(" &e点击购买");
            }
        }
        lores.add(" ");

        item = new ItemBuilder(Material.QUARTZ).name(ColorUtil.color(" &a" + name)).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack ZZSF(Player player,boolean isShop) {
        List<String> lores = new ArrayList<>();
        String name = "征战四方";
        String display = "&8[&b征&c战&d四&e方&8]&a✔";
        lores.add(" &8后缀");
        lores.add(" ");
        lores.add(" &7选择" + name);
        lores.add(" &7征服一切吧");
        lores.add(" ");
        lores.add(" &f浏览");
        lores.add(PlaceholderAPI.setPlaceholders(player, " %luckperms_prefix%%player_name% " + display));
        lores.add(" ");
        if (!isShop) {
            String suffix = LuckPermsUtil.getPlayerSuffix(player.getName());
            if (player.hasPermission("prefix.zzsf") || player.hasPermission("suffix.zzsf")) {
                if (suffix.equals(" " + display)) {
                    lores.add(" &a已选择");
                } else {
                    lores.add(" &e点击选择");
                }
            } else {
                lores.add(" &c前往货摊购买");
            }
        } else {
            if (player.hasPermission("prefix.zzsf") || player.hasPermission("suffix.zzsf")) {
                lores.add(" &e前往后缀切换");
            } else {
                lores.add(" &f售价 &b300 &f点券 ");
                lores.add(" ");
                lores.add(" &e点击购买");
            }
        }
        lores.add(" ");

        item = new ItemBuilder(Material.EMERALD).name(ColorUtil.color(" &a" + name)).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Guild(Player player) {
        List<String> lores = new ArrayList<>();
        String name = "公会";
        lores.add(" &8后缀");
        lores.add(" ");
        lores.add(" &7选择" + name);
        lores.add(" &7以会长所设定的公会名称出场");
        lores.add(" ");
        lores.add(" &c此选项于正式版加入");
        lores.add(" ");

        item = new ItemBuilder(Material.BOOK).name(ColorUtil.color(" &a" + name)).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Back() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至个人档案");

        item = new ItemBuilder(Material.ARROW).name(ColorUtil.color("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
