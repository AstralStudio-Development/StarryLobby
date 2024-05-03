package cn.starry.hub.commands.admin;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.NickUtil;
import cn.starry.hub.functions.achievement.AchievementManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class  NickCommand extends Command {

    private final List<String> ADMIN_LIST = Arrays.asList("Starry_Killer","MoJiya_","Leruitou","XingMC","CloudForeal","YukiOfficial_","Stalyer");

    public NickCommand() {
        super("nick");
        setAliases(Arrays.asList("匿名"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission("lobby.nick")) {
            commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
            return true;
        }
        if (strings.length != 1 && strings.length != 0) {
            player.sendMessage(ColorUtil.color("&c用法: /nick <玩家名>"));
            return true;
        } else if (strings.length == 0) {
            NickUtil.BookHelpPage(player);
            return true;
        } else {
            if (strings[0].equalsIgnoreCase(player.getName())) {
                player.sendMessage(ColorUtil.color("&c你不能匿名为自己!"));
                return true;
            }
            if (NickUtil.isNicked(player.getUniqueId())) {
                player.sendMessage(ColorUtil.color("&c你已经处于匿名状态了,如要修改匿名,请先取消当前匿名!"));
                return true;
            }
            NickUtil.setNick(player,strings[0]);
            AchievementManager.unlockAchievement(player, AchievementManager.getAchievement("WhoAmI"));
            player.sendMessage(ColorUtil.color("&a已成功匿名! 你现在的名字是:" + strings[0]));
            player.sendMessage(ColorUtil.color("&a请加入其他服务器以更新你的皮肤" + strings[0]));
            return true;
        }
    }

}
