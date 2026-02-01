package cn.starry.hub.functions.menu.achievements;

import cn.starry.core.api.enums.AchievementType;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.achievements.button.AchievementCategoryButton;
import cn.starry.hub.functions.menu.achievements.button.AchievementSummaryButton;
import cn.starry.hub.functions.menu.achievements.button.BackToProfileButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AchievementsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.translate("                  &0成就");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(1, new AchievementCategoryButton(AchievementType.GENERAL));
        buttons.put(10, new AchievementCategoryButton(AchievementType.BEDWARS));
        buttons.put(11, new AchievementCategoryButton(AchievementType.PIT));
        buttons.put(12, new AchievementCategoryButton(AchievementType.SKYWARS));
        buttons.put(13, new AchievementCategoryButton(AchievementType.DUELS));

        buttons.put(30, new BackToProfileButton());
        buttons.put(31, new AchievementSummaryButton());

        return buttons;
    }
    
    @Override
    public int getSize() {
        return 6 * 9; // Original was 36 (4*9), but wait.
        // Original init: Bukkit.createInventory(null, 36, title);
        // Buttons at 30, 31.
        // So 36 slots is correct (indices 0-35).
        // SettingsMenu returned 6*9=54.
        // Let's stick to 36 (4*9) if buttons fit. 
        // 30 and 31 fit in 36 slots.
    }
}

