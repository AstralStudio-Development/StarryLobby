
package cn.starry.hub.commands.rank;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.Permission;
import cn.starry.core.utils.chat.CC;
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
            commandSender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player)commandSender;
        UUID uuid = player.getUniqueId();
        if (player.hasPermission(Permission.RANK.getNode())) {
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
                        Core.getInstance().getMongoDB().updatePlayerData(player.getUniqueId(),"speed", String.valueOf(Double.valueOf(strings[0])));
                        player.setWalkSpeed(Float.parseFloat(level) * 0.1f);
                        player.sendMessage(CC.translate("&f已将你的速度设置为 &b" + strings[0] + "级"));
                        return true;
                    }
                } else {
                    Core.getInstance().getMongoDB().updatePlayerData(player.getUniqueId(),"speed", String.valueOf(2));
                    player.setWalkSpeed(0.2f);
                    player.sendMessage(CC.translate("&f已将你的速度设置为 &b默认"));
                    return true;
                }
            }
            player.sendMessage(CC.translate("&c用法: /speed <等级> &8(1至5级)"));
        } else {
            player.sendMessage(CC.translate("&c你没有权限来使用这个命令"));
        }
        return false;
    }
}

