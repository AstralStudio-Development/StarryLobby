package cn.starry.hub.menus.achievements.button;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.functions.achievement.AbstractAchievement;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.menus.achievements.ChallengePageMenu;
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

public class ChallengeCategoryButton extends Button {

    private final Menu parent;

    public ChallengeCategoryButton(Menu parent) {
        this.parent = parent;
    }

    public ChallengeCategoryButton() {
        this(null);
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        AchievementType achievementType = CacheData.ACHIEVEMENT_MENU.get(player);
        List<String> lores = new ArrayList<>();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
        int unlockedAchievements = 0;
        int totalAchievements = 0;
        int availablePoints = 0;
        int totalPoints = 0;
        for (AbstractAchievement achievements : Core.getInstance().getAchievementFactory().getAchievements()) {
            if (achievements.getType().equals(achievementType)) {
                totalAchievements++;
                totalPoints = totalPoints + achievements.getPoints();
                if (Core.getInstance().getMongoDB().getAchievementData(player.getUniqueId(),"achievements").contains(achievements.getInternalName())) {
                    unlockedAchievements++;
                    availablePoints = availablePoints + achievements.getPoints();
                }
            }
        }
        lores.add("   &a挑战成就   ");
        lores.add("   &8" + achievementType.getDisplayName() + "   ");
        lores.add(" ");
        lores.add("   &7已解锁 &b" + unlockedAchievements + "&7/&b" + totalAchievements + " &8(" + numberFormat.format((float) unlockedAchievements / (float) totalAchievements * 100) + "%)   ");
        lores.add("   &7点数 &e" + availablePoints + "&7/&e" + totalPoints + " &8(" + numberFormat.format((float) availablePoints / (float) totalPoints * 100) + "%)   ");
        lores.add(" ");
        lores.add("   &7挑战成就可一次性完成   ");
        lores.add(" ");
        lores.add("   &a+ &f点击查看成就   ");
        lores.add(" ");

        ItemStack item = new ItemBuilder(Material.DIAMOND).name(CC.translate(" ")).lore(lores).build();
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        // parent here is the AchievementsSubMenu instance passed from AchievementsSubMenu
        new ChallengePageMenu(parent).openMenu(player);
    }
}

