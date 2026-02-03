package cn.starry.hub.menus.achievements.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ToOtherPageButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("   &a前往分级成就   ");
        lores.add(" ");
        lores.add("   &7点击查看综合分级成就   ");
        lores.add("   &c此功能仍在开发,将于未来一段时间内上线   ");
        lores.add(" ");
        return new ItemBuilder(Material.DIAMOND_BLOCK).name(CC.translate(" ")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {

    }
}

