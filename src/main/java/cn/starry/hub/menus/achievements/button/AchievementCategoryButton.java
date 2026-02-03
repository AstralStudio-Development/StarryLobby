package cn.starry.hub.menus.achievements.button;

import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.hub.menus.achievements.AchievementsSubMenu;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AchievementCategoryButton extends Button {

    private final AchievementType achievementType;
    private final Menu parent;

    public AchievementCategoryButton(AchievementType achievementType, Menu parent) {
        this.achievementType = achievementType;
        this.parent = parent;
    }

    public AchievementCategoryButton(AchievementType achievementType) {
        this(achievementType, null);
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new TotalCompletionButton(achievementType, 3, parent).getButtonItem(player);
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        CacheData.ACHIEVEMENT_MENU.put(player, achievementType);
        new AchievementsSubMenu(parent).openMenu(player);
    }
}

