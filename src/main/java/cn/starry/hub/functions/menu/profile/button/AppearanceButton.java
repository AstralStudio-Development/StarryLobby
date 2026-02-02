package cn.starry.hub.functions.menu.profile.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.profile.CustomViewMenu;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AppearanceButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("   &a自定义外观   ");
        lores.add(" ");
        lores.add("   &7为你的形象自定义外观   ");
        lores.add("   &fStar+ 会员等级颜色   ");
        lores.add("   &f人物发光   ");
        lores.add(" ");
        lores.add("   &a+ &f点击查看   ");
        lores.add(" ");

        return new ItemBuilder(Material.LEATHER_CHESTPLATE).flags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DYE).name(CC.translate(" ")).lore(lores).setLetherColor(Color.BLUE).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new CustomViewMenu().openMenu(player);
    }
}
