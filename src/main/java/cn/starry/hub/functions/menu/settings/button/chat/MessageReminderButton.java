package cn.starry.hub.functions.menu.settings.button.chat;

import cn.starry.core.Core;
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

public class MessageReminderButton extends Button {

    public boolean isButton = false;
    public Menu parent;
    public int typeID;

    public MessageReminderButton(boolean isButton, Menu parent, int typeID) {
        this.isButton = isButton;
        this.parent = parent;
        this.typeID = typeID;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        boolean isChoose = Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"settings_chat_reminder").equalsIgnoreCase("TRUE");

        if (isChoose) {
            lores.add("   &a提及提醒   ");
            lores.add("   &7设置你在聊天中被提及时   ");
            lores.add("   &7是否开启声音提示和文本高亮   ");
            lores.add(" ");
            lores.add("   &7当前 &a已启用   ");
            lores.add(" ");
            lores.add("   &c- &f点击禁用   ");
            lores.add(" ");
        } else {
            lores.add("   &c提及提醒   ");
            lores.add("   &7设置你在聊天中被提及时   ");
            lores.add("   &7是否开启声音提示和文本高亮   ");
            lores.add(" ");
            lores.add("   &7当前 &c已禁用   ");
            lores.add(" ");
            lores.add("   &a+ &f点击启用   ");
            lores.add(" ");
        }

        return new ItemBuilder(isButton ? Material.JUKEBOX : (isChoose ? Material.LIME_DYE : Material.GRAY_DYE)).name((isChoose ? "&a" : "&c") + " ").lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        MongoDB mongoDB = Core.getInstance().getMongoDB();
        if (mongoDB.getPlayerData(player.getUniqueId(),"settings_chat_reminder").equalsIgnoreCase("TRUE")) {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_chat_reminder","FALSE");
            new SettingsMenu(parent,typeID).openMenu(player);
        } else {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_chat_reminder","TRUE");
            new SettingsMenu(parent,typeID).openMenu(player);
        }
    }
}
