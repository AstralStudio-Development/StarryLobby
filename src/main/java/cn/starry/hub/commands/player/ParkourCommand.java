package cn.starry.hub.commands.player;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.functions.parkour.ParkourManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParkourCommand extends Command {

    public ParkourCommand() {
        super("parkour");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cOnly players can use this command."));
            return true;
        }

        Player player = (Player) sender;
        ParkourManager manager = ParkourManager.getInstance();

        if (args.length == 0) {
            sendHelp(player);
            return true;
        }

        String sub = args[0].toLowerCase();

        switch (sub) {
            case "start":
                manager.startParkour(player);
                break;
            case "reset":
                manager.resetParkour(player, true, true);
                break;
            case "checkpoint":
                manager.checkpoint(player);
                break;
            case "cancel":
                manager.cancelParkour(player, false);
                break;
            case "setstart":
                if (!player.hasPermission("starrylobby.admin")) {
                    player.sendMessage(CC.translate("&cNo permission."));
                    return true;
                }
                manager.setStartLocation(player.getLocation());
                player.sendMessage(CC.translate("&aParkour start location set."));
                break;
            case "setcheckpoint":
                if (!player.hasPermission("starrylobby.admin")) {
                    player.sendMessage(CC.translate("&cNo permission."));
                    return true;
                }
                if (args.length < 2) {
                    player.sendMessage(CC.translate("&cUsage: /parkour setcheckpoint <number>"));
                    return true;
                }
                try {
                    int index = Integer.parseInt(args[1]);
                    manager.setCheckpoint(index, player.getLocation());
                    player.sendMessage(CC.translate("&aCheckpoint #" + index + " set."));
                } catch (NumberFormatException e) {
                    player.sendMessage(CC.translate("&cInvalid number."));
                }
                break;
            case "deletecheckpoint":
                if (!player.hasPermission("starrylobby.admin")) {
                    player.sendMessage(CC.translate("&cNo permission."));
                    return true;
                }
                if (args.length < 2) {
                    player.sendMessage(CC.translate("&cUsage: /parkour deletecheckpoint <number>"));
                    return true;
                }
                try {
                    int index = Integer.parseInt(args[1]);
                    manager.deleteCheckpoint(index);
                    player.sendMessage(CC.translate("&aCheckpoint #" + index + " deleted."));
                } catch (NumberFormatException e) {
                    player.sendMessage(CC.translate("&cInvalid number."));
                }
                break;
            default:
                sendHelp(player);
                break;
        }

        return true;
    }

    private void sendHelp(Player player) {
        player.sendMessage(CC.translate("&e&lParkour Commands:"));
        player.sendMessage(CC.translate("&7/parkour start &f- Start parkour"));
        player.sendMessage(CC.translate("&7/parkour reset &f- Reset to start"));
        player.sendMessage(CC.translate("&7/parkour checkpoint &f- Teleport to last checkpoint"));
        player.sendMessage(CC.translate("&7/parkour cancel &f- Cancel parkour"));
        if (player.hasPermission("starrylobby.admin")) {
            player.sendMessage(CC.translate("&c&lAdmin Commands:"));
            player.sendMessage(CC.translate("&7/parkour setstart"));
            player.sendMessage(CC.translate("&7/parkour setcheckpoint <num>"));
            player.sendMessage(CC.translate("&7/parkour deletecheckpoint <num>"));
        }
    }
}
