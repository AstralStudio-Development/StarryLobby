package cn.starry.hub.functions.menu.settings.button.lobby;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
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

public class LobbyLauncherButton extends Button {

    public boolean isButton = false;
    public Menu parent;
    public int typeID;

    public LobbyLauncherButton(boolean isButton, Menu parent, int typeID) {
        this.isButton = isButton;
        this.parent = parent;
        this.typeID = typeID;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        boolean isChoose = Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"settings_lobby_launcher").equalsIgnoreCase("TRUE");

        if (isChoose) {
            lores.add("   &a虚空弹射器   ");
            lores.add(" ");
            lores.add("   &7设置当你进入虚空时   ");
            lores.add("   &7是否使用虚空弹射器   ");
            lores.add(" ");
            lores.add("   &7在当前状态下   ");
            lores.add("   &7将使用虚空弹射器   ");
            lores.add("   &7把你重新弹射回大厅表面   ");
            lores.add(" ");
            lores.add("   &7当前 &a已启用   ");
            lores.add(" ");
            lores.add("   &c- &f点击禁用   ");
            lores.add(" ");
        } else {
            lores.add("   &c虚空弹射器   ");
            lores.add(" ");
            lores.add("   &7设置当你进入虚空时   ");
            lores.add("   &7是否使用虚空弹射器   ");
            lores.add(" ");
            lores.add("   &7在当前状态下   ");
            lores.add("   &7会将你直接传送回出生点   ");
            lores.add(" ");
            lores.add("   &7当前 &c已禁用   ");
            lores.add(" ");
            lores.add("   &a+ &f点击启用   ");
            lores.add(" ");
        }

        return new ItemBuilder(isButton ? Material.SLIME_BLOCK : (isChoose ? Material.LIME_DYE : Material.GRAY_DYE)).name((isChoose ? "&a" : "&c") + "   ").lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        MongoDB mongoDB = Core.getInstance().getMongoDB();
        if (mongoDB.getPlayerData(player.getUniqueId(),"settings_lobby_launcher").equalsIgnoreCase("TRUE")) {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_lobby_launcher","FALSE");
            CacheData.LAUNCHER.put(player,false);
            new SettingsMenu(parent,typeID).openMenu(player);
        } else {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_lobby_launcher","TRUE");
            CacheData.LAUNCHER.put(player,true);
            new SettingsMenu(parent,typeID).openMenu(player);
        }
    }
}
