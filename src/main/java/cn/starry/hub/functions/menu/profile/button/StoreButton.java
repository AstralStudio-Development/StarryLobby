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
        lores.add("   &a货摊   ");
        lores.add(" ");
        lores.add("   &7你可以从这获得   ");
        lores.add("   &7各种各样的权益内容   ");
        lores.add("   &f服务器会员   ");
        lores.add("   &f硬币倍增器   ");
        lores.add("   &f拓展内容等   ");
        lores.add(" ");
        if (!NickUtil.isNicked(player.getUniqueId())) {
            lores.add(PlaceholderAPI.setPlaceholders(player, StarryLobby.getInstance().economy ? "   &7璀璨星尘 &b%playerpoints_points%   " : "   &7璀璨星尘 &8已禁用   "));
        }
        lores.add(" ");
        lores.add("   &a+ &f点击查看   ");
        lores.add(" ");

        return new ItemBuilder(Material.NAME_TAG).name(CC.translate(" ")).lore(lores).shiny().build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new RankStoreMenu().openMenu(player);
    }
}
