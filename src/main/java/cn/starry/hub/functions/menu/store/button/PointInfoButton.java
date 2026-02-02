package cn.starry.hub.functions.menu.store.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PointInfoButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("   &b璀璨星尘   ");
        lores.add(" ");
        lores.add("   &7璀璨星尘比例   ");
        lores.add("   &f1元 &7=&f 10 璀璨星尘   ");
        lores.add(" ");

        return new ItemBuilder(Material.GUNPOWDER).name(CC.translate(" ")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, org.bukkit.event.inventory.ClickType clickType, int hotbarButton, ItemStack currentItem) {
        player.closeInventory();
        player.sendMessage(CC.translate("&c暂不支持充值服务器"));
    }
}
