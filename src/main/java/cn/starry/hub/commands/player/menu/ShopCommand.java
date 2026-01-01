package cn.starry.hub.commands.player.menu;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.functions.menu.bedwars.BedWarsShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand extends Command {

    public ShopCommand() {
        super("shop");
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("BedWars")) {
            new BedWarsShopMenu().openMenu(player);
        } else {
            player.sendMessage(Bukkit.spigot().getConfig().getString("settings.messages.unknown-command"));
        }
        return true;
    }
}
