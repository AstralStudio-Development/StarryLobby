package cn.starry.hub.utils.menu.buttons;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Stalyer
 */
public class BackButton extends Button {

    private final Menu back;

    public BackButton(Menu back) {
        this.back = back;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();
        lore.add("&7至" + back.getTitle(player));

        return new ItemBuilder(Material.ARROW).name("&a返回").lore(lore).build();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb, ItemStack currentItem) {
        Button.playNeutral(player);

        this.back.openMenu(player);
    }

}
