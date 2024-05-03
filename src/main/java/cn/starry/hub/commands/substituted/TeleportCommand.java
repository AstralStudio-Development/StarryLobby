package cn.starry.hub.commands.substituted;

import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super("tp");
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
            if (commandSender instanceof Player) {
                final Player player = (Player)commandSender;
                if (!commandSender.hasPermission("lobby.tp")) {
                    commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
                    return true;
                }
                if (strings.length < 1) {
                    player.sendMessage(ColorUtil.color("&c用法:/tp [被操作玩家] <目的地玩家> 或 /tp [目标] <x> <y> <z> [<y-方向> <x-方向>]"));
                    return true;
                }
                if (strings.length == 1) {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0]))) {
                        player.teleport(Bukkit.getPlayer(strings[0]));
                        player.sendMessage(ColorUtil.color("&f已将 " + player.getName() + " &f传送至 " + strings[0]));
                    }
                }
                if (strings.length == 2) {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0])) && Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[1]))) {
                        Bukkit.getPlayer(strings[0]).teleport(Bukkit.getPlayer(strings[1]));
                        player.sendMessage(ColorUtil.color("&f已将 " + strings[0] + " &f传送至 " + strings[1]));
                    }
                }
                if (strings.length == 3 && !Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0])) && !Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[1])) && !Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[2]))) {
                    player.teleport(new Location(player.getWorld(),Double.parseDouble(strings[0]),Double.parseDouble(strings[1]),Double.parseDouble(strings[2])));
                    player.sendMessage(ColorUtil.color("&f将 " + player.getName() + " &f传送到 " + strings[0] + "," + strings[1] + "," + strings[2]));
                }
                if (strings.length == 4 && Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0])) && !Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[1])) && !Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[2])) && !Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[3]))) {
                    Bukkit.getPlayer(strings[0]).teleport(new Location(player.getWorld(),Double.parseDouble(strings[1]),Double.parseDouble(strings[2]),Double.parseDouble(strings[3])));
                    player.sendMessage(ColorUtil.color("&f将 " + strings[0] + " &f传送到 " + strings[1] + "," + strings[2] + "," + strings[3]));
                }
            } else {
                commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            }
        return true;
    }
}
