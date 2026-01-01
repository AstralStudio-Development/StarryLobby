package cn.starry.hub.commands.admin;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.Permission;
import cn.starry.core.functions.achievement.AchievementManager;
import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.RankUtil;
import dev.jnic.annotations.Include;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Arrays;

@Include
public class KaboomCommand extends Command {

    public KaboomCommand() {
        super("kaboom");
        setAliases(Arrays.asList("轰隆"));
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
            if (commandSender instanceof Player) {
                final Player player = (Player)commandSender;
                if (!commandSender.hasPermission(Permission.ADMIN.getNode())) {
                    commandSender.sendMessage(CC.translate("&c你没有权限来使用这个命令"));
                    return true;
                }
                for (Player target : Bukkit.getOnlinePlayers()) {
                    AchievementManager.unlockAchievement(target,AchievementManager.getAchievement("Kaboom"));
                    target.setVelocity(new Vector(0, 2, 0));
                    target.getWorld().strikeLightningEffect(target.getLocation());
                    target.sendMessage(CC.translate("&a&l轰隆!!! " + RankUtil.getFormatRankById(Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"rank"), player.getUniqueId()) + player.getDisplayName() + " &7把你击飞了!"));
                }
            } else {
                for (Player target : Bukkit.getOnlinePlayers()) {
                    AchievementManager.unlockAchievement(target,AchievementManager.getAchievement("Kaboom"));
                    target.setVelocity(new Vector(0, 2, 0));
                    target.getWorld().strikeLightningEffect(target.getLocation());
                    target.sendMessage(CC.translate("&a&l轰隆!!! &cConsole &7把你击飞了!"));
                }
                Bukkit.getConsoleSender().sendMessage(CC.translate("&a&l轰隆!!! &c你 &7把所有人击飞了!"));
            }
        return true;
        }
    }
