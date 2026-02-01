package cn.starry.hub.functions.menu.profile.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.settings.SettingsMenu;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SettingsButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7允许你编辑和配置各种各样的个人设置");
        lores.add(" ");
        lores.add("&e点击以编辑你的设定！");

        return new ItemBuilder(Material.LEGACY_REDSTONE_COMPARATOR).name(CC.translate("&a设置及可见性")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new SettingsMenu(null,0).openMenu(player);
    }
}
