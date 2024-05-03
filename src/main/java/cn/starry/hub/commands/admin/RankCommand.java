package cn.starry.hub.commands.admin;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.RankUtil;
import cn.starry.hub.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand extends Command {

    public RankCommand() {
        super("rank");
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!commandSender.hasPermission("lobby.rank")) {
            commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
            return true;
        }
        if (strings.length != 2) {
            player.sendMessage(ColorUtil.color("&c用法: /rank <玩家> <RankName>"));
            return true;
        }
        if (Main.getInstance().getData().getPlayerData(strings[0], "uuid") == null) {
            player.sendMessage(ColorUtil.color("&c此玩家不存在于数据库内,请查看输入是否有误！"));
            return true;
        }
        if (!RankUtil.isRankAvailable(strings[1])) {
            player.sendMessage(ColorUtil.color("&c此Rank当前不可用,请查看输入是否有误！"));
            return true;
        }
        Main.getInstance().getData().updatePlayerData(strings[0],"rank",strings[1]);
        player.sendMessage(ColorUtil.color("&a更改Rank成功！"));
        return true;
    }

}
