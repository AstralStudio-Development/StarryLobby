package cn.starry.hub.menus.achievements.button;

import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GradeCategoryButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        AchievementType achievementType = CacheData.ACHIEVEMENT_MENU.get(player);
        List<String> lores = new ArrayList<>();
        lores.add("   &a分级成就   ");
        lores.add("   &8" + achievementType.getDisplayName() + "   ");
        lores.add(" ");
        lores.add("   &7已解锁 &bN&7/&bA   ");
        lores.add("   &7点数 &eN&7/&eA   ");
        lores.add(" ");
        lores.add("   &7分级成就需要   ");
        lores.add("   &7完成多个级别的成就   ");
        lores.add(" ");
        lores.add("   &c此分类即将开放   ");
        lores.add(" ");

        ItemStack item = new ItemBuilder(Material.DIAMOND_BLOCK).name(CC.translate(" ")).lore(lores).build();
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        // No action
    }
}

