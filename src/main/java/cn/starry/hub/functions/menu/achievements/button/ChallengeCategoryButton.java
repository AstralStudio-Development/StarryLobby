package cn.starry.hub.functions.menu.achievements.button;

import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.AchievementType;
import cn.starry.hub.functions.menu.achievements.ChallengePageMenu;
import cn.starry.hub.functions.menu.buttons.AchievementsButtons;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ChallengeCategoryButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        AchievementType type = CacheData.ACHIEVEMENT_MENU.get(player);
        return new AchievementsButtons().ChallengeButton(player, type);
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new ChallengePageMenu().openMenu(player);
    }
}
