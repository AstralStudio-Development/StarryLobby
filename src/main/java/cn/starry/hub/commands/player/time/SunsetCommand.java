package cn.starry.hub.commands.player.time;

import cn.starry.core.Core;
import cn.starry.core.api.data.CacheData;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class SunsetCommand extends Command {

    public SunsetCommand() {
        super("sunset");
        setAliases(Arrays.asList("傍晚"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        UUID uuid = player.getUniqueId();
            if (!Objects.equals(StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type"), "Login")) {
                if (!Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(),"settings_lobby_time").equals("SUNSET")) {
                    player.setPlayerTime(12650L, false);
                    Core.getInstance().getMongoDB().updatePlayerData(player.getUniqueId(),"settings_lobby_time", "SUNSET");
                    player.sendMessage(CC.translate("&f已将你的时间设置为 &b傍晚"));
                } else {
                    player.sendMessage(CC.translate("&c当前时间已为傍晚!"));
                }
            } else {
                player.sendMessage(CC.translate("&c此服务器不允许执行该命令"));
            }
        return true;
    }
}
