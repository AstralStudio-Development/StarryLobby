package cn.starry.hub.menus.achievements.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.menus.profile.PlayerProfileMenu;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BackToProfileButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("   &a返回   ");
        lores.add(" ");
        lores.add("   &7返回至个人档案   ");
        lores.add(" ");
        return new ItemBuilder(Material.ARROW).name(CC.translate(" ")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new PlayerProfileMenu().openMenu(player);
    }
}

