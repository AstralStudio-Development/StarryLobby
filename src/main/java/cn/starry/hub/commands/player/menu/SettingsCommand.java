package cn.starry.hub.commands.player.menu;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.menus.settings.SettingsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SettingsCommand extends Command {

    public SettingsCommand() {
        super("settings");
        setAliases(Arrays.asList("设置"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        player.sendMessage(CC.translate("&a正在打开设置..."));
        new SettingsMenu(null, 0).openMenu(player);
        return true;
    }
}

