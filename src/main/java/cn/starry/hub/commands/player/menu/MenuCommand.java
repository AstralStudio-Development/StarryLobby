package cn.starry.hub.commands.player.menu;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.selector.SelectorMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MenuCommand extends Command {

    public MenuCommand() {
        super("menu");
        setAliases(Arrays.asList("菜单"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        new SelectorMenu().openMenu(player);
        return true;
    }
}
