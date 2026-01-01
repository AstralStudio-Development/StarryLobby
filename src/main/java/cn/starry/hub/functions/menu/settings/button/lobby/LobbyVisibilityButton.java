package cn.starry.hub.functions.menu.settings.button.lobby;

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

import static org.bukkit.Bukkit.getServer;

/**
 * @Author: Stalyer
 * @Date: 2025/8/24
 */

public class LobbyVisibilityButton extends Button {

    public boolean isButton = false;
    public Menu parent;
    public int typeID;

    public LobbyVisibilityButton(boolean isButton, Menu parent, int typeID) {
        this.isButton = isButton;
        this.parent = parent;
        this.typeID = typeID;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        boolean isChoose = Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"settings_lobby_show").equalsIgnoreCase("TRUE");
        lores.add("&7设置玩家是否可见。");
        lores.add(" ");
        if (isChoose) {
            lores.add("&7当前 &a已启用");
            lores.add(" ");
            lores.add("&e点击禁用！");
        } else {
            lores.add("&7当前 &c已禁用");
            lores.add(" ");
            lores.add("&e点击启用！");
        }

        return new ItemBuilder(isButton ? Material.CLOCK : (isChoose ? Material.LIME_DYE : Material.GRAY_DYE)).name((isChoose ? "&a" : "&c") + "玩家可见性").lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        MongoDB mongoDB = Core.getInstance().getMongoDB();
        if (mongoDB.getPlayerData(player.getUniqueId(),"settings_lobby_show").equalsIgnoreCase("TRUE")) {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_lobby_show","FALSE");
            for (Player p : getServer().getOnlinePlayers()) {
                player.hidePlayer(p);
            }
            new SettingsMenu(parent,typeID).openMenu(player);
        } else {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_lobby_show","TRUE");
            for (Player p : getServer().getOnlinePlayers()) {
                player.showPlayer(p);
            }
            new SettingsMenu(parent,typeID).openMenu(player);
        }
    }
}
