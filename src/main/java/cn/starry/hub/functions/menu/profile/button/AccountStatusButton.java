package cn.starry.hub.functions.menu.profile.button;

import cn.starry.core.api.enums.GameOwned;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.ConnecterUtil;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Stalyer
 * @Date: 2025/8/20
 */

public class AccountStatusButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();
        lore.add("&7查看你的处罚记录和当前账号状态");
        lore.add(" ");
        lore.add("&e点击查看！");

        return new ItemBuilder(Material.ANVIL).name(CC.translate("&a账号状态")).lore(lore).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        player.closeInventory();
    }
}
