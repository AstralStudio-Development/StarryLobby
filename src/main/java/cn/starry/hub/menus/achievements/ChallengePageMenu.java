package cn.starry.hub.menus.achievements;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.functions.achievement.AbstractAchievement;
import cn.starry.hub.menus.achievements.button.ChallengeAchievementButton;
import cn.starry.hub.menus.achievements.button.ToOtherPageButton;
import cn.starry.hub.menus.achievements.button.TotalCompletionButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChallengePageMenu extends Menu {

    private final Menu parent;

    public ChallengePageMenu(Menu parent) {
        this.parent = parent;
    }

    public ChallengePageMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return "               &0挑战成就";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        AchievementType achievementType = CacheData.ACHIEVEMENT_MENU.get(player);

        List<AbstractAchievement> achievements = new ArrayList<>(Core.getInstance().getAchievementFactory().getAchievements());
        int slotIndex = 0;
        Integer[] slots = getItemsSlots();
        
        for (AbstractAchievement achievement : achievements) {
            if (achievement.getType().equals(achievementType) && slotIndex < slots.length) {
                buttons.put(slots[slotIndex], new ChallengeAchievementButton(achievement));
                slotIndex++;
            }
        }

        buttons.put(48, new BackButton(parent));
        buttons.put(49, new TotalCompletionButton(achievementType, 1, this)); // Pass 'this' (ChallengePageMenu) as parent? 
        // No, TotalCompletionButton logic for id=1 is just display. 
        // But if id=3 it opens submenu. 
        // Here id=1. It doesn't use parent. 
        // But to satisfy constructor and be safe:
        // Wait, TotalCompletionButton constructor I added: (type, id, parent).
        // And overload (type, id) -> calls (type, id, null).
        // So (achievementType, 1) is valid.
        // User said "parent没有具体传参". 
        // Maybe they want me to use TotalCompletionButton inside AchievementCategoryButton instead of duplicated logic?
        // Or maybe they mean in AchievementsMenu/SubMenu I am not passing something correctly?
        
        // Let's assume user wants me to use TotalCompletionButton inside AchievementCategoryButton and SubMenuSummaryButton to avoid duplication.
        
        buttons.put(50, new ToOtherPageButton());

        return buttons;
    }

    @Override
    public int getSize() {
        return 54;
    }

    public Integer[] getItemsSlots() {
        return new Integer[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};
    }
}

