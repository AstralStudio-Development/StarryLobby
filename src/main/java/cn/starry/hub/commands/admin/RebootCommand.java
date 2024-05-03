package cn.starry.hub.commands.admin;

import cn.starry.hub.functions.runnable.RebootRunnable;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.time.Duration;
import cn.starry.hub.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class RebootCommand extends Command {

    private final List<String> ADMIN_LIST = Arrays.asList("Starry_Killer","MoJiya_","Leruitou","XingMC","CloudForeal","YukiOfficial_","Stalyer");

    public RebootCommand() {
        super("reboot");
        setAliases(Arrays.asList("重新启动"));
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!ADMIN_LIST.contains(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"name"))) {
            commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
            return true;
        }
        if (strings.length != 2) {
            player.sendMessage(ColorUtil.color("&c用法: /reboot <时间> <原因>"));
            return true;
        } else {
            Main.getInstance().getRebootRunnable().addRebootTask(new RebootRunnable.RebootTask(strings[1], System.currentTimeMillis() + Duration.fromString(strings[0]).getValue()));
            return true;
        }
    }

}
