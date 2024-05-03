package cn.starry.hub.functions;

import cn.starry.hub.Main;
import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.api.enums.ProfileState;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.upperlevel.spigot.book.BookUtil;

import java.util.UUID;

@AutoRegister
public class CheckWipe implements Listener {


    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(), "wipe").equalsIgnoreCase("true") && PlayerData.PROFILE.get(player).equals(ProfileState.LOADED)) {
            isWiped(player.getPlayer());
        }
    }

    public void isWiped(Player player) {
        BookUtil.openPlayer(player,
                BookUtil.writtenBook()
                        .title(ColorUtil.color("&c$wipeNotification #" + player.getName()))
                        .author(UUID.randomUUID().toString())
                        .pages(
                                new BookUtil.PageBuilder()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(ColorUtil.color("&0您因" + Main.getInstance().getData().getPlayerData(player.getUniqueId(), "wipeReason")))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                ColorUtil.color("&0我们已清除您的存档")
                                        )
                                        .newLine()
                                        .add(
                                                ColorUtil.color("&0希望您在未来的游戏中")
                                        )
                                        .newLine()
                                        .add(
                                                ColorUtil.color("&0遵守我们的规则，谢谢")
                                        ).newLine()
                                        .add(
                                                ColorUtil.color("&0如有疑问，请在&c指定&0群聊中申诉")
                                        )
                                        .newLine()
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(ColorUtil.color("&a我已知晓"))
                                                        .onHover(BookUtil.HoverAction.showText(ColorUtil.color("&f点击不再提示")))
                                                        .onClick(BookUtil.ClickAction.runCommand("/iKnowIGotWiped"))
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );
    }

}
