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

public class StaffTypeButton extends Button {

    private final String name;
    private final String description;
    private final String color;

    public StaffTypeButton(String name, String description, String color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7");
        lores.add(description);
        lores.add(" ");

        return new ItemBuilder(Material.valueOf(color + "_STAINED_GLASS")).name(CC.translate(name)).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {

    }
}

