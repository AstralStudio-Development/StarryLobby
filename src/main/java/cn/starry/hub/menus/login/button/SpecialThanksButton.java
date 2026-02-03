package cn.starry.hub.menus.login.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.SkullUtil;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SpecialThanksButton extends Button {

    private final String name;
    private final String time;
    private final String texture;

    public SpecialThanksButton(String name, String time, String texture) {
        this.name = name;
        this.time = time;
        this.texture = texture;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemStack item = SkullUtil.makeModernSkull(texture);
        ArrayList<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&7时间节点&6 " + time);
        return new ItemBuilder(item).name("&e" + name).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {

    }
}

