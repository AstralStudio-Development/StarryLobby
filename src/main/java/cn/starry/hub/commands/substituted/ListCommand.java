package cn.starry.hub.commands.substituted;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ListUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
        List<String> displayNames = ListUtil.getPlayerDisplayNames();
        String displayNamesStr = String.join(", ", displayNames);
        commandSender.sendMessage(ColorUtil.color("Online Players (" + ListUtil.getOnlineCount() + "):&7 " + displayNamesStr));
        return true;
    }
}
