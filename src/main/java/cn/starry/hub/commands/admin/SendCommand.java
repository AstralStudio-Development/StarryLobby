package cn.starry.hub.commands.admin;

import cn.starry.hub.utils.BungeeUtil;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Objects;

public class SendCommand extends Command {

    public SendCommand() {
        super("send");
        setAliases(Arrays.asList("传送"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!commandSender.hasPermission("lobby.send")) {
            commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
            return true;
        }
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
            if (strings.length == 1) {
                BungeeUtil.sendServer(player, strings[0]);
                return true;
            }
            player.sendMessage(ColorUtil.color("&c用法: /sendto <服务器>"));
            return true;
        } else {
            player.sendMessage(ColorUtil.color("&c此服务器不允许执行该命令"));
            return true;
        }
    }
}
