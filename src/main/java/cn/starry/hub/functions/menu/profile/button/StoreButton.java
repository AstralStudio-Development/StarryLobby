package cn.starry.hub.functions.menu.profile.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.NickUtil;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.functions.menu.store.RankStoreMenu;
import cn.starry.hub.utils.menu.Button;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class StoreButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(PlaceholderAPI.setPlaceholders(player, "&7从这里查看货摊！"));
        lores.add(" ");
        if (!NickUtil.isNicked(player.getUniqueId())) {
            lores.add(PlaceholderAPI.setPlaceholders(player, StarryLobby.getInstance().economy ? "&7璀璨星尘: &b%playerpoints_points%" : "&7璀璨星尘: &8已禁用"));
            lores.add(" ");
        }
        lores.add("&c前往大厅内的货摊NPC查看");

        return new ItemBuilder(Material.NAME_TAG).name(CC.translate("&a货摊")).lore(lores).shiny().build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new RankStoreMenu().openMenu(player);
    }
}
