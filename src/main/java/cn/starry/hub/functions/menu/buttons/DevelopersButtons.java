package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.utils.SkullUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DevelopersButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack HeadButton(Player player, String name, boolean isEnable) {
        if (isEnable) {
            if (name.equalsIgnoreCase("duduskz")) {
                item = SkullUtil.makeTextureSkull("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmU1OTE2MTg5Y2E2ZjQzNmNiMDMxZTBiYTMyOWZjNTk1ZGQ1ZDIxZTM0YTAzNWJjYWQxOTdmMWRiOTA4ZmQ3MiJ9fX0=");
            } else {
                item = new ItemBuilder(Material.SKULL_ITEM).durability(3).build();
            }
        } else {
            item = SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTNkNzU3NWE2ZDhmZWZmYjIyNzgzZTFlMmI2ZGZiZTI5OTZmYWRiYzk3NzlmODhlNzQyYTAxMmM2MmFhIn19fQ==");
        }
        meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.color(isEnable ? "&e" + name : "&8???"));
        item.setItemMeta(meta);
        return item;
    }

}
