package cn.starry.hub.commands.player.time;

import cn.starry.hub.database.MongoDB;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
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
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        UUID uuid = player.getUniqueId();
            if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
                if (!Main.getPlugin(Main.class).getData().getPlayerData(uuid, "settings_lobby_time").equals("SUNSET")) {
                    player.setPlayerTime(12650L, false);
                    new MongoDB().updatePlayerData(uuid, "settings_lobby_time", "SUNSET");
                    player.sendMessage(ColorUtil.color("&f已将你的时间设置为 &b傍晚"));
                } else {
                    player.sendMessage(ColorUtil.color("&c当前时间已为傍晚!"));
                }
            } else {
                player.sendMessage(ColorUtil.color("&c此服务器不允许执行该命令"));
            }
        return true;
    }
}
