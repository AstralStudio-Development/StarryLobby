package cn.starry.hub.commands.player;

import cn.starry.hub.Main;
import cn.starry.hub.api.enums.PresentsType;
import cn.starry.hub.functions.activity.springfestival.Y2024.presents.PresentsManager;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.upperlevel.spigot.book.BookUtil;

import java.util.Arrays;
import java.util.Objects;

public class PresentsCommand extends Command {

    public PresentsCommand() {
        super("presents");
        setAliases(Arrays.asList("礼物"));
    }

    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) sender;
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
            if (Main.getInstance().presents) {
                BookUtil.openPlayer(player,
                        BookUtil.writtenBook()
                                .title("")
                                .author("")
                                .pages(
                                        new BookUtil.PageBuilder()
                                                .add(
                                                        ColorUtil.color("&c&lFairylands礼物:")
                                                )
                                                .newLine()
                                                .newLine()
                                                .add(
                                                        ColorUtil.color("&0寻找散落在各个大厅的礼物！")
                                                )
                                                .newLine()
                                                .newLine()
                                                .add(
                                                        ColorUtil.color("&0状态:")
                                                )
                                                .newLine()
                                                .add(
                                                        ColorUtil.color("&2&l总计: &7" + PresentsManager.getPlayerPresents(player.getUniqueId()) + "&8/&6" + Main.getInstance().getPresentsFactory().getPresents().size())
                                                )
                                                .newLine()
                                                .add(
                                                        ColorUtil.color("&c主大厅: &7" + PresentsManager.getPlayerPresentsByType(player.getUniqueId(), PresentsType.MAINLOBBY) + "&8/&6" + PresentsManager.getTotalPresentsByType(PresentsType.MAINLOBBY))
                                                )
                                                .newLine()
                                                .add(
                                                        ColorUtil.color("&2起床战争大厅: &8此分类未开放")
                                                )
                                                .newLine()
                                                .add(
                                                        ColorUtil.color("&c密室杀手大厅: &8此分类未开放")
                                                )
                                                .build()
                                )
                                .build()
                );
            } else {
                player.sendMessage(ColorUtil.color("&c活动未开放！"));
            }
        } else {
            player.sendMessage(ColorUtil.color("&c此服务器不允许执行该命令"));
        }
        return true;
    }

}
