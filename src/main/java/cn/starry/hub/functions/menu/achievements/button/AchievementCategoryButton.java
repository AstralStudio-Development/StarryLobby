package cn.starry.hub.functions.menu.achievements.button;

import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.hub.functions.menu.achievements.AchievementsSubMenu;
import cn.starry.hub.functions.menu.buttons.AchievementsButtons;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AchievementCategoryButton extends Button {

    private final AchievementType achievementType;

    public AchievementCategoryButton(AchievementType achievementType) {
        this.achievementType = achievementType;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new AchievementsButtons().TotalButton(player, achievementType, 3);
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        CacheData.ACHIEVEMENT_MENU.put(player, achievementType);
        new AchievementsSubMenu().openMenu(player);
    }
}
