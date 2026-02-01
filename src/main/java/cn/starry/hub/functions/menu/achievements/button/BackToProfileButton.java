package cn.starry.hub.functions.menu.achievements.button;

import cn.starry.hub.functions.menu.buttons.AchievementsButtons;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class BackToProfileButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new AchievementsButtons().BackToProfile();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new PlayerProfileMenu().openMenu(player);
    }
}
