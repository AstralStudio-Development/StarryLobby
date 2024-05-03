
package cn.starry.hub.commands.rank;

import cn.starry.hub.Main;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class SpeedCommand extends Command {

    public SpeedCommand() {
        super("speed");
        setAliases(Arrays.asList("速度"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player)commandSender;
        UUID uuid = player.getUniqueId();
        if (player.hasPermission("lobby.speed")) {
            if (strings.length == 1) {
                ArrayList<String> levels = new ArrayList<String>();
                levels.add("0");
                levels.add("1");
                levels.add("2");
                levels.add("3");
                levels.add("4");
                levels.add("5");
                if (!strings[0].equalsIgnoreCase("default")) {
                    for (String level : levels) {
                        if (!strings[0].equalsIgnoreCase(level)) continue;
                        Main.getInstance().getData().updatePlayerData(uuid,"speed",strings[0]);
                        player.setWalkSpeed(Float.parseFloat(level) * 0.1f);
                        player.sendMessage(ColorUtil.color("&f已将你的速度设置为 &b" + strings[0] + "级"));
                        return true;
                    }
                } else {
                    Main.getInstance().getData().updatePlayerData(uuid,"speed","2");
                    player.setWalkSpeed(0.2f);
                    player.sendMessage(ColorUtil.color("&f已将你的速度设置为 &b默认"));
                    return true;
                }
            }
            player.sendMessage(ColorUtil.color("&c用法: /speed <等级> &8(1至5级)"));
        } else {
            player.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
        }
        return false;
    }
}

