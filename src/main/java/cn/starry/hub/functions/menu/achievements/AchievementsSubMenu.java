package cn.starry.hub.functions.menu.achievements;

import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.achievements.button.ChallengeCategoryButton;
import cn.starry.hub.functions.menu.achievements.button.GradeCategoryButton;
import cn.starry.hub.functions.menu.achievements.button.SubMenuSummaryButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AchievementsSubMenu extends Menu {

    private final Menu parent;

    public AchievementsSubMenu(Menu parent) {
        this.parent = parent;
    }

    public AchievementsSubMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        AchievementType achievementType = CacheData.ACHIEVEMENT_MENU.get(player);
        return CC.translate("                " + achievementType.getDisplayName());
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(21, new ChallengeCategoryButton(this));
        buttons.put(23, new GradeCategoryButton());

        buttons.put(39, new BackButton(parent));
        buttons.put(41, new SubMenuSummaryButton());

        return buttons;
    }
    
    @Override
    public int getSize() {
        return 54;
    }
}
