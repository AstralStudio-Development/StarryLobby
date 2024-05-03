package cn.starry.hub.commands.player;

import cn.starry.hub.Main;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManagerCommand extends Command {

    public ManagerCommand() {
        super("starrylobby");
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
            if (commandSender instanceof Player) {
                final Player player = (Player)commandSender;
                player.sendMessage(ColorUtil.color("&8正在检查插件版本..."));
                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(Main.class), () -> {
                   player.sendMessage(ColorUtil.color("&f当前插件版本号为 &b" + Main.getInstance().getDescription().getVersion()));
                    player.sendMessage(ColorUtil.color("&f此版本授权予 &b" + Main.getInstance().getVersion()));
                    player.sendMessage(ColorUtil.color("&f该版本由&b " + Main.getInstance().getTeam() + (Main.getInstance().getTeam().equals("Starry_Killer") ? " &c(官方)" : " &7(非官方)") + " &f提供技术支持"));
                    player.sendMessage(ColorUtil.color("&fStarryLobby原作 &8- &bStarry_Killer,Qlickly_,CloudForeal"));
                }, 20L);
            } else {
                Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&8Checking your plugin version..."));
                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(Main.class), () -> {
                    Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&fThe current plugin version number is &b" + Main.getInstance().getDescription().getVersion()));
                    Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&fThis version is licensed to &b" + Main.getInstance().getVersion()));
                    Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&fPowered by &b" + Main.getInstance().getTeam() + (Main.getInstance().getTeam().equals("Starry_Killer") ? " &c(OFFICIAL)" : " &7(UNOFFICIAL)")));
                    Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&fStarryLobby Auhtors &8- &bStarry_Killer,Qlickly_,CloudForeal"));
                }, 20L);
            }
        return true;
        }
    }
