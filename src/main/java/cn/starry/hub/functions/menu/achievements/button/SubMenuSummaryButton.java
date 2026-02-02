package cn.starry.hub.functions.menu.achievements.button;

import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SubMenuSummaryButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        AchievementType achievementType = CacheData.ACHIEVEMENT_MENU.get(player);
        return new TotalCompletionButton(achievementType, 0).getButtonItem(player);
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        // No action
    }
}
