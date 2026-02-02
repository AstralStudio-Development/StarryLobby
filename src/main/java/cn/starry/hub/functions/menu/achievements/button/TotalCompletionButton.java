package cn.starry.hub.functions.menu.achievements.button;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.functions.achievement.AbstractAchievement;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.achievements.AchievementsSubMenu;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class TotalCompletionButton extends Button {

    private final AchievementType achievementType;
    private final int id; // 0=Total, 1=Challenge, 2=Grade, 3=Category? logic copied from legacy
    private final Menu parent;

    public TotalCompletionButton(AchievementType achievementType, int id, Menu parent) {
        this.achievementType = achievementType;
        this.id = id;
        this.parent = parent;
    }

    public TotalCompletionButton(AchievementType achievementType, int id) {
        this(achievementType, id, null);
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
        int unlockedAchievements = 0;
        int totalAchievements = 0;
        int availablePoints = 0;
        int totalPoints = 0;
        for (AbstractAchievement achievements : Core.getInstance().getAchievementFactory().getAchievements()) {
            if (id == 0 || id == 1 || id == 2 || id == 3 && achievementType != null) {
                if (achievements.getType().equals(achievementType)) {
                    totalAchievements++;
                    totalPoints = totalPoints + achievements.getPoints();
                    if (Core.getInstance().getMongoDB().getAchievementData(player.getUniqueId(),"achievements").contains(achievements.getInternalName())) {
                        unlockedAchievements++;
                        availablePoints = availablePoints + achievements.getPoints();
                    }
                }
            } else {
                totalAchievements++;
                totalPoints = totalPoints + achievements.getPoints();
                if (Core.getInstance().getMongoDB().getAchievementData(player.getUniqueId(),"achievements").contains(achievements.getInternalName())) {
                    unlockedAchievements++;
                    availablePoints = availablePoints + achievements.getPoints();
                }
            }
        }
        List<String> lores = new ArrayList<>();
        if (id == 0) {
            lores.add("   &a总完成度   ");
        } else if (id == 1) {
            lores.add("   &a挑战成就   ");
        } else if (id == 2) {
            lores.add("   &a分级成就   ");
        }

        if (id == 0 || id == 1 || id == 2 && achievementType != null) {
            lores.add("   &8" + achievementType.getDisplayName() + "   ");
            lores.add(" ");
        } else {
            if (id != 3) {
                lores.add("   &a成就完成进度   ");
                lores.add(" ");
            } else {
                lores.add("   &a" + achievementType.getDisplayName() + "成就   ");
                lores.add(" ");
            }
        }
        lores.add("   &7已解锁 &b" + unlockedAchievements + "&7/&b" + totalAchievements + " &8(" + numberFormat.format((float) unlockedAchievements / (float) totalAchievements * 100) + "%)   ");
        lores.add("   &7点数 &e" + availablePoints + "&7/&e" + totalPoints + " &8(" + numberFormat.format((float) availablePoints / (float) totalPoints * 100) + "%)   ");
        lores.add(" ");
        if (id == 3 && achievementType != null) {
            lores.add("   &a+ &f点击查看成就   ");
            lores.add(" ");
        }
        
        ItemStack item;
        if (id == 0) {
            item = new ItemBuilder(achievementType.getIcon()).name(CC.translate(" ")).lore(lores).build();
        } else if (id == 1) {
            item = new ItemBuilder(achievementType.getIcon()).name(CC.translate(" ")).lore(lores).build();
        } else if (id == 2) {
            item = new ItemBuilder(achievementType.getIcon()).name(CC.translate(" ")).lore(lores).build();
        } else if (id == 3) {
            item = new ItemBuilder(achievementType.getIcon()).name(CC.translate(" ")).lore(lores).build();
        } else {
            item = new ItemBuilder(Material.DIAMOND).name(CC.translate(" ")).lore(lores).build();
        }
        
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        if (id == 3 && achievementType != null) {
            CacheData.ACHIEVEMENT_MENU.put(player, achievementType);
            new AchievementsSubMenu(parent).openMenu(player);
        }
    }
}
