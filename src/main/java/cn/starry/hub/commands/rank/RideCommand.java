package cn.starry.hub.commands.rank;

import cn.starry.hub.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class RideCommand extends Command {

    public RideCommand() {
        super("ride");
        setAliases(Arrays.asList("骑行"));
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
            if (commandSender instanceof Player) {
                final Player player = (Player)commandSender;
                if (!commandSender.hasPermission("lobby.ride")) {
                    commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
                    return true;
                }
                if (strings.length != 1) {
                    player.sendMessage(ColorUtil.color("&c用法: /ride <玩家>"));
                    return true;
                }
                Player target = player.getServer().getPlayer(strings[0]);
                if (target == null) {
                    player.sendMessage(ColorUtil.color("&c找不到指定的玩家或该玩家不在线！"));
                    return true;
                }
                if (target == player) {
                    player.sendMessage(ColorUtil.color("&c你不能骑行你自己！"));
                    return true;
                }
                if (target.getDisplayName().equalsIgnoreCase("Starry_Killer")) {
                    player.sendMessage(ColorUtil.color("&c你不能骑行插件作者！"));
                    return true;
                }
                target.setPassenger(player);
            } else {
                commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            }
        return true;
        }
    }
