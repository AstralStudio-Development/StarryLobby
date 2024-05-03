package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class DeliveryButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack DisableButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7此内容仍在开发 ");
        lores.add(" &7于今晚上线! ");
        lores.add(" ");
        lores.add(" &7---Starry_Killer ");
        lores.add(" ");

        item = new ItemBuilder(Material.BARRIER).name(ColorUtil.color(" &c哦不...")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack PublicizeVideoButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7来了解一下YumeGames拍摄的");
        lores.add("&7服务器宣传片吧");
        lores.add(" ");
        lores.add("&e呃,可能还没拍摄...");

        item = new ItemBuilder(Material.RECORD_12).name(ColorUtil.color("&bFairylands宣传片")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack CodeItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" &7使用&e/code (兑换码)§7来兑换！ ");
        item = new ItemBuilder(Material.SIGN).name(ColorUtil.color(" &a使用兑换码")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack NewPlayerItem(Player player) {
        UUID uuid = player.getUniqueId();
        boolean isReceive = Main.getInstance().getData().getDeliveryData(uuid,"newPlayer");
        List<String> lores = new ArrayList<>();
        if (isReceive) {
            lores.add(" &7欢迎来到YumeGames！ ");
            lores.add(" &7领取一些免费的经验及神秘宝箱 ");
            lores.add(" &7来帮助你入门 ");
            lores.add(" ");
            lores.add(" &c你已经领取了这个礼包！ ");
        } else {
            lores.add(" &7欢迎来到YumeGames！ ");
            lores.add(" &7领取一些免费的经验及神秘宝箱 ");
            lores.add(" &7来帮助你入门 ");
            lores.add(" ");
            lores.add(" &e点击此处领取！ ");
        }
        item = new ItemBuilder(Material.ENCHANTMENT_TABLE).name(ColorUtil.color(" &a欢迎礼包")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getVIPItem(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        String permissionRequire = "lobby.delivery.vip";
        String rankRequire = "&aVIP";
        if (!Main.getInstance().getData().getDeliveryData(uuid,"vip")) {
            lores.add(" &7你在" + getCurrentlyMonth() + "的每月免费神秘箱已经送达！ ");
            lores.add(" ");
            lores.add(" &7需要会员等级" + rankRequire + " ");
            lores.add(" ");
            if (player.hasPermission(permissionRequire)) {
                lores.add(" &e点击领取 ");
            } else {
                lores.add(" &c点击领取 ");
            }
        }
        item = new ItemBuilder(Material.ENDER_CHEST).name(ColorUtil.color((player.hasPermission(permissionRequire) ? " &a" : " &c") + "神秘箱礼包")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getVIPPlusItem(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        String permissionRequire = "lobby.delivery.vip+";
        String rankRequire = "&aVIP&c+";
        if (!Main.getInstance().getData().getDeliveryData(uuid,"vipPlus")) {
            lores.add(" &7你在" + getCurrentlyMonth() + "的每月免费神秘箱已经送达！ ");
            lores.add(" ");
            lores.add(" &7需要会员等级" + rankRequire + " ");
            lores.add(" ");
            if (player.hasPermission(permissionRequire)) {
                lores.add(" &e点击领取 ");
            } else {
                lores.add(" &c点击领取 ");
            }
        }
        item = new ItemBuilder(Material.ENDER_CHEST).name(ColorUtil.color((player.hasPermission(permissionRequire) ? " &a" : " &c") + "神秘箱礼包")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getMVPItem(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        String permissionRequire = "lobby.delivery.mvp";
        String rankRequire = "&bMVP";
        if (!Main.getInstance().getData().getDeliveryData(uuid,"mvp")) {
            lores.add(" &7你在" + getCurrentlyMonth() + "的每月免费神秘箱已经送达！ ");
            lores.add(" ");
            lores.add(" &7需要会员等级" + rankRequire + " ");
            lores.add(" ");
            if (player.hasPermission(permissionRequire)) {
                lores.add(" &e点击领取 ");
            } else {
                lores.add(" &c点击领取 ");
            }
        }
        item = new ItemBuilder(Material.ENDER_CHEST).name(ColorUtil.color((player.hasPermission(permissionRequire) ? " &a" : " &c") + "神秘箱礼包")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getMVPPlusItem(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lores = new ArrayList<>();
        String permissionRequire = "lobby.delivery.mvp+";
        String rankRequire = "&bMVP&c+";
        if (!Main.getInstance().getData().getDeliveryData(uuid,"mvpPlus")) {
            lores.add(" &7你在" + getCurrentlyMonth() + "的每月免费神秘箱已经送达！ ");
            lores.add(" ");
            lores.add(" &7需要会员等级" + rankRequire + " ");
            lores.add(" ");
            if (player.hasPermission(permissionRequire)) {
                lores.add(" &e点击领取 ");
            } else {
                lores.add(" &c点击领取 ");
            }
        }
        item = new ItemBuilder(Material.ENDER_CHEST).name(ColorUtil.color((player.hasPermission(permissionRequire) ? " &a" : " &c") + "神秘箱礼包")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getDefaultItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" &7你在" + getCurrentlyMonth() + "的每月免费神秘箱已经送达！ ");
        lores.add(" ");
        lores.add(" &e点击领取 ");
        item = new ItemBuilder(Material.ENDER_CHEST).name(ColorUtil.color(" &a神秘箱礼包")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getDoneBoxItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" &7你已经领取了这个礼包，请过段时间再来！ ");
        lores.add(" ");
        lores.add(" &7距下一次礼包送达 " + getLeftTime(0) + "天" + getLeftTime(1) + "小时" + getLeftTime(2) + "分钟！ ");
        lores.add(" ");
        lores.add(" &c下次在来吧 :) ");
        item = new ItemBuilder(Material.MINECART).name(ColorUtil.color(" &c神秘箱礼包")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getDailyItem(Player player) {
        UUID uuid = player.getUniqueId();
        boolean isReceive = Main.getInstance().getData().getDeliveryData(uuid,"daily");
        List<String> lores = new ArrayList<>();
        if (isReceive) {
            lores.add(" &7你已经领取了这个奖励！ ");
            lores.add(" &7请在" + getLeftTime(1) + "小时" + getLeftTime(2) + "分钟" + getLeftTime(3) + "秒后再来领取 ");
        } else {
            lores.add(" &7免费的2500点服务器经验和五个一星奖励箱 ");
            lores.add(" ");
            lores.add(" &e点击领取奖励 ");
        }
        item = new ItemBuilder(isReceive ? Material.MINECART : Material.STORAGE_MINECART).name(ColorUtil.color((isReceive ? " &c" : " &a") + "每日奖励")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getDoneDailyItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" &7你已经领取了这个奖励！ ");
        lores.add(" &7请在" + getLeftTime(1) + "小时" + getLeftTime(2) + "分钟" + "秒后再来领取 ");
        item = new ItemBuilder(Material.MINECART).name(ColorUtil.color(" &c每日奖励")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    private long getLeftTime(int a) {
        Calendar now = Calendar.getInstance();
        Calendar tomorrowStart = Calendar.getInstance();
        tomorrowStart.add(6, 1);
        tomorrowStart.set(11, 0);
        tomorrowStart.set(12, 0);
        tomorrowStart.set(13, 0);
        tomorrowStart.set(14, 0);
        long remainingTime = tomorrowStart.getTimeInMillis() - now.getTimeInMillis();
        long remainingSeconds = remainingTime / 1000L;
        long days = remainingSeconds / 86400L;
        long hours = remainingSeconds / 3600L;
        long minutes = remainingSeconds % 3600L / 60L;
        long seconds = remainingSeconds % 60L;
        if (a == 0) {
            return days;
        } else if (a == 1) {
            return hours;
        } else if (a == 2) {
            return minutes;
        } else if (a == 3) {
            return seconds;
        } else {
            return 999;
        }
    }

    private String getCurrentlyMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(2) + 1;
        String chineseMonth;
        switch (month) {
            case 1: {
                chineseMonth = "一月";
                break;
            }
            case 2: {
                chineseMonth = "二月";
                break;
            }
            case 3: {
                chineseMonth = "三月";
                break;
            }
            case 4: {
                chineseMonth = "四月";
                break;
            }
            case 5: {
                chineseMonth = "五月";
                break;
            }
            case 6: {
                chineseMonth = "六月";
                break;
            }
            case 7: {
                chineseMonth = "七月";
                break;
            }
            case 8: {
                chineseMonth = "八月";
                break;
            }
            case 9: {
                chineseMonth = "九月";
                break;
            }
            case 10: {
                chineseMonth = "十月";
                break;
            }
            case 11: {
                chineseMonth = "十一月";
                break;
            }
            case 12: {
                chineseMonth = "十二月";
                break;
            }
            default: {
                chineseMonth = "";
            }
        }
        return chineseMonth;
    }

}
