package cn.starry.hub.utils.menu.buttons;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
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
        List<String> lores = new ArrayList<>();
        if (back == null) {
            lores.add("   &c关闭   ");
            lores.add(" ");
            return new ItemBuilder(Material.BARRIER).name(CC.translate(" ")).lore(lores).build();
        }

        lores.add("   &a返回   ");
        lores.add(" ");

        String title = back.getTitle(player);
        if (title != null) {
            title = title.replaceAll("\\s+", "").replaceAll("(?i)[&§][0-9a-fk-or]", "");
        } else {
            title = "";
        }

        lores.add("   &7返回至" + title + "   ");
        lores.add(" ");

        return new ItemBuilder(Material.ARROW).name(CC.translate(" ")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        if (back == null) {
            player.closeInventory();
        } else {
            this.back.openMenu(player);
        }
    }

}
