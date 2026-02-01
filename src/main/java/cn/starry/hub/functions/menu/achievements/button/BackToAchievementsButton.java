package cn.starry.hub.functions.menu.achievements.button;

import cn.starry.core.api.data.CacheData;
import cn.starry.hub.functions.menu.achievements.AchievementsMenu;
import cn.starry.hub.functions.menu.buttons.AchievementsButtons;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class BackToAchievementsButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new AchievementsButtons().Back(null);
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        CacheData.ACHIEVEMENT_MENU.remove(player);
        new AchievementsMenu().openMenu(player);
    }
}
