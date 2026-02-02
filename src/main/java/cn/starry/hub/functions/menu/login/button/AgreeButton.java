package cn.starry.hub.functions.menu.login.button;

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

public class AgreeButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("&f点击即可进入服务器"));
        lores.add(CC.translate("&a同时,代表您同意协议内容"));

        return new ItemBuilder(Material.GREEN_TERRACOTTA).name(CC.translate("&b同意协议内容")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        ConnecterUtil.connect(player, "MainLobby");
    }
}
