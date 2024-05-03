package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.Main;
import cn.starry.hub.functions.rank.RankColors;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RankColorButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack Back() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至自定义外观");
        item = new ItemBuilder(Material.ARROW).name(ColorUtil.color("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Close() {
        item = new ItemBuilder(Material.BARRIER).name(ColorUtil.color("&c关闭")).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Red_ColorButton(Player player) {
        RankColors rankColors = RankColors.RED;
        List<String> lores = new ArrayList<>();
        lores.add("&bMVP&c+&7的默认颜色。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(1).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Gold_ColorButton(Player player) {
        RankColors rankColors = RankColors.GOLD;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(14).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Green_ColorButton(Player player) {
        RankColors rankColors = RankColors.GREEN;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(10).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Yellow_ColorButton(Player player) {
        RankColors rankColors = RankColors.YELLOW;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(11).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Pink_ColorButton(Player player) {
        RankColors rankColors = RankColors.PINK;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(9).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack White_ColorButton(Player player) {
        RankColors rankColors = RankColors.WHITE;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(15).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Blue_ColorButton(Player player) {
        RankColors rankColors = RankColors.BLUE;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(12).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Dark_Green_ColorButton(Player player) {
        RankColors rankColors = RankColors.DARK_GREEN;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(2).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Dark_Red_ColorButton(Player player) {
        RankColors rankColors = RankColors.DARK_RED;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.REDSTONE).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Cyan_ColorButton(Player player) {
        RankColors rankColors = RankColors.CYAN;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(6).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Purple_ColorButton(Player player) {
        RankColors rankColors = RankColors.PURPURE;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(5).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Gray_ColorButton(Player player) {
        RankColors rankColors = RankColors.GRAY;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(8).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Black_ColorButton(Player player) {
        RankColors rankColors = RankColors.BLACK;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(0).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Dark_Blue_ColorButton(Player player) {
        RankColors rankColors = RankColors.DARK_BLUE;
        List<String> lores = new ArrayList<>();
        lores.add("&7在&bMVP&c+&7处改变“+”的颜色");
        lores.add("&7为" + rankColors.getColorChinese() + "，将它变为&bMVP&" + rankColors.getColorChar() + "+");
        lores.add("");
        lores.add("&7在TAB列表，聊天中");
        lores.add("&7与进入大厅时显示。");
        lores.add("");
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("&a已选择！");
        } else {
            lores.add("&e点击选择！");
        }
        item = new ItemBuilder(Material.INK_SACK).durability(4).name(ColorUtil.color("&a" + rankColors.getColorChinese() + "色会员颜色")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
