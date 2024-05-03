package cn.starry.hub.commands.player.menu;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.menu.profile.PlayerProfileMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ProfileCommand extends Command {

    public ProfileCommand() {
        super("profile");
        setAliases(Arrays.asList("个人档案"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        new PlayerProfileMenu().openMenu(player);
        return true;
    }
}
