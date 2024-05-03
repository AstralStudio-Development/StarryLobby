package cn.starry.hub.commands.admin;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.NickUtil;
import cn.starry.hub.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WhoCommand extends Command {

    public WhoCommand() {
        super("who");
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!commandSender.hasPermission("lobby.nick")) {
            commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
            return true;
        }
        if (strings.length != 1) {
            player.sendMessage(ColorUtil.color("&c用法: /who <玩家名>"));
            return true;
        } else {
            if (Main.getInstance().getData().getPlayerData(strings[0], "uuid") == null) {
                player.sendMessage(ColorUtil.color("&c此玩家不存在于数据库内,请查看输入是否有误！"));
                return true;
            }
            UUID uuid = UUID.fromString(Main.getInstance().getData().getPlayerData(strings[0],"uuid"));
            if (!NickUtil.isNicked(uuid)) {
                player.sendMessage(ColorUtil.color("&c他并不处于匿名状态!"));
                return true;
            } else {
                player.sendMessage(ColorUtil.color("&a该玩家处于匿名状态"));
                player.sendMessage(ColorUtil.color("&7" + Main.getInstance().getData().getPlayerData(uuid,"name") + "&b ➨ &f" + NickUtil.getNickName(uuid)));
                return true;
            }
        }
    }

}
