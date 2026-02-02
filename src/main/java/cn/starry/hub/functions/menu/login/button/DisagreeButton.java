package cn.starry.hub.functions.menu.login.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DisagreeButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("&f点击将被踢出服务器"));
        lores.add(CC.translate("&c同时,代表您不同意协议内容"));

        return new ItemBuilder(Material.RED_TERRACOTTA).name(CC.translate("&b不同意协议内容")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        player.kickPlayer(ChatColor.RED + "你被踢出了服务器" + "\n" + "" + "\n" + ChatColor.GRAY + "原因: 不同意玩家协议" + "\n" + "" + "\n" + "当您想要进入服务器时,必须同意我们的玩家协议"+ "\n" + "其目的在于保障我们与玩家的权益");
    }
}
