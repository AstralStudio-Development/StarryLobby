package cn.starry.hub.functions.menu.bedwars.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CosmeticsButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" &7查看你已拥有的起床战争特效");
        lores.add(" &7或是通过代币来购买它们。");

        return new ItemBuilder(Material.ARMOR_STAND).name(CC.translate(" &a起床战争特效")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 0.5F);
        player.sendMessage(CC.translate("&c将于数个版本内上线。"));
    }
}
