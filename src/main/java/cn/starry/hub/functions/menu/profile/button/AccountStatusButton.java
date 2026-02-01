package cn.starry.hub.functions.menu.profile.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import me.rin.bukkit.api.InternalHook;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AccountStatusButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7查看你的处罚记录和当前账号状态");
        lores.add(" ");
        lores.add("&e点击查看！");

        return new ItemBuilder(Material.ANVIL).name(CC.translate("&a账号状态")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        InternalHook.openMenu(player,"AccountStatus");
    }
}
