package cn.starry.hub.commands.admin;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.VanishUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class VanishCommand extends Command {

    public VanishCommand() {
        super("vanish");
        setAliases(Arrays.asList("隐身","v"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!commandSender.hasPermission("lobby.vanish")) {
            commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
            return true;
        }
        VanishUtil.checkVanishState(player,true,false);
        return true;
    }

}
