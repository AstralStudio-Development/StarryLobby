package cn.starry.hub.functions.menu.achievements;

import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.achievements.button.AchievementCategoryButton;
import cn.starry.hub.functions.menu.achievements.button.AchievementSummaryButton;
import cn.starry.hub.functions.menu.achievements.button.BackToProfileButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AchievementsMenu extends Menu {

    private final Menu parent;

    public AchievementsMenu(Menu parent) {
        this.parent = parent;
    }

    public AchievementsMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("                  &0成就");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(13, new AchievementCategoryButton(AchievementType.GENERAL, this));
        buttons.put(20, new AchievementCategoryButton(AchievementType.BEDWARS, this));
        buttons.put(21, new AchievementCategoryButton(AchievementType.PIT, this));
        buttons.put(23, new AchievementCategoryButton(AchievementType.SKYWARS, this));
        buttons.put(24, new AchievementCategoryButton(AchievementType.DUELS, this));

        if (parent != null) {
            buttons.put(39, new BackButton(parent));
        } else {
            buttons.put(39, new BackToProfileButton());
        }
        
        buttons.put(41, new AchievementSummaryButton());

        return buttons;
    }
    
    @Override
    public int getSize() {
        return 54; // Standard size
    }
}
