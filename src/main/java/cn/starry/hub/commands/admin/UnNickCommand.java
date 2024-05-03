package cn.starry.hub.commands.admin;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.NickUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class UnNickCommand extends Command {

    public UnNickCommand() {
        super("unnick");
        setAliases(Arrays.asList("取消匿名"));
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
        if (!NickUtil.isNicked(player.getUniqueId())) {
            player.sendMessage(ColorUtil.color("&c你当前没有匿名!"));
            return true;
        } else {
            NickUtil.setUnNick(player);
            player.sendMessage(ColorUtil.color("&a已成功取消当前匿名!"));
            return true;
        }

    }

}
