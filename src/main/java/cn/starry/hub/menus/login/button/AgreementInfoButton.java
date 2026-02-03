package cn.starry.hub.menus.login.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AgreementInfoButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("&7这是一份玩家协议"));
        lores.add(CC.translate("&7在您进入服务器前,请阅读此协议"));
        lores.add(CC.translate(""));
        lores.add(CC.translate("&e点击查看"));

        return new ItemBuilder(Material.NETHER_STAR).name(CC.translate("&b玩家协议")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        player.sendMessage(CC.translate("&b↓ 点击打开玩家协议 ↓"));
        player.sendMessage(CC.translate("&7\"很明显，他们已经准备好了。\""));
        player.sendMessage(CC.translate("&b&nhttps://www.yume.games/eula/"));
        player.closeInventory();
    }
}

