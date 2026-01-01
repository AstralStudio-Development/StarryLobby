package cn.starry.hub.functions.menu.profile.button;

import cn.starry.core.api.enums.GameOwned;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.NickUtil;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.utils.ConnecterUtil;
import cn.starry.hub.utils.menu.Button;
import me.clip.placeholderapi.PlaceholderAPI;
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

public class StoreButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(PlaceholderAPI.setPlaceholders(player, "&7从这里查看货摊！"));
        lores.add("&7在这获取 ");
        lores.add("&7权益 道具等...");
        lores.add(" ");
        if (!NickUtil.isNicked(player.getUniqueId())) {
            lores.add(PlaceholderAPI.setPlaceholders(player, StarryLobby.getInstance().economy ? "&7璀璨星尘: &b%playerpoints_points%" : "&7璀璨星尘: &8已禁用"));
            lores.add(" ");
        }
        lores.add("&e点击查看");

        return new ItemBuilder(Material.NAME_TAG).name(CC.translate("&a货摊")).lore(lores).shiny().build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        player.closeInventory();
    }
}
