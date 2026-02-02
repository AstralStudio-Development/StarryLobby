package cn.starry.hub.functions.menu.login.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.SkullUtil;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class StaffHeadButton extends Button {

    private final String name;
    private final boolean isEnable;
    private final String role;
    private final String quote;
    private final String texture;

    public StaffHeadButton(String name, boolean isEnable, String role, String quote, String texture) {
        this.name = name;
        this.isEnable = isEnable;
        this.role = role;
        this.quote = quote;
        this.texture = texture;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemStack item = isEnable ? SkullUtil.makeModernSkull(texture) : SkullUtil.makeModernSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTNkNzU3NWE2ZDhmZWZmYjIyNzgzZTFlMmI2ZGZiZTI5OTZmYWRiYzk3NzlmODhlNzQyYTAxMmM2MmFhIn19fQ==");
        ArrayList<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7职务&f " + role);
        if (!quote.equals("")) {
            lores.add(" ");
            lores.add(" &7\"" + quote + "\" ");
            lores.add(" ");
        } else {
            lores.add(" ");
            lores.add(" &7\"" + "无" + "\" ");
            lores.add(" ");
        }
        return new ItemBuilder(item).name("&e" + name).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {

    }
}
