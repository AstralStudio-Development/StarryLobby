package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.utils.LuckPermsUtil;
import cn.starry.hub.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MainButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack PlayerProfileButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(PlaceholderAPI.setPlaceholders(player, Main.getInstance().rewards ? "&7YumeGames等级: &6%alonsolevels_level%" : "&7YumeGames等级: &8已禁用"));
        lores.add(PlaceholderAPI.setPlaceholders(player, "&7成就点数: &e%starrylobby_points%"));
        item = new ItemBuilder(Material.SKULL_ITEM).durability(3).name(ColorUtil.color(LuckPermsUtil.getPlayerRank(player.getUniqueId()) + player.getName())).setNewSkullOwner(player).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack RewardsButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7玩游戏并完成任务");
        lores.add("&7可以得到&3YumeGames经验&7奖励,");
        lores.add("&7可用于升级以及体验");
        lores.add("&7新的增益效果和领取更多奖励！");
        if (Main.getInstance().rewards) {
            lores.add(" ");
            lores.add(PlaceholderAPI.setPlaceholders(player, "&3YumeGames等级&a%alonsolevels_level% %alonsolevels_progress_bar% &3%alonsolevels_progress_percent_format%"));
            lores.add(" ");
            lores.add(PlaceholderAPI.setPlaceholders(player, "&7距离下级所需经验: &3%alonsolevels_experience_to_level_up%"));
        }
        lores.add(" ");
        lores.add("&e点击查看你的奖励！");

        item = new ItemBuilder(Material.BREWING_STAND_ITEM).name(ColorUtil.color("&aYumeGames等级")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack ShopButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7在这获取 ");
        lores.add(" &7权益 道具等...");
        lores.add(" ");
        lores.add(" &f包含 ");
        lores.add(" &7会员 神秘箱 ");
        lores.add(" &7喊话喇叭等... ");
        lores.add(" ");

        item = new ItemBuilder(Material.NAME_TAG).name(ColorUtil.color(" &f货摊")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack NewsButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7在这查看 ");
        lores.add(" &7服务器的更新公告");
        lores.add(" ");
        lores.add(" &f@mc.fairylands.top ");
        lores.add(" ");

        item = new ItemBuilder(Material.ITEM_FRAME).name(ColorUtil.color(" &f公告")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack GlassButton(Player player,int durability) {
        item = new ItemBuilder(Material.STAINED_GLASS_PANE).name(ColorUtil.color(" ")).durability(durability).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
