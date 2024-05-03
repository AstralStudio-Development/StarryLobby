package cn.starry.hub.commands.admin;

import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FakeOPCommand extends Command {

    public FakeOPCommand() {
        super("fakeop");
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!commandSender.hasPermission("lobby.fakeop")) {
            commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
            return true;
        }
        if (strings.length != 1) {
            player.sendMessage(ColorUtil.color("&c用法: /fakeop <玩家>"));
            return true;
        }
        Player target = Bukkit.getPlayer(strings[0]);
        if (!Bukkit.getOnlinePlayers().contains(target)) {
            player.sendMessage(ColorUtil.color("&c这个玩家处于离线状态！"));
            return true;
        }
        player.sendMessage("§a你给" + strings[0] + "发送了假OP信息。");
        target.sendMessage("§7[§b§lL§3§lP§7] §3日志 §l> §8(§eConsole§8) §8[§aU§8] §8(§b" + target.getName() + "§8)");
        target.sendMessage("§7[§b§lL§3§lP§7] §3日志 §l> §fpermission set * true");
        return true;
    }

}
