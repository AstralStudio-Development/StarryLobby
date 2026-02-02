package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.functions.rank.RankColors;
import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import net.minecraft.world.item.InkSacItem;
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
        lores.add("   &a返回   ");
        lores.add(" ");
        lores.add("   &7返回至自定义外观   ");
        lores.add(" ");
        item = new ItemBuilder(Material.ARROW).name(CC.translate(" ")).lore(lores).build();
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

    public ItemStack Red_ColorButton(Player player) {
        RankColors rankColors = RankColors.RED;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &bStar&c+&7 的默认颜色   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.RED_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Gold_ColorButton(Player player) {
        RankColors rankColors = RankColors.GOLD;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.ORANGE_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Green_ColorButton(Player player) {
        RankColors rankColors = RankColors.GREEN;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
            
        }
        item = new ItemBuilder(Material.LIME_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Yellow_ColorButton(Player player) {
        RankColors rankColors = RankColors.YELLOW;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.YELLOW_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Pink_ColorButton(Player player) {
        RankColors rankColors = RankColors.PINK;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
            lores.add("");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.PINK_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack White_ColorButton(Player player) {
        RankColors rankColors = RankColors.WHITE;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.WHITE_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Blue_ColorButton(Player player) {
        RankColors rankColors = RankColors.BLUE;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.LIGHT_BLUE_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Dark_Green_ColorButton(Player player) {
        RankColors rankColors = RankColors.DARK_GREEN;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.GREEN_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Dark_Red_ColorButton(Player player) {
        RankColors rankColors = RankColors.DARK_RED;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.REDSTONE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Cyan_ColorButton(Player player) {
        RankColors rankColors = RankColors.CYAN;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.CYAN_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Purple_ColorButton(Player player) {
        RankColors rankColors = RankColors.PURPURE;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.PURPLE_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Gray_ColorButton(Player player) {
        RankColors rankColors = RankColors.GRAY;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.GRAY_DYE).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Black_ColorButton(Player player) {
        RankColors rankColors = RankColors.BLACK;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.INK_SAC).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Dark_Blue_ColorButton(Player player) {
        RankColors rankColors = RankColors.DARK_BLUE;
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        item = new ItemBuilder(Material.LAPIS_LAZULI).name(CC.translate(" ")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
