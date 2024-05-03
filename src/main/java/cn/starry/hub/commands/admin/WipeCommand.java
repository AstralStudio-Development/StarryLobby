package cn.starry.hub.commands.admin;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class WipeCommand extends Command {

    public WipeCommand() {
        super("wipe");
        setAliases(Arrays.asList("清除"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!commandSender.hasPermission("lobby.wipe")) {
            commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
            return true;
        }
        if (strings.length != 2) {
            player.sendMessage(ColorUtil.color("&c用法: /wipe <玩家> <理由>"));
            return true;
        }
        Player target = Bukkit.getPlayer(strings[0]);
        String reason = strings[1];
        if (Main.getInstance().getData().getPlayerData(strings[0], "uuid") == null) {
            player.sendMessage(ColorUtil.color("&c此玩家不存在于数据库内,请查看输入是否有误！"));
            return true;
        }
        if (Bukkit.getServer().getOnlinePlayers().contains(target)) {
            target.kickPlayer(ColorUtil.color("&c在加载你的数据时产生了一个错误,请重新加入服务器以便验证数据"));
        }
        if (Main.getInstance().getData().getPlayerData(strings[0], "wipe").equalsIgnoreCase("false")) {
            Main.getInstance().getData().updatePlayerData(strings[0], "wipe", "true");
            Main.getInstance().getData().updatePlayerData(strings[0], "wipeReason", reason);
            player.sendMessage(ColorUtil.color("&a已成功将此玩家的数据清除！"));
            return true;
        } else {
            Main.getInstance().getData().updatePlayerData(strings[0], "wipeReason", reason);
            player.sendMessage(ColorUtil.color("&a已更新Wipe理由！新的理由是: " + strings[0]));
            return true;
        }
    }

}
