package cn.starry.hub.menus.profile.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ShineButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("   &a人物发光   ");
        lores.add(" ");
        lores.add("   &7你的角色将会基于   ");
        lores.add("   &7你的会员颜色染上轮廓   ");
        lores.add("");
        lores.add("   &c此功能将在数个版本内上线   ");
        lores.add("");

        return new ItemBuilder(Material.PRISMARINE_CRYSTALS).name(CC.translate(" ")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {

    }
}

