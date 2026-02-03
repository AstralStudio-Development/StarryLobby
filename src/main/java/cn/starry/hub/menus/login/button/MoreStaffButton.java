package cn.starry.hub.menus.login.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.menus.login.ThanksMenu;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MoreStaffButton extends Button {

    private final Menu parent;

    public MoreStaffButton(Menu parent) {
        this.parent = parent;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7已离职/曾经合作的名单列表/第三方社区支持");

        return new ItemBuilder(Material.RED_WOOL).name(CC.translate("&b特别感谢名单")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new ThanksMenu(parent).openMenu(player);
    }
}

