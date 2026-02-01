package cn.starry.hub.functions.menu.profile.button;

import cn.starry.core.Core;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.NickUtil;
import cn.starry.core.utils.RankUtil;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import cn.starry.hub.utils.menu.Button;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ProfileButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(PlaceholderAPI.setPlaceholders(player, "&7会员等级: " + RankUtil.getDisplayRankById(Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rank"),player.getUniqueId())));
        lores.add(" ");
        lores.add(PlaceholderAPI.setPlaceholders(player, "&7成就点数: &e" + Core.getInstance().getMongoDB().getAchievementPoints(player.getUniqueId(),"points")));
        if (!NickUtil.isNicked(player.getUniqueId())) {
            lores.add(PlaceholderAPI.setPlaceholders(player, "&7神秘之尘: &b%gadgetsmenu_mystery_dust%"));
            lores.add(PlaceholderAPI.setPlaceholders(player, "&7人品值: &d0"));
            lores.add(PlaceholderAPI.setPlaceholders(player, StarryLobby.getInstance().economy ? "&7璀璨星尘: &b%playerpoints_points%" : "&7璀璨星尘: &8已禁用"));
        }

        return new ItemBuilder(Material.PLAYER_HEAD).name(CC.translate("&a角色信息")).durability(3).setModernSkullOwner(player).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new PlayerProfileMenu().openMenu(player);
    }
}
