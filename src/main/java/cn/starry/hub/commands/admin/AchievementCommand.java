package cn.starry.hub.commands.admin;

import cn.starry.hub.functions.achievement.AchievementManager;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

public class AchievementCommand extends Command {

    public AchievementCommand() {
        super("achievement");
        setAliases(Arrays.asList("成就"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!commandSender.hasPermission("lobby.booster")) {
            commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
            return true;
        }
        if (strings.length != 2) {
            player.sendMessage(ColorUtil.color("&c用法: /achievement <玩家> <成就>"));
            return true;
        }
        if (Main.getInstance().getData().getPlayerData(strings[0], "uuid") == null) {
            player.sendMessage(ColorUtil.color("&c此玩家不存在于数据库内,请查看输入是否有误！"));
            return true;
        }
        AchievementManager.unlockAchievement(UUID.fromString(Main.getInstance().getData().getPlayerData(strings[0],"uuid")),AchievementManager.getAchievement(strings[1]));
        player.sendMessage(ColorUtil.color("&a" + strings[1] + "成就已成功激活于玩家" + strings[0]));
        return true;
    }

}
