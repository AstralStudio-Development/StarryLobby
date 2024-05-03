package cn.starry.hub.commands.admin;

import cn.starry.hub.functions.activity.springfestival.Y2024.Y2024Major;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class EventCommand extends Command {
    public EventCommand() {
        super("event");
        setAliases(Arrays.asList("事件"));
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;
            if (!commandSender.hasPermission("lobby.event")) {
                commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
                return true;
            }
            if (strings.length == 0) {
                player.sendMessage(ColorUtil.color("&c用法: /event <事件>"));
                return true;
            }
            if (strings[0].equalsIgnoreCase("Y2024")) {
                new Y2024Major().startEvent();
                player.sendMessage("done");
                return true;
            } else {
                player.sendMessage(ColorUtil.color("&c无效或不存在的事件！"));
                return true;
            }
        } else {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
        }
        return true;
    }
}
