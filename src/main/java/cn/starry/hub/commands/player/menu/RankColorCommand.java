package cn.starry.hub.commands.player.menu;

import cn.starry.hub.functions.menu.profile.RankColorMenu;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class RankColorCommand extends Command {

    public RankColorCommand() {
        super("rankcolor");
        setAliases(Arrays.asList("rankcolour","会员颜色"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        new RankColorMenu().openMenu(player,true);
        return true;
    }
}
