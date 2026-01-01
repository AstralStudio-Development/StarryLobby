package cn.starry.hub.listener.handler;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.chat.CC;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import dev.jnic.annotations.Include;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;

@Include
public class TabHandler implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PacketContainer TabListPc = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        TabListPc.getChatComponents().write(0, WrappedChatComponent.fromText(CC.translate("&7\n&f  幻梦茶会 &8@ &b" + new SimpleDateFormat("yyyy").format(System.currentTimeMillis()) + " \n&7")));
        TabListPc.getChatComponents().write(1, WrappedChatComponent.fromText(CC.translate("&7\n&8       » &7交流群 &f477859489 &8«       \n&8» &7游戏地址 &fYume.games &8«\n" + "       \n&7")));
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(event.getPlayer(), TabListPc);
        }
        catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
