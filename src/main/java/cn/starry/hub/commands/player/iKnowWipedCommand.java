package cn.starry.hub.commands.player;

import cn.starry.hub.Main;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class iKnowWipedCommand extends Command {

    public iKnowWipedCommand() {
        super("iKnowIGotWiped");
        setAliases(Arrays.asList("我已知晓"));
    }

    public boolean execute(CommandSender sender, final String lable, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player)sender;
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(), "wipe").equalsIgnoreCase("true")) {
            Main.getInstance().getData().updatePlayerData(player.getUniqueId(), "wipe", "false");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP,1,1);
        } else {
            player.sendMessage(ColorUtil.color("&c你不需要解除Wipe提醒,当前非Wiped状态！"));
        }
        return false;
    }

}
