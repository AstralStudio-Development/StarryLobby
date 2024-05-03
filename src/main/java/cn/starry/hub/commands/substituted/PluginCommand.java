package cn.starry.hub.commands.substituted;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.PluginUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PluginCommand extends Command {

    public PluginCommand() {
        super("plugin");
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
            if (commandSender instanceof Player) {
                final Player player = (Player)commandSender;
                if (!commandSender.hasPermission("lobby.plugin")) {
                    commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
                    return true;
                }
                player.sendMessage(ColorUtil.color("Plugins (" + PluginUtil.getPluginCount() + "):&a " + PluginUtil.getPluginNames())
                        .replace("[","")
                        .replace("]","")
                        .replaceAll("StarryLobby",ColorUtil.color("&bStarryLobby&a"))
                        .replaceAll("ColdLeaderBoards",ColorUtil.color("&bColdLeaderBoards&a"))
                        .replaceAll("ShuttleSkyWars",ColorUtil.color("&bShuttleSkyWars&a"))
                        .replaceAll("SkyWarsProxy",ColorUtil.color("&bSkyWarsProxy&a"))
                        .replaceAll("BedWarsProxy",ColorUtil.color("&bBedWarsProxy&a"))
                        .replaceAll("BedWars1058",ColorUtil.color("&bBedWars1058&a"))
                );
            } else {
                commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            }
        return true;
    }
}
