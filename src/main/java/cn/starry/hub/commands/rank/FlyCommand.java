package cn.starry.hub.commands.rank;

import cn.starry.core.api.enums.Permission;
import cn.starry.core.functions.achievement.AchievementManager;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Objects;

public class FlyCommand extends Command {

    public FlyCommand() {
        super("fly");
        setAliases(Arrays.asList("飞行"));
    }

    public boolean execute(CommandSender sender, final String lable, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player p = (Player)sender;
                if (!Objects.equals(StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type"), "Login")) {
                    if (p.hasPermission(Permission.RANK.getNode()) && !p.getAllowFlight()) {
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        p.setFlySpeed(0.1f);
                        AchievementManager.unlockAchievement(p,AchievementManager.getAchievement("WeWillGoToTheSky"));
                        p.sendMessage(CC.translate("&f已将你的飞行模式设置为 &a开启"));
                    } else if (p.hasPermission("lobby.fly") && p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        p.sendMessage(CC.translate("&f已将你的飞行模式设置为 &c关闭"));
                    } else {
                        p.sendMessage(CC.translate("&c你没有权限来使用这个命令"));
                    }
                } else {
                    p.sendMessage(CC.translate("&c此服务器不允许执行该命令"));
                }
        return false;
    }

}
