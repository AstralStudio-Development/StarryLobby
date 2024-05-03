package cn.starry.hub.commands.rank;

import cn.starry.hub.functions.achievement.AchievementManager;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.LuckPermsUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class KaboomCommand extends Command {

    public KaboomCommand() {
        super("kaboom");
        setAliases(Arrays.asList("轰隆"));
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
            if (commandSender instanceof Player) {
                final Player player = (Player)commandSender;
                if (!commandSender.hasPermission("lobby.kaboom")) {
                    commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
                    return true;
                }
                for (Player target : Bukkit.getOnlinePlayers()) {
                    AchievementManager.unlockAchievement(target,AchievementManager.getAchievement("Kaboom"));
                    target.setVelocity(new Vector(0, 2, 0));
                    target.getWorld().strikeLightningEffect(target.getLocation());
                    target.sendMessage(ColorUtil.color("&a&l轰隆!!! " + LuckPermsUtil.getPlayerColoredName(player.getName()) + " &7把你击飞了!"));
                }
            } else {
                for (Player target : Bukkit.getOnlinePlayers()) {
                    AchievementManager.unlockAchievement(target,AchievementManager.getAchievement("Kaboom"));
                    target.setVelocity(new Vector(0, 2, 0));
                    target.getWorld().strikeLightningEffect(target.getLocation());
                    target.sendMessage(ColorUtil.color("&a&l轰隆!!! &cConsole &7把你击飞了!"));
                }
                Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&a&l轰隆!!! &c你 &7把所有人击飞了!"));
            }
        return true;
        }
    }
