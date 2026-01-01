package cn.starry.hub.commands.player;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StuckCommand extends Command {

    public StuckCommand() {
        super("stuck");
    }

    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player)sender;
        final Location spawn = player.getWorld().getSpawnLocation();
        spawn.add(0.5, 0, 0.5);
        spawn.setYaw(StarryLobby.getPlugin(StarryLobby.class).getConfig().getInt("yaw"));
        spawn.setPitch(0);
        player.teleport(spawn);
        return true;
    }

}
