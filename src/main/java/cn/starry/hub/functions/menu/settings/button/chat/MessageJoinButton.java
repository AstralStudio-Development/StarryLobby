package cn.starry.hub.functions.menu.settings.button.chat;

import cn.starry.core.Core;
import cn.starry.core.api.enums.Permission;
import cn.starry.core.database.MongoDB;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.functions.menu.settings.SettingsMenu;
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
 * @Date: 2025/8/24
 */

public class MessageJoinButton extends Button {

    public boolean isButton = false;
    public Menu parent;
    public int typeID;

    public MessageJoinButton(boolean isButton, Menu parent, int typeID) {
        this.isButton = isButton;
        this.parent = parent;
        this.typeID = typeID;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        boolean isChoose = Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"settings_chat_joinmessage").equalsIgnoreCase("TRUE");

        if (isChoose ) {
            lores.add("   &a加入信息   ");
            lores.add(" ");
            lores.add("   &7设置是否在你   ");
            lores.add("   &7加入大厅时发送加入消息   ");
            lores.add(" ");
        } else {
            lores.add("   &c加入信息   ");
            lores.add(" ");
            lores.add("   &7设置是否在你   ");
            lores.add("   &7加入大厅时发送加入消息   ");
            lores.add(" ");
        }
        if (player.hasPermission(Permission.PREMIUM.getNode())) {
            lores.add("   &7此功能需要 &bStar&c+   ");
            lores.add("");
        } else {
            lores.add("   &7此功能需要 &bStar&c+   ");
            lores.add("");
        }
        if (isChoose) {
            lores.add("   &7当前 &a已启用   ");
            lores.add(" ");
            lores.add("   &c- &f点击禁用   ");
            lores.add(" ");
        } else {
            lores.add("   &7当前 &c已禁用   ");
            lores.add(" ");
            lores.add("   &a+ &f点击启用   ");
            lores.add(" ");
        }

        return new ItemBuilder(isButton ? Material.PAPER : (isChoose ? Material.LIME_DYE : Material.GRAY_DYE)).name((isChoose ? "&a" : "&c") + " ").lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        MongoDB mongoDB = Core.getInstance().getMongoDB();
        if (mongoDB.getPlayerData(player.getUniqueId(),"settings_chat_joinmessage").equalsIgnoreCase("TRUE")) {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_chat_joinmessage","FALSE");
            new SettingsMenu(parent,typeID).openMenu(player);
        } else {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_chat_joinmessage","TRUE");
            new SettingsMenu(parent,typeID).openMenu(player);
        }
    }
}
