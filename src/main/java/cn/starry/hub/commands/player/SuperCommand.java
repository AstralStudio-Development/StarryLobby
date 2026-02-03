package cn.starry.hub.commands.player;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.menus.achievements.AchievementsMenu;
import cn.starry.hub.utils.menu.menus.PagedMenu;
import dev.jnic.annotations.Include;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

@Include
public class SuperCommand extends Command {

    public SuperCommand() {
        super("super");
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (!Objects.equals(StarryLobby.getPlugin(StarryLobby.class).getConfig().getString("type"), "Login")) {
            if (strings.length != 1) {
                player.sendMessage(CC.translate("&cUsage: /super <classpath>"));
                return true;
            }
            switch (strings[0]) {
                case "8JLo4ZPpbi3IXMyVeyx0Tw7wUX1gjh5X":
                    // AchievementsMenu
                    new AchievementsMenu().openMenu(player);
                    break;
                case "SG4R5FxUevvBM6pJ4mU1mQzPvxqpubOpSG4R5FxUevvBM6pJ4mU1mQzPvxqpubOpSG4R5FxUevvBM6pJ4mU1mQzPvxqpubOpSG4R5FxUevvBM6pJ4mU1mQzPvxqpubOp":
                    new PagedMenu("Tested",null).openMenu(player);
                     break;
                case "dH0zW8UqA522C7thJw4zMEcan0sozZdR":
                    //BedWars Shop Debug Command
                    //new BedWarsShopMenu().openMenu(player);
                    break;
                case "4lY9zk7CmPe1GoTq0bsAs0ynh6TYpI9g":
                    //Effect
                    break;
                case "hTtjyOKQ91Bmj0u3NvZJS8a9UazUjl1M":
                    //NickUtil.BookRankPage(player);
                    break;
                case "WJ2qleVGAoGmHc1fFelEHRbQ9LKGIdHf":
                    break;
                case "CKQsatN0ff6FxU6IhPjCqq3vBdvPSTJs":
                    break;
                default:
                    player.sendMessage(CC.translate("&cImproper usage."));
            }
        } else {
            player.sendMessage(CC.translate("&c此服务器不允许执行该命令"));
        }
        return true;
    }

}

