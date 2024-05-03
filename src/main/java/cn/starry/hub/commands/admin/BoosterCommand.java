package cn.starry.hub.commands.admin;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BoosterCommand extends Command {

    public BoosterCommand() {
        super("booster");
        setAliases(Arrays.asList("硬币增倍器"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!commandSender.hasPermission("lobby.booster")) {
            commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
            return true;
        }
        if (strings.length != 2) {
            player.sendMessage(ColorUtil.color("&c用法: /booster <游戏> <倍数>"));
            return true;
        }
        String game = strings[0];
        double multiple = Double.parseDouble(strings[1]);
        if (Main.getInstance().getData().getBoosterData(game, "game") == null) {
            player.sendMessage(ColorUtil.color("&c此游戏不存在,请查看输入是否有误！"));
            return true;
        }
        if (multiple >= 1 && multiple < 10) {
            Main.getInstance().getData().updateBoosterData(game,"name",player.getName());
            Main.getInstance().getData().updateBoosterMultiple(game,"booster",multiple);
            player.sendMessage(ColorUtil.color("&f成功将 &b" + game + "&f 的硬币增倍器提升至 &6" + multiple + "倍"));
            return true;
        } else {
            player.sendMessage(ColorUtil.color("&c无效的倍数范围,请在1.0-10.0内选取"));
            return true;
        }
    }

}
