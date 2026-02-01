package cn.starry.hub.functions.menu.achievements.button;

import cn.starry.hub.functions.menu.buttons.AchievementsButtons;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AchievementSummaryButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new AchievementsButtons().TotalButton(player, null, 4);
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        // Do nothing
    }
}
