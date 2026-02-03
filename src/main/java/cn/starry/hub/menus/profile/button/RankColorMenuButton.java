package cn.starry.hub.menus.profile.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.menus.profile.RankColorMenu;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RankColorMenuButton extends Button {

    private final Menu parent;

    public RankColorMenuButton(Menu parent) {
        this.parent = parent;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("   &aStar+等级颜色   ");
        lores.add(" ");
        lores.add("   &7拥有 &bStar&c+   ");
        lores.add("   &7的玩家可以切换“+”的颜色   ");
        lores.add("");
        lores.add("   &a+ &f点击切换   ");
        lores.add("");

        return new ItemBuilder(Material.LEGACY_INK_SACK).name(CC.translate(" ")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new RankColorMenu(parent).openMenu(player);
    }
}

