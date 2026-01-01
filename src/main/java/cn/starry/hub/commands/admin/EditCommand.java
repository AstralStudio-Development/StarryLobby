package cn.starry.hub.commands.admin;

import cn.starry.core.api.data.CacheData;
import cn.starry.core.api.enums.Permission;
import cn.starry.core.utils.chat.CC;
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
            if (!commandSender.hasPermission(Permission.ADMIN.getNode())) {
                commandSender.sendMessage(CC.translate("&c你没有权限来使用这个命令"));
                return true;
            }
            if (CacheData.EDIT.get(player) == false) {
                CacheData.EDIT.put(player, true);
                player.sendMessage(CC.translate("&a你现在可以对方块进行操作了"));
            } else {
                CacheData.EDIT.put(player, false);
                player.sendMessage(CC.translate("&c你不再被允许对方块进行操作"));
            }
        } else {
            commandSender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
        }
        return true;
    }
}
