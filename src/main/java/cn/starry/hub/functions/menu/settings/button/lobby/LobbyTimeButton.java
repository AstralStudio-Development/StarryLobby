package cn.starry.hub.functions.menu.settings.button.lobby;

import cn.starry.core.Core;
import cn.starry.core.database.MongoDB;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
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

public class LobbyTimeButton extends Button {

    public boolean isButton = false;
    public Menu parent;
    public int typeID;

    public LobbyTimeButton(boolean isButton, Menu parent, int typeID) {
        this.isButton = isButton;
        this.parent = parent;
        this.typeID = typeID;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        MongoDB mongoDB = Core.getInstance().getMongoDB();

        ItemStack timeItemStack = new ItemStack(Material.BARRIER);

        List<String> lores = new ArrayList<>();

        String type = mongoDB.getPlayerData(player.getUniqueId(),"settings_lobby_time");

        if (type.equals("DAY")) {
            lores.add("   &a大厅时间状态   ");
            lores.add(" ");
            lores.add("   &7设置大厅的时间状态   ");
            lores.add(" ");
            lores.add("   &7当前 &a白天   ");
            lores.add("   &7下一个选项是 &6傍晚");
            lores.add(" ");

            timeItemStack = new ItemStack(Material.RED_DYE);
        } else if (type.equals("SUNSET")) {
            lores.add("   &6大厅时间状态   ");
            lores.add(" ");
            lores.add("   &7设置大厅的时间状态   ");
            lores.add(" ");
            lores.add("   &7当前 &6傍晚   ");
            lores.add("   &7下一个选项是 &b夜晚   ");
            lores.add(" ");

            timeItemStack = new ItemStack(Material.ORANGE_DYE);
        } else if (type.equals("NIGHT")) {
            lores.add("   &b大厅时间状态   ");
            lores.add(" ");
            lores.add("   &7设置大厅的时间状态   ");
            lores.add(" ");
            lores.add("   &7当前 &b夜晚   ");
            lores.add("   &f下一个选项是 &a白天   ");
            lores.add("  ");

            timeItemStack = new ItemStack(Material.INK_SAC);
        } else {
            player.sendMessage(CC.translate("&c发生了一个错误，类型:TIME_TYPE"));
            player.sendMessage(CC.translate("&c请将错误截图反馈至管理员"));
        }

        return new ItemBuilder(isButton ? new ItemStack(Material.REDSTONE_LAMP) : timeItemStack).name(" ").lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        MongoDB mongoDB = Core.getInstance().getMongoDB();
        if (mongoDB.getPlayerData(player.getUniqueId(),"settings_lobby_time").equals("DAY")) {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_lobby_time","SUNSET");
            player.setPlayerTime(12650L,false);
            new SettingsMenu(parent,typeID).openMenu(player);
        } else if (mongoDB.getPlayerData(player.getUniqueId(),"settings_lobby_time").equals("SUNSET")) {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_lobby_time","NIGHT");
            player.setPlayerTime(18000L,false);
            new SettingsMenu(parent,typeID).openMenu(player);
        } else if (mongoDB.getPlayerData(player.getUniqueId(),"settings_lobby_time").equals("NIGHT")) {
            mongoDB.updatePlayerData(player.getUniqueId(),"settings_lobby_time","DAY");
            player.setPlayerTime(1200L,false);
            new SettingsMenu(parent,typeID).openMenu(player);
        } else {
            player.sendMessage(CC.translate("&c发生了一个错误，类型:TIME_TYPE"));
            player.sendMessage(CC.translate("&c请将错误截图反馈至管理员"));
        }
    }
}
