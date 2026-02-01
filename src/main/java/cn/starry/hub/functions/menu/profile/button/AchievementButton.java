package cn.starry.hub.functions.menu.profile.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.achievements.AchievementsMenu;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AchievementButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7查看你成就解锁的进度");
        lores.add("&7以及成就点数");
        lores.add(" ");
        lores.add("&e点击查看你的成就！");

        return new ItemBuilder(Material.DIAMOND).name(CC.translate("&a成就")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new AchievementsMenu().openMenu(player);
    }
}
