package cn.starry.hub.commands.admin;

import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.api.enums.PlayerState;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class EditCommand extends Command {
    public EditCommand() {
        super("edit");
        setAliases(Arrays.asList("编辑"));
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;
            if (!commandSender.hasPermission("lobby.edit")) {
                commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
                return true;
            }
            if (PlayerData.EDIT.get(player) == PlayerState.EDIT_OFF) {
                PlayerData.EDIT.put(player, PlayerState.EDIT_ON);
                player.sendMessage(ColorUtil.color("&a你现在可以对方块进行操作了"));
            } else {
                PlayerData.EDIT.put(player, PlayerState.EDIT_OFF);
                player.sendMessage(ColorUtil.color("&c你不再被允许对方块进行操作"));
            }
        } else {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
        }
        return true;
    }
}
