package cn.starry.hub.functions.menu.achievements;

import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.achievements.button.BackToAchievementsButton;
import cn.starry.hub.functions.menu.achievements.button.ChallengeCategoryButton;
import cn.starry.hub.functions.menu.achievements.button.GradeCategoryButton;
import cn.starry.hub.functions.menu.achievements.button.SubMenuSummaryButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AchievementsSubMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        AchievementType achievementType = CacheData.ACHIEVEMENT_MENU.get(player);
        return CC.translate("                  " + achievementType.getDisplayName());
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(21, new ChallengeCategoryButton());
        buttons.put(23, new GradeCategoryButton());

        buttons.put(39, new BackToAchievementsButton());
        buttons.put(41, new SubMenuSummaryButton());

        return buttons;
    }
    
    @Override
    public int getSize() {
        return 6 * 9; // 36 slots used in original
    }
}

