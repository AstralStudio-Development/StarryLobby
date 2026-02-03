package cn.starry.hub.menus.settings.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.menus.settings.SettingsMenu;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * @Author: Stalyer
 * @Date: 2025/8/24
 */

public class SettingTypeButton extends Button {

    public Menu parent;
    public boolean isAlready = false;
    public int typeID;

    public SettingTypeButton(Menu parent, boolean isAlready, int typeID) {
        this.parent = parent;
        this.isAlready = isAlready;
        this.typeID = typeID;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemStack item;
        Material material;
        String typeDisplayName;

        switch (typeID) {
            case 0:
                material = Material.NETHER_STAR;
                typeDisplayName = "&a大厅设置";
                break;
            case 1:
                material = Material.PAPER;
                typeDisplayName = "&a聊天设置";
                break;
            case 2:
                material = Material.IRON_BARS;
                typeDisplayName = "&a社交设置";
                break;
            default:
                material = Material.BARRIER;
                typeDisplayName = "Unknown";
                break;
        }

            item = new ItemBuilder(material).name(typeDisplayName).build();

        return item;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        new SettingsMenu(parent,typeID).openMenu(player);
    }
}

