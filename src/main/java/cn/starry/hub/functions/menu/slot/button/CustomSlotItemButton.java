package cn.starry.hub.functions.menu.slot.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CustomSlotItemButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7此槽位可用于快速加入你最喜欢的游戏或模式");
        lores.add(" ");
        lores.add("&7你随时可以右键修改此槽位");

        return new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("&e自定义槽位").lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {

    }
}
