package cn.starry.hub.commands.player.menu;

import cn.starry.hub.functions.menu.profile.settings.LobbySettingsMenu;
import cn.starry.hub.utils.ColorUtil;
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
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        player.sendMessage(ColorUtil.color("&a正在打开设置..."));
        new LobbySettingsMenu().openMenu(player,false);
        return true;
    }
}
