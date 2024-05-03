package cn.starry.hub.listener.handler;

import cn.starry.hub.utils.ColorUtil;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.InvocationTargetException;

public class TabHandler implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PacketContainer TabListPc = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        TabListPc.getChatComponents().write(0, WrappedChatComponent.fromText(ColorUtil.color("&b你正在游玩&f&lYumeGames&b服务器")));
        TabListPc.getChatComponents().write(1, WrappedChatComponent.fromText(ColorUtil.color("\n&7YumeGames\n")));
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(event.getPlayer(), TabListPc);
        }
        catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
