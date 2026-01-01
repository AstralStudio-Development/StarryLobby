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
public class GlassButton extends Button {

    private String up;
    private String down;
    private boolean isSelected;

    public GlassButton(String up, String down, boolean isSelected) {
        this.up = up;
        this.down = down;
        this.isSelected = isSelected;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();
        lore.add(down);
        return new ItemBuilder(isSelected ? Material.LIME_STAINED_GLASS_PANE : Material.GRAY_STAINED_GLASS_PANE).name(up).lore(lore).build();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb, ItemStack currentItem) {
    }

}
