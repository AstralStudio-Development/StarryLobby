package cn.starry.hub.functions.menu.profile.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.achievements.AchievementsMenu;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AchievementButton extends Button {

    private Menu parent;

    public AchievementButton(Menu parent) {
        this.parent = parent;
    }

    public AchievementButton() {
        this(null);
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("   &a游戏成就   ");
        lores.add(" ");
        lores.add("   &7查看你成就解锁的进度   ");
        lores.add("   &7以及成就点数   ");
        lores.add(" ");
        lores.add("   &a+ &f点击查看   ");
        lores.add(" ");

        return new ItemBuilder(Material.DIAMOND).name(CC.translate(" ")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new AchievementsMenu(parent).openMenu(player);
    }
}
