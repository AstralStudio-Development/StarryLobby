package cn.starry.hub.commands.player;

import cn.starry.core.utils.chat.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EmojiCommand extends Command {

    public EmojiCommand() {
        super("emoji");
    }

    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player)sender;
        player.sendMessage(new String[]{"§a以下是所有在大厅可用的表情变量: §7(§7需要§bMVP§c+§7或以上！)",
                "§6<3 §f- §c❤",
                "§6:star: §f- §6✬",
                "§6:yes: §f- §a✔",
                "§6:no: §f- §c✖",
                "§6:java: §f- §b☕",
                "§6:123: §f- §a1§e2§c3",
                "§6:oof: §f- §c§lOOF",
                "§6:cat: §f- §d(\\\u25cfω●~)",
                "§6:qwq: §f- §d§lQwQ",
                "§6:awa: §f- §a§lAwA",
                "§6:owo: §f- §e§lOwO~",
                "§6:skeleton: §f- §4☠",
                "§6:snow: §f- §b☃"});
        return true;
    }

}
